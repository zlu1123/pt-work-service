package com.lsg.service;

import com.lsg.exception.BusinessException;
import com.lsg.model.Result;
import com.lsg.vo.PositionApplyInfoVo;

public interface PositionManageService {
	
	Result insert(PositionApplyInfoVo positionApplyInfoVo,String openId);
	
	Result update(PositionApplyInfoVo positionApplyInfoVo,String openId);
	
	Result delete(PositionApplyInfoVo positionApplyInfoVo,String openId);
	
	Result page(PositionApplyInfoVo positionApplyInfoVo,String openId);
	
	Result applyList(PositionApplyInfoVo applyInfoVo,String openId);
	
	Result applyExam(PositionApplyInfoVo applyInfoVo,String openId) throws BusinessException;
	
	Result applyPush(PositionApplyInfoVo applyInfoVo,String openId);
}
