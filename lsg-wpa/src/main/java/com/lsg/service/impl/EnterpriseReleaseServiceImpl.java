package com.lsg.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lsg.common.ComonUtil;
import com.lsg.entity.OmsMerchInfoPo;
import com.lsg.entity.OmsUserInfoPo;
import com.lsg.entity.OmsUserRolePo;
import com.lsg.exception.BusinessException;
import com.lsg.mapper.MerchInfoReleaseMapper;
import com.lsg.mapper.OmsUserInfoMapper;
import com.lsg.model.Result;
import com.lsg.service.EnterpriseReleaseService;
import com.lsg.utils.wechat.MD5Utils;
import com.lsg.vo.EnterpriseInfoVo;

@Service 
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
public class EnterpriseReleaseServiceImpl implements EnterpriseReleaseService{
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private MerchInfoReleaseMapper merchInfoReleaseMapper;
	
	@Autowired
	private OmsUserInfoMapper omsUserInfoMapper;


	@Override
	public Result insert(EnterpriseInfoVo vo) throws BusinessException {
		
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
		po.setOperNo(vo.getOperNo());
		
		po.setModifyTime(new Date());
		po.setCreateTime(new Date());
		
		int num = merchInfoReleaseMapper.insert(po);
		
		if(num != 1	) {
			throw new BusinessException("数据库插入数量不符");
		}
		
		//创建密码信息
		insertOmsUserPwd(vo,merchId);
		
		return Result.success(po);
	}

	private void insertOmsUserPwd(EnterpriseInfoVo vo, String merchId) throws BusinessException {
		OmsUserInfoPo po = new OmsUserInfoPo();
		
		po.setLoginNo(merchId);
		po.setUserType("03");
		po.setPwd(MD5Utils.getMD5("123456"));
		po.setUserStat("01");
		
		int num = omsUserInfoMapper.insert(po);

		if(num != 1	) {
			throw new BusinessException("数据库插入数量不符");
		}
		
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
	public Result page(EnterpriseInfoVo vo) {
		
		//Map<String, Object> map = new HashMap<String, Object>();
		
//		map.put("merchId", vo.getMerchId());
//		map.put("merchName", vo.getMerchName());
		OmsMerchInfoPo po = new OmsMerchInfoPo();
		po.setMerchId(vo.getMerchId());
		po.setMerchName( vo.getMerchName());		
		
		List<OmsMerchInfoPo> list  = merchInfoReleaseMapper.select(po);
		return Result.success(list);
	}
	
	
	
}

