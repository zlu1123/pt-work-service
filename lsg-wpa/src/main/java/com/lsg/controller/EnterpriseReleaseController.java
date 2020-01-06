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
import com.lsg.service.EnterpriseReleaseService;
import com.lsg.vo.EnterpriseInfoVo;


@RestController
@RequestMapping("/enterpriseRelease" )
public class EnterpriseReleaseController {
	
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
    private EnterpriseReleaseService enterpriseReleaseService;	
	
	
	/**
	   *企业信息维护-查询企业列表
	 * */
	@ResponseBody
	@RequestMapping("/page" )
	public Result page(@RequestBody(required = false) @Valid EnterpriseInfoVo enterpriseInfoVo) {
		
		//企业信息维护-查询企业列表
		return enterpriseReleaseService.page(enterpriseInfoVo);

	}
	
	/**
	   *企业信息维护-企业新增
	 * @throws BusinessException 
	 * */
	@ResponseBody
	@RequestMapping("/insert" )
	public Result insert(@RequestBody(required = false) @Valid EnterpriseInfoVo enterpriseInfoVo) throws BusinessException {
		
		//企业信息维护-企业新增
		return enterpriseReleaseService.insert(enterpriseInfoVo);

	}
	
	
	/**
	   *企业信息维护-企业更新
	 * */
	@ResponseBody
	@RequestMapping("/update" )
	public Result update(@RequestBody(required = false) @Valid EnterpriseInfoVo enterpriseInfoVo,
			@RequestHeader(value = "openId", required = true) String openId) {
		
		//企业信息维护-企业更新
		return enterpriseReleaseService.update(enterpriseInfoVo,openId);

	}
	
	
	/**
	   *企业信息维护-企业删除
	 * */
	@ResponseBody
	@RequestMapping("/delete" )
	public Result delete(@RequestBody(required = false) @Valid EnterpriseInfoVo enterpriseInfoVo,
			@RequestHeader(value = "openId", required = true) String openId) {
		
		//企业信息维护-企业删除
		return enterpriseReleaseService.delete(enterpriseInfoVo,openId);

	}
	
}
