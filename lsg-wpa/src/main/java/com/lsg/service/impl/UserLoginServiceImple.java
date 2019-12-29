package com.lsg.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lsg.common.OrderNumberGeneratorClient;
import com.lsg.entity.WpaUserInfo;
import com.lsg.mapper.UserInfoMapper;
import com.lsg.model.Result;
import com.lsg.service.UserLoginService;

@Service
public class UserLoginServiceImple implements UserLoginService{
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private UserInfoMapper userInfoMapper;
	
	@Override
	public Result getUserById(String id) {
		
		//1.查询用户信息
		WpaUserInfo wpaUserInfo = new WpaUserInfo();
		wpaUserInfo.setOpenId(id);
		wpaUserInfo = userInfoMapper.selectOne(wpaUserInfo);
				
		//2.1首次登录
		if(null == wpaUserInfo) {
			//插入用户信息
			 /* 生产用户ID */
			 String userId = createUserId();
			 WpaUserInfo wpaUserInfo1 = new WpaUserInfo();
	      
	         wpaUserInfo1.setOpenId(id);
	         wpaUserInfo1.setUserId(userId);
	         wpaUserInfo1.setUserType("01");
	         wpaUserInfo1.setMainMobile("123123123123");
	         wpaUserInfo1.setUserStat("01");
	         wpaUserInfo1.setUserLevel("01");
	         wpaUserInfo1.setIsCert("0");
	         wpaUserInfo1.setIsHealth("0");
	         wpaUserInfo1.setLogoAddr("");
	         wpaUserInfo1.setIdentImageAddr("");
	         wpaUserInfo1.setUserSex("1");
	         wpaUserInfo1.setUserName("tset");
	         
	         userInfoMapper.insert(wpaUserInfo1);
	         
	         return Result.success(wpaUserInfo1);
		}
		//2.2不是首次登录
		else {
			return Result.success(wpaUserInfo);
		}
		
	}

	private String createUserId() {
			logger.debug("=============createUserId begin=============");
	        OrderNumberGeneratorClient worker = new OrderNumberGeneratorClient();
	        String regDateStr = (new SimpleDateFormat("yyyyMMdd")).format(new Date());
	        String userId = new StringBuffer(regDateStr).append(worker.getSerialOrderNumber(8)).toString();
	        logger.debug("userId=" + userId);
	        logger.debug("=============createUserId end=============");
	        return userId;
		
	}

}
