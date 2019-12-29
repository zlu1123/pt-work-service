package com.lsg.mapper;



import com.lsg.entity.WpaUserInfo;

import tk.mybatis.mapper.common.BaseMapper;

public interface UserInfoMapper extends BaseMapper<WpaUserInfo> {
	
	 /**
     * 条件查询数据
     * @param userQueryVo
     * @return
     */
    
    WpaUserInfo selectOne(String openId);
	
}
