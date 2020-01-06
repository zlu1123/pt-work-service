package com.lsg.service.impl;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lsg.entity.OmsPostionInfoPo;
import com.lsg.entity.OmsWorkExamInfoPo;
import com.lsg.entity.OmsWorkerClockListPo;
import com.lsg.exception.BusinessException;
import com.lsg.mapper.PostionInfoMapper;
import com.lsg.mapper.WorkClockListMapper;
import com.lsg.mapper.WorkerExemMapper;
import com.lsg.model.Result;
import com.lsg.service.EnterpriseRoleService;
import com.lsg.service.PlatformUserService;
import com.lsg.vo.AttendanceRecordVo;
import com.lsg.vo.PostionInfoVo;

@Service 
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
public class EnterpriseServiceImpl implements EnterpriseRoleService{
	
	@Autowired
	private PostionInfoMapper postionInfoMapper;
	
	@Autowired
	private WorkClockListMapper workClockListMapper;
	
	@Autowired
	private WorkerExemMapper workerExemMapper;

	@Override
	public Result punchCardRecord(AttendanceRecordVo vo, String userId) {
		
		//查询负责的职位，通过用户ID。
		OmsPostionInfoPo po = new OmsPostionInfoPo();
		po.setMerchCharge(userId);
		po.setPostionStat(vo.getExamStat()); //01-职位申请 -02申请通过 -03职位申请不通过 -04工作审核通过 -05工作审核不通过
		List<OmsPostionInfoPo> postionInfolist = postionInfoMapper.select(po);
		
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		if(null!=postionInfolist && postionInfolist.size()>0) {
			for(OmsPostionInfoPo postionPo : postionInfolist) { 
				//通过职位ID，查询此职位的所有打卡记录
				OmsWorkerClockListPo clockListPo = new OmsWorkerClockListPo();
				clockListPo.setPostionId(postionPo.getPostionId());
				List<OmsWorkerClockListPo> clockList = workClockListMapper.select(clockListPo);
				
				if(null!=clockList && clockList.size()>0) {
					//返回打卡记录，以职位ID 作为key
					Map<String,Object> map = new HashMap<String, Object>();
					map.put(postionPo.getPostionId(), clockList);
					list.add(map);
				}else {
					break;
				}
			}
		}
		
		
		return Result.success(list);
	}

	@Override
	public Result punchCardRecordExam(AttendanceRecordVo vo, String userId) throws BusinessException {

		//职位申请ID不能为空
		if(Strings.isBlank(vo.getPostionApplyId())) {
			throw new BusinessException("职位申请ID不能为空");
		}
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("postionApplyId", vo.getPostionApplyId());
		map.put("workerUserId", vo.getWorkerUserId());
		map.put("merchExamStat", vo.getExamStat());
		map.put("merchCharge", userId);
		
		int num = workerExemMapper.updateWorkerExemStat(map);
		
		if(num != 1) {
			throw new BusinessException("数据库更新数量不符");
		}
		return Result.success();
	}
	
	
 
	


	
	




}
