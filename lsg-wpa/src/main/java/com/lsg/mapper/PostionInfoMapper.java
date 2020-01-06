package com.lsg.mapper;

import java.util.Map;

import com.lsg.entity.OmsPostionInfoPo;

import tk.mybatis.mapper.common.BaseMapper;

public interface PostionInfoMapper extends BaseMapper<OmsPostionInfoPo>{

	int updatePostionStat(Map<String, Object> map);

	

}
