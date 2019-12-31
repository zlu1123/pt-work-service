package com.lsg.service;

import com.lsg.exception.BusinessException;
import com.lsg.model.Result;
import com.lsg.vo.EnterpriseInfoVo;

public interface EnterpriseDirectorService {
	
	Result page(String openId);
	
	Result insert(EnterpriseInfoVo enterpriseInfoVo,String openId) throws BusinessException;
	
	Result update(EnterpriseInfoVo enterpriseInfoVo,String openId);
	
	Result delete(EnterpriseInfoVo enterpriseInfoVo,String openId);
}
