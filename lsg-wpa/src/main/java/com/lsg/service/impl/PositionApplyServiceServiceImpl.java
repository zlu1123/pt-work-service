package com.lsg.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.util.Strings;
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
import com.lsg.service.PositionApplyService;
import com.lsg.service.PositionReleaseService;
import com.lsg.vo.PostionInfoVo;

@Service 
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
public class PositionApplyServiceServiceImpl implements PositionApplyService{
	
	
	@Autowired
	private PostionApplyInfoMapper postionApplyInfoMapper;
	
	@Autowired
	WorkerExemMapper workerExemMapper;

	@Override
	public Result insert(PostionInfoVo applyInfoVo, String openId) {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public Result update(PostionInfoVo applyInfoVo, String openId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result delete(PostionInfoVo applyInfoVo, String openId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result page(PostionInfoVo applyInfoVo, String openId) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Result applyExam(PostionInfoVo applyInfoVo) throws BusinessException {
		
		
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
		exemPostionApply(applyInfoVo);
		
		//插入职位信息到务工审核表
		insertWorkExamInfo(po);
		return Result.success();
	}

	private void insertWorkExamInfo(OmsPostionApplyInfoPo po2) throws BusinessException {
		OmsWorkExamInfoPo po = new OmsWorkExamInfoPo();
		
		po.setUserId(po2.getApplyUserId());
		po.setMerchId(po2.getMerchId());
		po.setPostionId(po2.getPostionId());
		po.setPostionApplyId(po2.getPostionApplyId());
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

	private void exemPostionApply(PostionInfoVo applyInfoVo) throws BusinessException {

		Map<String,Object> map = new HashMap<String,Object>();
		
		map.put("exemStat", "2");
		map.put("postionApplyId", applyInfoVo.getPostionApplyId());
		
		int num = postionApplyInfoMapper.updateExemStat(map);
		if(num == 0) {
			throw new BusinessException("数据库未更新");
		}
		
	}

	@Override
	public Result applyPush(PostionInfoVo applyInfoVo, String openId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result applyList(PostionInfoVo applyInfoVo,String openId) {
	
		Integer pageNum = null;
        Integer pageSize = null;
        if(null!=applyInfoVo){
            pageNum = applyInfoVo.getPageNum();
            pageSize =applyInfoVo.getPageSize();
        }
		//根据查询条件查询职位申请列表
        OmsPostionApplyInfoPo po = new OmsPostionApplyInfoPo();
        //po.setPostionId(applyInfoVo.getPostionId());
        //po.setMerchId(applyInfoVo.getMerchId());
        
        
    	
		
		if(!Strings.isBlank(openId)) {
			String userId = openId;
			po.setApplyUserId(userId);
		}
        if(null!=applyInfoVo) {
            po.setExemStat(applyInfoVo.getApplyExemStat());
           // po.setMerchId(applyInfoVo);
        }

        
        List<OmsPostionApplyInfoPo> list = postionApplyInfoMapper.select(po);
        
        
		return Result.success(list);
	}
	
	




}
