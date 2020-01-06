package com.lsg.service;

import com.lsg.exception.BusinessException;
import com.lsg.model.Result;
import com.lsg.vo.PostionInfoVo;

public interface PositionApplyService {
	
	Result insert(PostionInfoVo PostionInfoVo,String openId);
	
	Result update(PostionInfoVo PostionInfoVo,String openId);
	
	Result delete(PostionInfoVo PostionInfoVo,String openId);
	
	Result page(PostionInfoVo PostionInfoVo,String openId);
	
	
	Result applyList(PostionInfoVo applyInfoVo, String openId);
	
	Result applyExam(PostionInfoVo applyInfoVo) throws BusinessException;
	
	Result applyPush(PostionInfoVo applyInfoVo,String openId);
}