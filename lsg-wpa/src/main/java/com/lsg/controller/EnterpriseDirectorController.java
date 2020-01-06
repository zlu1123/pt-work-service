package com.lsg.controller;

import javax.validation.Valid;

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
import com.lsg.service.EnterpriseDirectorService;
import com.lsg.vo.EnterpriseInfoVo;

@RestController
@RequestMapping("/enterpriseDirector" )
public class EnterpriseDirectorController {
	
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	
	@Autowired
    private EnterpriseDirectorService enterpriseDirectorService;
	
	/**
	   *企业负责人维护-负责人列表
	 * @throws BusinessException 
	 * */
	@ResponseBody
	@RequestMapping("/page" )
	public Result page(@RequestBody(required = false) @Valid EnterpriseInfoVo enterpriseInfoVo) throws BusinessException {
		
		logger.info("/infoRelease/page  param is {} ");
		//企业负责人维护-负责人列表
		return enterpriseDirectorService.page(enterpriseInfoVo);

	}
	
	/**
	   *企业负责人维护-负责人新增
	 * @throws BusinessException 
	 * */
	@ResponseBody
	@RequestMapping("/insert" )
	public Result insert(@RequestBody(required = false) @Valid EnterpriseInfoVo enterpriseInfoVo) throws BusinessException {
		
		//企业负责人维护-负责人新增
		return enterpriseDirectorService.insert(enterpriseInfoVo);

	}
	
	
	/**
	   *企业负责人维护-负责人更新
	 * */
	@ResponseBody
	@RequestMapping("/update" )
	public Result update(@RequestBody(required = false) @Valid EnterpriseInfoVo enterpriseInfoVo,
			@RequestHeader(value = "openId", required = true) String openId) {
		
		//企业负责人维护-负责人更新
		return enterpriseDirectorService.update(enterpriseInfoVo,openId);

	}
	
	
	/**
	   *企业负责人维护-负责人删除
	 * */
	@ResponseBody
	@RequestMapping("/infoRelease/delete" )
	public Result delete(@RequestBody(required = false) @Valid EnterpriseInfoVo enterpriseInfoVo,
			@RequestHeader(value = "openId", required = true) String openId) {
		
		//企业负责人维护-负责人删除
		return enterpriseDirectorService.delete(enterpriseInfoVo,openId);

	}
}
