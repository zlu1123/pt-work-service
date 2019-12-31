package com.lsg.mapper;



import java.util.Map;

import com.lsg.entity.WpaUserInfo;

import tk.mybatis.mapper.common.BaseMapper;

public interface UserInfoMapper extends BaseMapper<WpaUserInfo> {
	
	 /**
     * 条件查询数据
     * @param userQueryVo
     * @return
     */

	int updateOpenIdByUserId(Map<String, Object> map1);
	
}
