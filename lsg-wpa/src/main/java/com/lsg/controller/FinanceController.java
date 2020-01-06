package com.lsg.controller;


import javax.validation.Valid;

import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lsg.exception.BusinessException;
import com.lsg.model.Result;
import com.lsg.service.FinanceRoleSevice;
import com.lsg.vo.FinanceVo;


@RestController
@RequestMapping("/finance" )
public class FinanceController {
	
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	
	@Autowired
    private FinanceRoleSevice financeRoleSevice;
	
	/**
	   *工资审核-待审核发放工资职位列表
	 * @throws BusinessException 
	 * */
	@ResponseBody
	@RequestMapping("/page" )
	public Result page(@RequestBody(required = false) FinanceVo vo) throws BusinessException {
		
		if(Strings.isBlank(vo.getFinancer())) {
			throw new BusinessException("财务审核员不能为空");
		}
		//工资审核-待审核发放工资职位列表
		return financeRoleSevice.page(vo);

	}
	
	
	
	/**
	   *工资审核-职位工作人员工资审核
	 * @throws BusinessException 
	 * */
	@ResponseBody
	@RequestMapping("/audit" )
	public Result audit(@RequestBody(required = false) FinanceVo financeVo) throws BusinessException {
		
		//工资审核-职位工作人员工资审核
		return financeRoleSevice.audit(financeVo );

	}
}
