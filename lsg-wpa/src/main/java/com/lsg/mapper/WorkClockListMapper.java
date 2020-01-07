package com.lsg.mapper;

import java.util.Map;

import com.lsg.entity.OmsWorkerClockListPo;

import tk.mybatis.mapper.common.BaseMapper;

public interface WorkClockListMapper extends BaseMapper<OmsWorkerClockListPo> {
	
	OmsWorkerClockListPo queryCurrentDayClock(Map<String, Object> map);
	
	int updateClockTime(Map<String, Object> map);

	int updateClockStat(Map<String, Object> map);
}
