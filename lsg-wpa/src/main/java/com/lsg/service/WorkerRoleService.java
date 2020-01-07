package com.lsg.service;

import javax.validation.Valid;

import com.lsg.exception.BusinessException;
import com.lsg.model.Result;
import com.lsg.vo.AttendanceRecordVo;
import com.lsg.vo.PostionInfoVo;
import com.lsg.vo.UserInfoVo;
import com.lsg.vo.WagePayRollVo;

public interface WorkerRoleService {
	
	Result enRoll(PostionInfoVo postionInfoVo,String openId) throws Exception;
	
	Result queryEmployInfo(String openId);
	
	Result disRoll(PostionInfoVo postionInfoVo,String openId) throws Exception;
	
	Result queryBillInfo(String openId);
	
	Result queryClockIn(String openId);
	
	Result userInfo(String openId);
	
	Result userInfoMatn(UserInfoVo userInfovo,String openId);
	
	Result payRollInfo(String openId);
	
	Result getPayRoll(WagePayRollVo wagePayRollVo,String openId)throws Exception;
	
	Result usrIdtfyCert(String certNo,String custName,String openId);
	
	Result healthCert(String fileImageAddr,String openId);
	
	Result defaultRecords(String optType,String openId);
	
	Result clockInOrSignOut(AttendanceRecordVo attendanceRecordVo,String openId) throws Exception;
	
	Result queryCurrentDayClock(AttendanceRecordVo attendanceRecordVo,String openId) throws Exception;

	Result cardQuery(@Valid UserInfoVo userInfoVo, String openId);

	Result cardAdd(@Valid UserInfoVo userInfoVo, String openId) throws BusinessException;

}	
