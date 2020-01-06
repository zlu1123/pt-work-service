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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lsg.common.ComonUtil;
import com.lsg.model.Result;
import com.lsg.service.WorkerRoleService;
import com.lsg.vo.AttendanceRecordVo;
import com.lsg.vo.PostionInfoVo;
import com.lsg.vo.UserInfoVo;
import com.lsg.vo.WagePayRollVo;

import tk.mybatis.mapper.util.StringUtil;

@RestController
@RequestMapping("/worker" )
public class WorkerController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
    private WorkerRoleService workerRoleService;	
	
	
	
	/**
	 *职位报名
	 * @throws Exception 
	 * */
	@ResponseBody
	@RequestMapping("/position/enRoll" )
    public Result enRoll(@RequestBody(required = false) @Valid PostionInfoVo postionInfoVo,
            @RequestHeader(value = "openId", required = true) String openId) throws Exception {
        
		
		
		logger.info("职位报名开始");
	//	ComonUtil.printlnRequest(postionInfoVo,openId);
		//职位详报名
		Result out = workerRoleService.enRoll(postionInfoVo, openId);
	//	ComonUtil.printlnResponse(out);
		logger.info("职位报名开始结束");
		return out;

    }
	
	

	/**
	 *报名取消
	 * @throws Exception 
	 * */
	@ResponseBody
	@RequestMapping("/operation/disRoll" )
    public Result disRoll(@RequestBody(required = false) @Valid PostionInfoVo postionInfoVo,
            @RequestHeader(value = "openId", required = true) String openId) throws Exception {
        
		//报名取消
        return workerRoleService.disRoll(postionInfoVo, openId);

    }
	
	/**
	 *结算信息
	 * */
	@ResponseBody
	@RequestMapping("/operation/queryBillInfo" )
    public Result queryBillInfo(@RequestHeader(value = "openId", required = true) String openId) {
        
		//职位详报名
        return workerRoleService.queryBillInfo(openId);

    }
	
	

	/**
	 *结算信息打卡记录
	 * */
	@ResponseBody
	@RequestMapping("/operation/queryClockIn" )
    public Result queryClockIn(@RequestHeader(value = "openId", required = true) String openId) {
        
		//结算信息查询
        return workerRoleService.queryClockIn(openId);

    }
	
	/**
	 *个人信息
	 * */
	@ResponseBody
	@RequestMapping("/mine/userInfo" )
    public Result userInfo(@RequestHeader(value = "openId", required = true) String openId) {
        
		//个人信息
        return workerRoleService.userInfo(openId);

    }
	
	
	/**
	 *个人信息维护
	 * */
	@ResponseBody
	@RequestMapping("/mine/userInfoMatn" )
    public Result userInfoMatn(@RequestBody(required = false) @Valid UserInfoVo userInfovo,
    		@RequestHeader(value = "openId", required = true) String openId) {
        
		//个人信息维护
        return workerRoleService.userInfoMatn(userInfovo,openId);

    }
	
	/**
	 *工资单
	 * */
	@ResponseBody
	@RequestMapping("/mine/payRollInfo" )
    public Result payRollInfo(@RequestHeader(value = "openId", required = true) String openId) {
        
		//工资单
        return workerRoleService.payRollInfo(openId);

    }
	
	
	/**
	 *工资发放
	 * */
	@ResponseBody
	@RequestMapping("/mine/GetPayRoll" )
    public Result getPayRoll(@RequestBody(required = false) @Valid WagePayRollVo wagePayRollVo,
    		@RequestHeader(value = "openId", required = true) String openId) {
        
		if(StringUtils.isBlank(wagePayRollVo.getPostionApplyId())){
			Result.error("职位申请ID不能为空");
		}
		//工资发放
        try {
			return workerRoleService.getPayRoll(wagePayRollVo ,openId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

    }
	
	
	/**
	 *实名认证
	 * */
	@ResponseBody
	@RequestMapping("/mine/usrIdtfyCert" )
    public Result usrIdtfyCert(@RequestBody(required = true) @Valid String certNo,String custName,
    		@RequestHeader(value = "openId", required = true) String openId) {
        
		//工资发放
        return workerRoleService.usrIdtfyCert(certNo,custName,openId);

    }
	
	/**
	 *健康证认证
	 * */
	@ResponseBody
	@RequestMapping("/mine/healthCert" )
    public Result healthCert(@RequestBody(required = true) @Valid String fileImageAddr,
    		@RequestHeader(value = "openId", required = true) String openId) {
        
		//健康证认证
        return workerRoleService.healthCert(fileImageAddr,openId);

    }
	
	/**
	 *违约记录/违约处理
	 * */
	@ResponseBody
	@RequestMapping("/mine/defaultRecords" )
    public Result defaultRecords(@RequestBody(required = true) @Valid String optType,
    		@RequestHeader(value = "openId", required = true) String openId) {
        
		//违约记录/违约处理
        return workerRoleService.defaultRecords(optType,openId);

    }
	
	/**
	 *签到/签退
	 * */
	@ResponseBody
	@RequestMapping("/mine/clockInOrSignOut" )
    public Result clockInOrSignOut(@RequestBody(required = true) @Valid AttendanceRecordVo attendanceRecordVo,
    		@RequestHeader(value = "openId", required = true) String openId) throws Exception {
        
		logger.info("签到/签退：clockInOrSignOut开始");
		
		Result out = workerRoleService.clockInOrSignOut(attendanceRecordVo,openId);
		
		logger.info("签到/签退：clockInOrSignOut结束");
        return out;
        
       
    }
	
	
	/**
	 *当前签到查询
	 * */
	@ResponseBody
	@RequestMapping("/mine/queryCurrentDayClock" )
    public Result queryCurrentDayClock(@RequestBody @Valid AttendanceRecordVo attendanceRecordVo,
    		@RequestHeader(value = "openId", required = true) String openId) throws Exception {
        
		logger.info("当前签到查询：queryCurrentDayClock开始");
		
		Result out = workerRoleService.queryCurrentDayClock(attendanceRecordVo,openId);
		
		logger.info("当前签到查询：queryCurrentDayClock结束");
        return out;
        
       
    }
	
	
}
