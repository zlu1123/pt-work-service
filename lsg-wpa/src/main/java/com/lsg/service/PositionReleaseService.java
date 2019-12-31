package com.lsg.service;


import com.lsg.exception.BusinessException;
import com.lsg.model.Result;
import com.lsg.vo.PostionInfoVo;

public interface PositionReleaseService {
	
	Result insert(PostionInfoVo PostionInfoVo,String openId) throws BusinessException;
	
	Result update(PostionInfoVo PostionInfoVo,String openId);
	
	Result delete(PostionInfoVo PostionInfoVo,String openId);
	
	Result page(PostionInfoVo PostionInfoVo,String openId);

	Result positionInfo( PostionInfoVo PostionInfoVo, String openId) throws BusinessException;
	

}
