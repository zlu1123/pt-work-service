package com.lsg.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.lsg.entity.OmsPostionApplyInfoPo;
import com.lsg.entity.OmsWorkExamInfoPo;
import com.lsg.exception.BusinessException;
import com.lsg.mapper.PostionApplyInfoMapper;
import com.lsg.mapper.WorkerExemMapper;
import com.lsg.model.Result;
import com.lsg.service.PositionManageService;
import com.lsg.vo.PositionApplyInfoVo;

@Service 
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
public class PositionManageServiceServiceImpl implements PositionManageService{
	
	
	@Autowired
	private PostionApplyInfoMapper postionApplyInfoMapper;
	
	@Autowired
	WorkerExemMapper workerExemMapper;

	@Override
	public Result insert(PositionApplyInfoVo applyInfoVo, String openId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result update(PositionApplyInfoVo applyInfoVo, String openId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result delete(PositionApplyInfoVo applyInfoVo, String openId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result page(PositionApplyInfoVo applyInfoVo, String openId) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Result applyExam(PositionApplyInfoVo applyInfoVo, String openId) throws BusinessException {
		
		//获取userId
		String userId = openId;
		
		//校验职位申请信息
		OmsPostionApplyInfoPo po = new OmsPostionApplyInfoPo();
		po.setPostionApplyId(applyInfoVo.getPostionApplyId());
		
		po = postionApplyInfoMapper.selectOne(po);
		
		if(null == po) {
			Result.error("职位申请信息不存在");
		}else {
			if(!"1".equals(po.getExemStat())) {
				Result.error("职位申请状态异常");
			}
		}
		
		//审核 职位申请
		exemPostionApply(applyInfoVo,userId);
		
		//插入职位信息到务工审核表
		insertWorkExamInfo(applyInfoVo,userId);
		return Result.success();
	}

	private void insertWorkExamInfo(PositionApplyInfoVo applyInfoVo, String userId) throws BusinessException {
		OmsWorkExamInfoPo po = new OmsWorkExamInfoPo();
		
		po.setUserId(userId);
		po.setMerchId(applyInfoVo.getMerchId());
		po.setPostionId(applyInfoVo.getPostionId());
		po.setPostionApplyId(applyInfoVo.getPostionApplyId());
		po.setPlatformExamStat1("0");//待审核
		po.setMerchExamStat("0");//待审核
		po.setFinanceExamStat("0");//待审核
		po.setCreateTime(new Date());
		po.setModifyTime(new Date());
		
		int num = workerExemMapper.insert(po);
		

		if(num != 1) {
			throw new BusinessException("数据库插入数量不符");
		}
	}

	private void exemPostionApply(PositionApplyInfoVo applyInfoVo, String userId) throws BusinessException {

		Map<String,Object> map = new HashMap<String,Object>();
		
		map.put("exemNo", userId);
		map.put("exemStat", "2");
		map.put("postionApplyId", applyInfoVo.getPostionApplyId());
		
		int num = postionApplyInfoMapper.updateExemStat(map);
		if(num == 0) {
			throw new BusinessException("数据库未更新");
		}
		
	}

	@Override
	public Result applyPush(PositionApplyInfoVo applyInfoVo, String openId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result applyList(PositionApplyInfoVo applyInfoVo, String openId) {
		Integer pageNum = null;
        Integer pageSize = null;
        if(null!=applyInfoVo){
            pageNum = applyInfoVo.getPageNum();
            pageSize =applyInfoVo.getPageSize();
        }
		//根据查询条件查询职位申请列表
        OmsPostionApplyInfoPo po = new OmsPostionApplyInfoPo();
        po.setPostionId(applyInfoVo.getPostionId());
        po.setMerchId(applyInfoVo.getMerchId());
        po.setExemStat("1");
        
        List<OmsPostionApplyInfoPo> list = postionApplyInfoMapper.select(po);
        
        
		return Result.success(list);
	}
	
	




}
