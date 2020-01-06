package com.lsg.mapper;

import java.util.Map;

import com.lsg.entity.OmsWorkExamInfoPo;

import tk.mybatis.mapper.common.BaseMapper;

public interface WorkerExemMapper extends BaseMapper<OmsWorkExamInfoPo>{

	int updateWorkerExemStat(Map<String, Object> map);

}
