package com.lsg.service.impl;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lsg.common.ComonUtil;
import com.lsg.entity.OmsMerchInfoPo;
import com.lsg.entity.OmsMerchToUserInofPo;
import com.lsg.entity.OmsPostionInfoPo;
import com.lsg.entity.WpaUserInfo;
import com.lsg.exception.BusinessException;
import com.lsg.mapper.MerchInfoReleaseMapper;
import com.lsg.mapper.MerchToUserInfoMapper;
import com.lsg.mapper.PostionInfoMapper;
import com.lsg.mapper.UserInfoMapper;
import com.lsg.model.Result;
import com.lsg.service.PositionReleaseService;
import com.lsg.vo.PostionInfoVo;

@Service 
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
public class PositionReleaseServiceServiceImpl implements PositionReleaseService{
	
	
	
	@Autowired
	private UserInfoMapper userInfoMapper;
	
	@Autowired
	private MerchToUserInfoMapper merchToUserInfoMapper;
	
	@Autowired
	private MerchInfoReleaseMapper merchInfoReleaseMapper;
	
	@Autowired
	private PostionInfoMapper postionInfoMapper;
	@Override
	public Result insert(PostionInfoVo PostionInfoVo, String openId) throws BusinessException {
		// TODO Auto-generated method stub
		//通过openId 缓存中获取用户ID
		String userId = openId;
		
		//查询用户信息 是否是企业类型
		WpaUserInfo po = new WpaUserInfo();
		po.setUserId(userId);
		
		po = userInfoMapper.selectOne(po);
		
		if(null ==po) {
			throw new BusinessException("用户信息不存在");
		}else {
			if(!"03".equals(po.getUserType())) {
				throw new BusinessException("非企业负责人不能发布职位");
			}
		}
		
		//根据负责人人用户ID查询企业ID
		OmsMerchToUserInofPo merchInfo = queryMerchInfo(userId);
		//创建职位ID、
		String positionId = ComonUtil.createPositionId(merchInfo.getMerchId());
		
		//发布职位信息
		insertPostionInfo(PostionInfoVo,merchInfo.getMerchId(),positionId,userId);
		
		
		return Result.success();
	}

	private void insertPostionInfo(PostionInfoVo vo,String merchId, String positionId, String userId) 
			throws BusinessException {
		//查询企业信息
		OmsMerchInfoPo merchInfoPo = new OmsMerchInfoPo();
		merchInfoPo.setMerchId(merchId);
		
		merchInfoPo = merchInfoReleaseMapper.selectOne(merchInfoPo);
		if(null==merchInfoPo) {
			throw new BusinessException("企业信息不存在");
		}
		
		//插入职位信息
		OmsPostionInfoPo po = new OmsPostionInfoPo();
		
		po.setPostionId(positionId);
		po.setPostionName(vo.getPostionName());
		po.setPostionAddr(vo.getPostionAddr());
		po.setPostionWelfare(vo.getPostionWelfare());
		po.setPostionRequire(vo.getPostionRequire());
		po.setWorkTime(vo.getWorkTime());
		po.setPrice(vo.getPrice());
		po.setPriceUnit(vo.getPriceUnit());
		po.setBilltype(vo.getBilltype());
		po.setPositiondes(vo.getPositiondes());
		po.setInsurance(vo.getInsurance());
		po.setMargin(vo.getMargin());
		po.setHealth(vo.getHealth());
		po.setReleasEmerch(merchId);
		po.setReleasEmerchName(merchInfoPo.getMerchName());
		po.setOperNo(userId);
		po.setModifyTime(new Date());
		po.setCreateTime(new Date());
		
		
		int num = postionInfoMapper.insert(po);
		
		if(num != 1) {
			throw new BusinessException("数据库插入数量不符");
		}
	}

	private OmsMerchToUserInofPo queryMerchInfo(String userId) throws BusinessException {
		
		OmsMerchToUserInofPo po = new OmsMerchToUserInofPo();
		po.setMerchChargeId(userId);
		
		po = merchToUserInfoMapper.selectOne(po);
		if(null==po) {
			throw new BusinessException("企业负责人公司信息不存在");
		}
		
		
		return po;
		
		
		
	}

	@Override
	public Result update(PostionInfoVo PostionInfoVo, String openId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result delete(PostionInfoVo PostionInfoVo, String openId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result page(PostionInfoVo PostionInfoVo, String openId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result positionInfo(PostionInfoVo PostionInfoVo, String openId) throws BusinessException {

		if(StringUtils.isBlank(PostionInfoVo.getPostionId())) {
			throw new BusinessException("职位ID不能为空");
		}
		OmsPostionInfoPo po = new OmsPostionInfoPo();
		
		po.setPostionId(PostionInfoVo.getPostionId());
		
		po = postionInfoMapper.selectOne(po);
		
		return Result.success(po);
	}
	
	




}
