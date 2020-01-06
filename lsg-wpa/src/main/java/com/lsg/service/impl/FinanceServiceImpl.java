package com.lsg.service.impl;



import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lsg.entity.OmsWorkExamInfoPo;
import com.lsg.exception.BusinessException;
import com.lsg.mapper.WorkerExemMapper;
import com.lsg.model.Result;
import com.lsg.service.FinanceRoleSevice;
import com.lsg.vo.FinanceVo;

@Service 
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
public class FinanceServiceImpl implements FinanceRoleSevice{

	@Autowired
	private WorkerExemMapper workerExemMapper;
	
	
	@Override
	public Result audit(FinanceVo vo) throws BusinessException {
		
		//职位申请ID不能为空
		if(Strings.isBlank(vo.getPostionApplyId())) {
			throw new BusinessException("职位申请ID不能为空");
		}
		
		//审核劳工工作
		auditWorker(vo);
		
		//登记工资发放登记簿
		insertWorkWageList(vo);
		return Result.success();
		
	}

	private void insertWorkWageList(FinanceVo vo) {
		// TODO Auto-generated method stub
		
	}

	private void auditWorker(FinanceVo vo) throws BusinessException {
		auditWorker(vo);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("postionApplyId", vo.getPostionApplyId());
		map.put("workerUserId", vo.getWorkerUserId());
		map.put("financeExamStat", vo.getAuditStat());
		map.put("financeOperNo", vo.getFinancer());
		
		int num = workerExemMapper.updateWorkerExemStat(map);
		
		if(num != 1) {
			throw new BusinessException("数据库更新数量不符");
		}
		
	}

	@Override
	public Result page(FinanceVo financeVo) {
		
		OmsWorkExamInfoPo po = new OmsWorkExamInfoPo();
		
		po.setMerchExamStat("02");//企业已审核
		
		List<OmsWorkExamInfoPo> outlist = workerExemMapper.select(po);
		
		return Result.success(outlist);
	}
	
	



}
