package com.lsg.service;


import com.lsg.exception.BusinessException;
import com.lsg.model.Result;
import com.lsg.vo.AttendanceRecordVo;

public interface EnterpriseRoleService {
	
	
	Result punchCardRecord(AttendanceRecordVo vo, String userId);
	
	Result punchCardRecordExam(AttendanceRecordVo vo,String userId ) throws BusinessException;

}
