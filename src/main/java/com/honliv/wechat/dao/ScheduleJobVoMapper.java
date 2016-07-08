package com.honliv.wechat.dao;

import java.util.List;

import com.honliv.wechat.bean.ScheduleJobVo;

public interface ScheduleJobVoMapper {
    int deleteByPrimaryKey(Long scheduleJobId);

    int insert(ScheduleJobVo record);

    int insertSelective(ScheduleJobVo record);

    ScheduleJobVo selectByPrimaryKey(Long scheduleJobId);

    int updateByPrimaryKeySelective(ScheduleJobVo record);

    int updateByPrimaryKey(ScheduleJobVo record);
    
    List<ScheduleJobVo> queryList();
}