package com.lsg.service;

import com.lsg.exception.BusinessException;
import com.lsg.model.Result;
import com.lsg.vo.FinanceVo;

public interface FinanceRoleSevice {
	
	
	Result page(FinanceVo financeVo);
	
	Result audit(FinanceVo financeVo) throws BusinessException;
}
