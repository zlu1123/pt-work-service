 package com.lsg.controller;

import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
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
import com.lsg.service.PositionApplyService;
import com.lsg.vo.PostionInfoVo;

@RestController
@RequestMapping("/postionApply" )
public class PositionApplyController {
	
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
    private PositionApplyService managePostionService;	
	
	/**
	   *职位申请-申请人列表
	 * */
	@ResponseBody
	@RequestMapping("/applyList" )
	public Result applyList(@RequestBody(required = false) @Valid PostionInfoVo applyInfoVo,
			@RequestHeader(value = "openId", required = false) String openId) {
		//职位申请-申请人列表
		return managePostionService.applyList(applyInfoVo,openId);

	}
	
	/**
	   *职位申请-申请人审核
	 * */
	@ResponseBody
	@RequestMapping("/applyExam" )
	public Result applyExam(@RequestBody(required = false) @Valid PostionInfoVo applyInfoVo ) {
		
		if(StringUtils.isBlank(applyInfoVo.getPostionApplyId())) {
			return Result.error("职位申请ID不能为空");
		}
		//职位申请-申请人列表
		try {
			return managePostionService.applyExam(applyInfoVo);
		} catch (BusinessException e) {
			return Result.error();
		}

	}
	
	/**
	   *职位申请-申请通过信息发布
	 * */
	@ResponseBody
	@RequestMapping("/applyPush" )
	public Result applyPush(@RequestBody(required = false) @Valid PostionInfoVo applyInfoVo,
			@RequestHeader(value = "openId", required = true) String openId) {
		//职位申请-申请通过信息发布
		return managePostionService.applyPush(applyInfoVo,openId);

	}
	
}
