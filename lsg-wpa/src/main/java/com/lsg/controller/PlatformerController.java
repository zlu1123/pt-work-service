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
import com.lsg.service.PlatformUserService;
import com.lsg.vo.AttendanceRecordVo;
import com.lsg.vo.PlatformInfoVo;
import com.lsg.vo.PostionInfoVo;

@RestController
@RequestMapping("/platformer" )
public class PlatformerController {

	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
    private PlatformUserService platformUserService;	
	
	
	
	/**
	   * 查看务工人员打卡记录
	 * */
	@ResponseBody
	@RequestMapping("/punchCardRecord" )
	public Result punchCardRecord(@RequestBody(required = false) @Valid AttendanceRecordVo vo,
			@RequestHeader(value = "openId", required = true) String openId) {
		

		String userId = openId;
		return platformUserService.punchCardRecord(vo,userId);

    }
	
	
	/**
	   * 查看务工人员打卡记录
	 * @throws BusinessException 
	 * */
	@ResponseBody
	@RequestMapping("/punchCardRecordExam" )
	public Result punchCardRecordExam(@RequestBody(required = false) @Valid AttendanceRecordVo vo,
            @RequestHeader(value = "openId", required = true) String openId) throws BusinessException {
		
	  String userId = openId;
	  return platformUserService.punchCardRecordExam(vo,userId);

	}
	
	
	
	/**
	   * 企业发布职位审核
	 * @throws BusinessException 
	 * */
	@ResponseBody
	@RequestMapping("/enterpriseReleaseExam" )
	public Result enterpriseReleaseExam(@RequestBody(required = false) @Valid PostionInfoVo vo) throws BusinessException {
		
	  // 企业发布职位审核
	  return platformUserService.punchCardRecordExam(vo);

	}
	
	

	/**
	   * 平台负责人/财务人员维护
	 * @throws BusinessException 
	 * */
	@ResponseBody
	@RequestMapping("/insert" )
	public Result insert(@RequestBody(required = false) @Valid PlatformInfoVo vo) throws BusinessException {
		
	  //平台负责人/财务人员维护
	  return platformUserService.insert(vo);

	}
	
	
	
}
