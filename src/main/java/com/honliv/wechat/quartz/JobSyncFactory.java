package com.honliv.wechat.quartz;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.honliv.wechat.bean.ScheduleJob;
import com.honliv.wechat.bean.ScheduleJobVo;

/**
 * 同步的任务工厂类
 *
 * Created by liyd on 12/19/14.
 */
public class JobSyncFactory implements Job {

    /* 日志对象 */
    private static final Logger LOG = LoggerFactory.getLogger(JobSyncFactory.class);
    
//    public static AccessToken accessToken = null;

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        LOG.info("JobSyncFactory execute");

        JobDataMap mergedJobDataMap = jobExecutionContext.getMergedJobDataMap();
        ScheduleJob scheduleJob = (ScheduleJob) mergedJobDataMap.get(ScheduleJobVo.JOB_PARAM_KEY);

        System.out.println("【JobSyncFactory】jobName:" + scheduleJob.getJobName() + "  " + scheduleJob);
        
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /*try {
        	accessToken = WeixinUtil.getAccessToken(scheduleJob.getJobName(), scheduleJob.getAliasName());  
            if (null != accessToken) {  
            	LOG.info("公众号ID：" + scheduleJob.getJobName() + "获取access_token成功，有效时长{}秒 token:{}", accessToken.getExpiresIn(), accessToken.getToken());  
            	// 休眠一小时  
                Thread.sleep(1000);
            } else {  
                // 如果access_token为null，60秒后再获取  
            	LOG.info("公众号ID：" + scheduleJob.getJobName() + "获取access_token失败！");
                Thread.sleep(60 * 1000);  
            }  
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }
}
