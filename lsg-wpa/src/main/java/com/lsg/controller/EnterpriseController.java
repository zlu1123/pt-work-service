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
import com.lsg.service.EnterpriseRoleService;
import com.lsg.vo.AttendanceRecordVo;

 

@RestController
@RequestMapping("/enterpise" )
public class EnterpriseController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
    private EnterpriseRoleService enterpriseRoleService;	
	
	/**
	   * 查看务工人员打卡记录
	 * */
	@ResponseBody
	@RequestMapping("/punchCardRecord" )
	public Result punchCardRecord(@RequestBody(required = false) @Valid AttendanceRecordVo vo,
			@RequestHeader(value = "openId", required = true) String openId) {
		
		String userId= openId;
		return enterpriseRoleService.punchCardRecord(vo,userId);

  }
	
	
	/**
	   * 审核务工人员打卡记录
	 * @throws BusinessException 
	 * */
	@ResponseBody
	@RequestMapping("/punchCardRecordExam" )
	public Result punchCardRecordExam(@RequestBody(required = false) @Valid AttendanceRecordVo vo,
          @RequestHeader(value = "openId", required = true) String openId) throws BusinessException {
		
			  String userId = openId;
        	  return enterpriseRoleService.punchCardRecordExam(vo,userId);

	}
	
	
	
}
