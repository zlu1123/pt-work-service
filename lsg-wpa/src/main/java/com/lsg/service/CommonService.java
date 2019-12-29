package com.lsg.service;

import com.lsg.model.Result;
import com.lsg.vo.CommonVo;

public interface CommonService {
	
	
	Result advert(String openId);
	
	Result queryPosition(CommonVo commonVo,String openId);
	
	Result positionInfo(CommonVo commonVo,String openId);
	
	Result suggestions(CommonVo commonVo,String openId);
}	
