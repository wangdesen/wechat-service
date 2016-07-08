package com.honliv.wechat.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.dexcoder.commons.bean.BeanConverter;
import com.honliv.wechat.bean.ScheduleJob;
import com.honliv.wechat.bean.ScheduleJobVo;
import com.honliv.wechat.dao.ScheduleJobMapper;
import com.honliv.wechat.dao.ScheduleJobVoMapper;
import com.honliv.wechat.service.ScheduleJobService;
import com.honliv.wechat.util.ScheduleUtils;

/**
 * 定时任务
 *
 * Created by liyd on 12/19/14.
 */
public class ScheduleJobServiceImpl implements ScheduleJobService {

    //调度工厂Bean
    @Autowired
    private Scheduler scheduler;

    //通用Dao
//    @Autowired
//    private JdbcDao   jdbcDao;
    
    @Autowired
	private ScheduleJobMapper scheduleJobDao;

    @Autowired
	private ScheduleJobVoMapper scheduleJobVoDao;
    
    /**
     * 初始化定时任务
     * */
    public void initScheduleJob() {
    	//获取数据表中所有定时任务
        //List<ScheduleJob> scheduleJobList = jdbcDao.queryList(Criteria.select(ScheduleJob.class));
        
    	List<ScheduleJob> scheduleJobList = scheduleJobDao.queryList();
        if (CollectionUtils.isEmpty(scheduleJobList)) {
            return;
        }
        for (ScheduleJob scheduleJob : scheduleJobList) {

            CronTrigger cronTrigger = ScheduleUtils.getCronTrigger(scheduler, scheduleJob.getJobName(), scheduleJob.getJobGroup());

            //不存在，创建一个
            if (cronTrigger == null) {
                ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
            } else {
                //已存在，那么更新相应的定时设置
                ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
            }
        }
    }

    public Long insert(ScheduleJobVo scheduleJobVo) {
        ScheduleJob scheduleJob = scheduleJobVo.getTargetObject(ScheduleJob.class);
        ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
        //return jdbcDao.insert(scheduleJob);
        return Long.valueOf(scheduleJobDao.insert(scheduleJob));
    }

    public void update(ScheduleJobVo scheduleJobVo) {
        ScheduleJob scheduleJob = scheduleJobVo.getTargetObject(ScheduleJob.class);
        ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
        //jdbcDao.update(scheduleJob);
        scheduleJobDao.updateByPrimaryKey(scheduleJob);
    }

    public void delUpdate(ScheduleJobVo scheduleJobVo) {
        ScheduleJob scheduleJob = scheduleJobVo.getTargetObject(ScheduleJob.class);
        //先删除
        ScheduleUtils.deleteScheduleJob(scheduler, scheduleJob.getJobName(), scheduleJob.getJobGroup());
        //再创建
        ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
        //数据库直接更新即可
        //jdbcDao.update(scheduleJob);
        scheduleJobDao.updateByPrimaryKey(scheduleJob);
    }

    public void delete(Long scheduleJobId) {
        //ScheduleJob scheduleJob = jdbcDao.get(ScheduleJob.class, scheduleJobId);
    	ScheduleJob scheduleJob = scheduleJobDao.selectByPrimaryKey(scheduleJobId);
        //删除运行的任务
        ScheduleUtils.deleteScheduleJob(scheduler, scheduleJob.getJobName(), scheduleJob.getJobGroup());
        //删除数据
        //jdbcDao.delete(ScheduleJob.class, scheduleJobId);
        scheduleJobDao.deleteByPrimaryKey(scheduleJobId);
    }

    public void runOnce(Long scheduleJobId) {

        //ScheduleJob scheduleJob = jdbcDao.get(ScheduleJob.class, scheduleJobId);
    	ScheduleJob scheduleJob = scheduleJobDao.selectByPrimaryKey(scheduleJobId);
        ScheduleUtils.runOnce(scheduler, scheduleJob.getJobName(), scheduleJob.getJobGroup());
    }

    public void pauseJob(Long scheduleJobId) {

        //ScheduleJob scheduleJob = jdbcDao.get(ScheduleJob.class, scheduleJobId);
    	ScheduleJob scheduleJob = scheduleJobDao.selectByPrimaryKey(scheduleJobId);
        ScheduleUtils.pauseJob(scheduler, scheduleJob.getJobName(), scheduleJob.getJobGroup());

        //演示数据库就不更新了
    }

    public void resumeJob(Long scheduleJobId) {
        //ScheduleJob scheduleJob = jdbcDao.get(ScheduleJob.class, scheduleJobId);
    	ScheduleJob scheduleJob = scheduleJobDao.selectByPrimaryKey(scheduleJobId);
        ScheduleUtils.resumeJob(scheduler, scheduleJob.getJobName(), scheduleJob.getJobGroup());

        //演示数据库就不更新了
    }

    public ScheduleJobVo get(Long scheduleJobId) {
        //ScheduleJob scheduleJob = jdbcDao.get(ScheduleJob.class, scheduleJobId);
    	ScheduleJob scheduleJob = scheduleJobDao.selectByPrimaryKey(scheduleJobId);
        return scheduleJob.getTargetObject(ScheduleJobVo.class);
    }

    public List<ScheduleJobVo> queryList(ScheduleJobVo scheduleJobVo) {

        //List<ScheduleJob> scheduleJobs = jdbcDao.queryList(scheduleJobVo.getTargetObject(ScheduleJob.class));

    	List<ScheduleJob> scheduleJobs = scheduleJobDao.queryList();
    	
        List<ScheduleJobVo> scheduleJobVoList = BeanConverter.convert(ScheduleJobVo.class, scheduleJobs);
        try {
            for (ScheduleJobVo vo : scheduleJobVoList) {

                JobKey jobKey = ScheduleUtils.getJobKey(vo.getJobName(), vo.getJobGroup());
                List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
                if (CollectionUtils.isEmpty(triggers)) {
                    continue;
                }

                //这里一个任务可以有多个触发器， 但是我们一个任务对应一个触发器，所以只取第一个即可，清晰明了
                Trigger trigger = triggers.iterator().next();
                scheduleJobVo.setJobTrigger(trigger.getKey().getName());

                Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
                vo.setStatus(triggerState.name());

                if (trigger instanceof CronTrigger) {
                    CronTrigger cronTrigger = (CronTrigger) trigger;
                    String cronExpression = cronTrigger.getCronExpression();
                    vo.setCronExpression(cronExpression);
                }
            }
        } catch (SchedulerException e) {
            //演示用，就不处理了
        }
        return scheduleJobVoList;
    }

    public List<ScheduleJobVo> queryExecutingJobList() {
        try {
            List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();
            List<ScheduleJobVo> jobList = new ArrayList<ScheduleJobVo>(executingJobs.size());
            for (JobExecutionContext executingJob : executingJobs) {
                ScheduleJobVo job = new ScheduleJobVo();
                JobDetail jobDetail = executingJob.getJobDetail();
                JobKey jobKey = jobDetail.getKey();
                Trigger trigger = executingJob.getTrigger();
                job.setJobName(jobKey.getName());
                job.setJobGroup(jobKey.getGroup());
                job.setJobTrigger(trigger.getKey().getName());
                Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
                job.setStatus(triggerState.name());
                if (trigger instanceof CronTrigger) {
                    CronTrigger cronTrigger = (CronTrigger) trigger;
                    String cronExpression = cronTrigger.getCronExpression();
                    job.setCronExpression(cronExpression);
                }
                jobList.add(job);
            }
            return jobList;
        } catch (SchedulerException e) {
            //演示用，就不处理了
            return null;
        }

    }
}
