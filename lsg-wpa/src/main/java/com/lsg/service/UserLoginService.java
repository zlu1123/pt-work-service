package com.lsg.service;




import com.lsg.exception.BusinessException;
import com.lsg.model.Result;
import com.lsg.vo.UserLoginVo;

public interface UserLoginService {
	
	Result UserLogin(UserLoginVo vo,String id) throws BusinessException;
	
}	
