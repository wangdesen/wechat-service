package com.honliv.wechat.dao;

import java.util.List;

import com.honliv.wechat.bean.ScheduleJob;

public interface ScheduleJobMapper {
	
    int deleteByPrimaryKey(Long scheduleJobId);

    int insert(ScheduleJob record);

    int insertSelective(ScheduleJob record);

    ScheduleJob selectByPrimaryKey(Long scheduleJobId);

    int updateByPrimaryKeySelective(ScheduleJob record);

    int updateByPrimaryKey(ScheduleJob record);
    
    List<ScheduleJob> queryList();
}