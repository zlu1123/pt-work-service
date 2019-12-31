package com.lsg.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lsg.common.ComonUtil;
import com.lsg.common.OrderNumberGeneratorClient;
import com.lsg.entity.OmsMerchInfoPo;
import com.lsg.exception.BusinessException;
import com.lsg.mapper.MerchInfoReleaseMapper;
import com.lsg.model.Result;
import com.lsg.service.EnterpriseReleaseService;
import com.lsg.vo.EnterpriseInfoVo;

@Service 
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
public class EnterpriseReleaseServiceImpl implements EnterpriseReleaseService{
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private MerchInfoReleaseMapper merchInfoReleaseMapper;


	@Override
	public Result insert(EnterpriseInfoVo vo, String openId) throws BusinessException {
		
		String userId = openId;
		if(Strings.isBlank(vo.getMerchName())) {
			throw new BusinessException("公司名称不能为空");
		}
		
		//生成企业ID
		String merchId = ComonUtil.createMerchId();
		
		OmsMerchInfoPo po = new OmsMerchInfoPo();
		
		po.setMerchId(merchId);
		po.setMerchName(vo.getMerchName());
		po.setMerchImg(vo.getMerchImg());
		po.setMerchInfo(vo.getMerchInfo());
		po.setOperNo(userId);
		
		po.setModifyTime(new Date());
		po.setCreateTime(new Date());
		
		int num = merchInfoReleaseMapper.insert(po);
		
		if(num != 1	) {
			throw new BusinessException("数据库插入数量不符");
		}
		return Result.success(po);
	}

	@Override
	public Result update(EnterpriseInfoVo enterpriseInfoVo, String openId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result delete(EnterpriseInfoVo enterpriseInfoVo, String openId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Override
	public Result page( String openId) {
		
		
		return null;
	}
	
}

