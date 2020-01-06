package com.lsg.service;


import javax.validation.Valid;

import com.lsg.exception.BusinessException;
import com.lsg.model.Result;
import com.lsg.vo.AttendanceRecordVo;
import com.lsg.vo.PlatformInfoVo;
import com.lsg.vo.PostionInfoVo;

public interface PlatformUserService {
	
	
	Result punchCardRecord(AttendanceRecordVo vo, String userId);
	
	Result punchCardRecordExam(AttendanceRecordVo vo,String openId ) throws BusinessException;

	Result insert(PlatformInfoVo vo) throws BusinessException;

	Result punchCardRecordExam(@Valid PostionInfoVo vo) throws BusinessException;

}
