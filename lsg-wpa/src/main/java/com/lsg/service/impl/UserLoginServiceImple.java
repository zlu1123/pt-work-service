package com.lsg.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lsg.common.BeanUtils;
import com.lsg.common.ComonUtil;
import com.lsg.entity.OmsMerchToUserInofPo;
import com.lsg.entity.OmsUserInfoPo;
import com.lsg.entity.OmsUserPwdInfoPo;
import com.lsg.entity.WpaUserInfo;
import com.lsg.exception.BusinessException;
import com.lsg.mapper.MerchInfoDirectorMapper;
import com.lsg.mapper.OmsUserInfoMapper;
import com.lsg.mapper.WpaUserInfoMapper;
import com.lsg.mapper.UserPwdInfoMapper;
import com.lsg.model.Result;
import com.lsg.service.UserLoginService;
import com.lsg.utils.wechat.MD5Utils;
import com.lsg.vo.UserLoginVo;

@Service
public class UserLoginServiceImple implements UserLoginService{
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private WpaUserInfoMapper userInfoMapper;
	
	@Autowired
	private OmsUserInfoMapper omsUserInfoMapper;
	
	@Autowired
	private MerchInfoDirectorMapper merchInfoDirectorMapper;
	
	@Autowired
	private UserPwdInfoMapper userPwdInfoMapper;
	
	@Override
	public Result UserLogin(UserLoginVo vo,String id) throws BusinessException {
		
		//登陆类型不能为空
		if (Strings.isBlank(vo.getLoginType())) {
			throw new BusinessException("登陆类型不能为空");
		}
		Map<String,Object> map = new HashMap<String,Object>();
		//务工人员登录 01
		if("01".equals(vo.getLoginType())) {
			workerLogin(vo,id,map);
		}
		//企业人员登录 02
		else if("02".equals(vo.getLoginType())) {
			merchLogin(vo,id,map);
		}
		//平台人员登录 03
		else if("03".equals(vo.getLoginType())) {
			
		}else {
			return Result.error("登陆类型不存在");
		}
		return Result.success(map);
		
	}

	private void merchLogin(UserLoginVo vo, String id, Map<String, Object> map) throws BusinessException {
		
		//查询企业用户信息
		OmsMerchToUserInofPo po = new OmsMerchToUserInofPo();
		po.setLoginId(vo.getLoginId());
		
		po = merchInfoDirectorMapper.selectOne(po);
		if(null ==po) {
			throw new BusinessException("用户信息不存在");
		}
		
		String userId = po.getMerchChargeId();
		map.put("merchId", po.getMerchId());
		//查询密码表对比密码
		checkPwd(vo,userId);
		
		//查询用户信息
		WpaUserInfo wpaUserInfo = new WpaUserInfo();
		wpaUserInfo.setUserId(userId);
		
		wpaUserInfo = userInfoMapper.selectOne(wpaUserInfo);
		if(null==wpaUserInfo) {
			throw new BusinessException("用户信息不存在");
		}
		
		//首次登陆绑定与用户ID关系
		if(Strings.isBlank(wpaUserInfo.getOpenId())) {
			//绑定用户ID与OpenId关系。
			bindOpenIdAndUserId(userId,id);
		}
		
	   	map.putAll(BeanUtils.beanConvertToMap(po));
		
	}

	private void bindOpenIdAndUserId(String userId, String id) throws BusinessException {
		
		//绑定openId与userId
	    Map<String,Object> map = new HashMap<String,Object>();
	    map.put("openId", id);
	    map.put("userId", userId);
	    
	    int num = userInfoMapper.updateOpenIdByUserId(map);
	    
	   	if(num!=1) {
	   		throw new BusinessException("数据库更新不符");
	   	}
	}

	private void checkPwd(UserLoginVo vo, String userId) throws BusinessException {
		OmsUserPwdInfoPo po =  new OmsUserPwdInfoPo();
		po.setUserId(userId);
		
		po = userPwdInfoMapper.selectOne(po);
		
		if(null == po) {
			throw new BusinessException("密码信息不存在");
		}
		
		String pwd = MD5Utils.getMD5(vo.getPwd());
		
		if("01".equals(po.getPwdStat())) {
			if(!pwd.equals(po.getPwd())) {
				throw new BusinessException("密码不正确");
			}else {
				//更新错误次数
			}
		}else {
			throw new BusinessException("密码为锁定状态");
		}
		
		
	}

	private void workerLogin(UserLoginVo vo, String id, Map<String, Object> map) {
		//1.查询用户信息
		WpaUserInfo wpaUserInfo = new WpaUserInfo();
		wpaUserInfo.setOpenId(id);
		wpaUserInfo = userInfoMapper.selectOne(wpaUserInfo);
				
		//2.1首次登录
		if(null == wpaUserInfo) {
			//插入用户信息
			 /* 生产用户ID */
			 String userId = ComonUtil.createUserId();
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
	         
	         map.putAll(BeanUtils.beanConvertToMap(wpaUserInfo1));
		}
		//2.2不是首次登录
		else {
			map.putAll(BeanUtils.beanConvertToMap(wpaUserInfo));
		}
		
	}

	@Override
	public Result OmsUserLogin(UserLoginVo vo) throws BusinessException {
		
		//参数检查
		if(	Strings.isBlank(vo.getLoginId())) {
			throw new BusinessException("登录账户不能为空");
		}
		
		if(	Strings.isBlank(vo.getPwd())) {
			throw new BusinessException("登录密码不能为空");
		}
		            
		//查询后管登录账号信息
		OmsUserInfoPo po = new OmsUserInfoPo();
		po.setLoginNo(vo.getLoginId());
		po = omsUserInfoMapper.selectOne(po);
		
		if(null==po) {
			throw new BusinessException("内管用户信息不存在");
		}
		
		String pwd = MD5Utils.getMD5(vo.getPwd());
		
		if(!pwd.equals(po.getPwd())) {
			throw new BusinessException("密码不正确");
		}
		return Result.success(po);
	}
}
