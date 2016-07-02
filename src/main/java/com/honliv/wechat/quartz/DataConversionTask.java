package com.honliv.wechat.quartz;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author wangdesen
 * 
 * 计划任务类
 * */
public class DataConversionTask {

	private static Logger log = LoggerFactory.getLogger(DataConversionTask.class);
	
	/*public void run() {
		log.info(log.isDebugEnabled()+"");
		log.info(log.isInfoEnabled()+"");
        log.info("数据转换任务线程开始执行");
    }*/
	
	/** 计划任务map */
	private static Map<String, ScheduleJob> jobMap = new HashMap<String, ScheduleJob>();
	static {
		for (int i = 0; i < 5; i++) {
			ScheduleJob job = new ScheduleJob();
			job.setJobId("10001" + i);
			job.setJobName("data_import" + i);
			job.setJobGroup("dataWork");
			job.setJobStatus("1");
			job.setCronExpression("0/5 * * * * ?");
			job.setDesc("数据导入任务");
			addJob(job);
		}
	}
	/**
	 * 添加任务
	 * @param scheduleJob
	 */
	public static void addJob(ScheduleJob scheduleJob) {
		jobMap.put(scheduleJob.getJobGroup() + "_" + scheduleJob.getJobName(), scheduleJob);
	}
}
