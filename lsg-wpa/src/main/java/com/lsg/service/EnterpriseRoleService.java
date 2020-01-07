package com.lsg.service;


import javax.validation.Valid;

import com.lsg.exception.BusinessException;
import com.lsg.model.Result;
import com.lsg.vo.AttendanceRecordVo;

public interface EnterpriseRoleService {
	
	
	Result punchCardRecord(AttendanceRecordVo vo, String userId);
	
	Result punchCardRecordExam(AttendanceRecordVo vo,String userId ) throws BusinessException;

	Result exemWork(@Valid AttendanceRecordVo vo, String userId) throws BusinessException;

}
