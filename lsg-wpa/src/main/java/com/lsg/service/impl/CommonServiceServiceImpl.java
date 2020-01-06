package com.lsg.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.lsg.mapper.WpaUserInfoMapper;
import com.lsg.model.Result;
import com.lsg.service.CommonService;
import com.lsg.service.PositionReleaseService;
import com.lsg.vo.CommonVo;
import com.lsg.vo.PostionInfoVo;

@Service 
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
public class CommonServiceServiceImpl implements CommonService{

	
	@Autowired
	private PostionInfoMapper postionInfoMapper;
	
	
	
	@Override
	public Result advert(String openId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result queryPosition(CommonVo commonVo, String openId) {

		OmsPostionInfoPo po = new OmsPostionInfoPo();
		po.setReleasEmerchName(commonVo.getMerchName());
		
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

	@Override
	public Result positionInfo(CommonVo commonVo, String openId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result suggestions(CommonVo commonVo, String openId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	
	




}
