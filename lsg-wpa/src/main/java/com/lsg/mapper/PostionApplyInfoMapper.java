package com.lsg.mapper;

import java.util.Map;

import com.lsg.entity.OmsPostionApplyInfoPo;

import tk.mybatis.mapper.common.BaseMapper;

public interface PostionApplyInfoMapper extends BaseMapper<OmsPostionApplyInfoPo>{

	

	 /**
     * 条件查询数据
     * @param userQueryVo
     * @return
     */
	int updateExemStat(Map<String, Object> map);
    
}
