package com.lsg.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lsg.common.ComonUtil;
import com.lsg.entity.OmsMerchInfoPo;
import com.lsg.entity.OmsPostionInfoPo;
import com.lsg.exception.BusinessException;
import com.lsg.mapper.MerchInfoReleaseMapper;
import com.lsg.mapper.MerchToUserInfoMapper;
import com.lsg.mapper.PostionInfoMapper;
import com.lsg.mapper.WpaUserInfoMapper;
import com.lsg.model.Result;
import com.lsg.service.PositionReleaseService;
import com.lsg.vo.PostionInfoVo;


@Service 
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
public class PositionReleaseServiceServiceImpl implements PositionReleaseService{
	
	
	
	@Autowired
	private WpaUserInfoMapper userInfoMapper;
	
	@Autowired
	private MerchToUserInfoMapper merchToUserInfoMapper;
	
	@Autowired
	private MerchInfoReleaseMapper merchInfoReleaseMapper;
	
	@Autowired
	private PostionInfoMapper postionInfoMapper;
	@Override
	public Result insert(PostionInfoVo PostionInfoVo) throws BusinessException {
		// TODO Auto-generated method stub
		//通过openId 缓存中获取用户ID
		String merchId = PostionInfoVo.getMerchId();
		
		//根据负责人人用户ID查询企业ID
		//OmsMerchToUserInofPo merchInfo = queryMerchInfo(userId);
		//创建职位ID、
		String positionId = ComonUtil.createPositionId(merchId);
		
		//发布职位信息
		insertPostionInfo(PostionInfoVo,merchId,positionId,merchId);
		
		
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
		po.setPostionStat("01");
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
		po.setMerchCharge(vo.getMerchCharge());
		po.setOperNo(userId);
		po.setModifyTime(new Date());
		po.setCreateTime(new Date());
		
		
		int num = postionInfoMapper.insert(po);
		
		if(num != 1) {
			throw new BusinessException("数据库插入数量不符");
		}
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
	public Result positionInfo(PostionInfoVo PostionInfoVo, String openId) throws BusinessException {

		if(StringUtils.isBlank(PostionInfoVo.getPostionId())) {
			throw new BusinessException("职位ID不能为空");
		}
		OmsPostionInfoPo po = new OmsPostionInfoPo();
		
		po.setPostionId(PostionInfoVo.getPostionId());
		
		po = postionInfoMapper.selectOne(po);
		
		return Result.success(po);
	}

	@Override
	public Result page(PostionInfoVo vo) throws BusinessException {

		if(Strings.isBlank(vo.getMerchId())) {
			throw new BusinessException("公司ID不能为空");
		}
		
		OmsPostionInfoPo po = new OmsPostionInfoPo();
		po.setReleasEmerch(vo.getMerchId());
		List<OmsPostionInfoPo> list = postionInfoMapper.select(po);	
		
		List<Map<String,Object>> outlist = new ArrayList<Map<String,Object>>();
		for(OmsPostionInfoPo postionPo : list) {
			Map<String,Object> map = new HashMap<String, Object>();
			//map.put("merchId", postionPo.getReleasEmerch());
			map.put("merchName", postionPo.getReleasEmerchName());
			map.put("postionId", postionPo.getPostionId());
			map.put("postionName", postionPo.getPostionName());
			map.put("workTime", postionPo.getWorkTime());
			map.put("billtype", postionPo.getBilltype());
			map.put("price", postionPo.getPrice());
			map.put("priceUnit", postionPo.getPriceUnit());
			
			outlist.add(map);
		}
		
				
				
		return Result.success(list);
	}

	
	

}
