package com.lsg.mapper;


import java.util.Map;

import com.lsg.entity.OmsWorkWageListPo;

import tk.mybatis.mapper.common.BaseMapper;

public interface WorkeWageListMapper extends BaseMapper<OmsWorkWageListPo>{

	int updateWageList(Map<String, Object> map);


}
