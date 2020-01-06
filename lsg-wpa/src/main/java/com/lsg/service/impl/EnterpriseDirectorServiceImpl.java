package com.lsg.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

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
import com.lsg.entity.OmsMerchToUserInofPo;
import com.lsg.entity.OmsUserPwdInfoPo;
import com.lsg.entity.WpaUserInfo;
import com.lsg.exception.BusinessException;
import com.lsg.mapper.MerchInfoDirectorMapper;
import com.lsg.mapper.WpaUserInfoMapper;
import com.lsg.mapper.UserPwdInfoMapper;
import com.lsg.model.Result;
import com.lsg.service.EnterpriseDirectorService;
import com.lsg.service.EnterpriseReleaseService;
import com.lsg.utils.wechat.MD5Utils;
import com.lsg.vo.EnterpriseInfoVo;

@Service 
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
public class EnterpriseDirectorServiceImpl implements EnterpriseDirectorService{
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private MerchInfoDirectorMapper merchInfoDirectorMapper;

	@Autowired
	private WpaUserInfoMapper userInfoMapper;
	
	@Autowired
	private UserPwdInfoMapper userPwdInfoMapper;
	

	@Override
	public Result insert(EnterpriseInfoVo vo) throws BusinessException {

		//企业ID 不能为空
		if(Strings.isBlank(vo.getMerchId())) {
			throw new BusinessException("企业ID不能为空");
		}
		if(Strings.isBlank(vo.getCertNo())) {
			throw new BusinessException("身份证号码");
		}
		
		if(Strings.isBlank(vo.getMerchChargeName())) {
			throw new BusinessException("负责人姓名");
		}
		//查询企业负责人手否被添加
		
		OmsMerchToUserInofPo po = new OmsMerchToUserInofPo();
		po.setLoginId(vo.getCertNo());
		po.setMerchId(vo.getMerchId());
		
		po = merchInfoDirectorMapper.selectOne(po) ;
		
		if(null !=po) {
			throw new BusinessException("负责人信息已存在");
		}
		String userId = ComonUtil.createUserId();
	
		//插入负责人信息
		insertsMerchToUserInfo(vo,userId);
		//插入用户信息表
		insertUserInfo(vo,userId);
		//维护负责人默认密码  身份证后6位
		insertPwdInfo(vo,userId);
		
		
		return Result.success();
		
	}

	private void insertUserInfo(EnterpriseInfoVo vo, String userId) throws BusinessException {
		
		WpaUserInfo po = new WpaUserInfo();
     
		po.setUserId(userId);
		po.setUserType("03");
		po.setMainMobile(vo.getMobile());
		po.setUserStat("01");
		po.setUserLevel("01");
		po.setIsCert("0");
		po.setIsHealth("0");
		po.setUserName(vo.getMerchChargeName());
		
        int num = userInfoMapper.insert(po);
        
      
        if( num != 1	) {
			throw new BusinessException("数据库插入数量不符");
		}
	}


	private void insertPwdInfo(EnterpriseInfoVo vo, String userId) throws BusinessException {
		
		OmsUserPwdInfoPo po = new OmsUserPwdInfoPo();
		
		po.setUserId(userId);
		po.setPwdErrorNum(5);
		po.setPwdStat("01"); //01 正常 02 锁定
		
		String certNo = vo.getCertNo();
		
		String pwd = MD5Utils.getMD5(certNo.substring(certNo.length()-6));
		po.setPwd(pwd);
		
		int num = userPwdInfoMapper.insert(po);
		
		
		 if( num != 1	) {
			throw new BusinessException("数据库插入数量不符");
		}
	}

	private void insertsMerchToUserInfo(EnterpriseInfoVo vo, String userId) throws BusinessException {
		OmsMerchToUserInofPo po = new OmsMerchToUserInofPo();
		
		po.setMerchCharge(vo.getMerchChargeName());
		po.setMerchChargeId(userId);
		po.setMerchId(vo.getMerchId());
		po.setLoginId(vo.getCertNo());
		po.setModifyTime(new Date());
		po.setCreateTime(new Date());
		
		int num = merchInfoDirectorMapper.insert(po);
		
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
	public Result page(EnterpriseInfoVo vo) throws BusinessException {
		
		if(Strings.isBlank(vo.getMerchId())) {
			throw new BusinessException("企业ID不能为空");
		}
		
		OmsMerchToUserInofPo po = new OmsMerchToUserInofPo();
		po.setMerchId(vo.getMerchId());
		po.setLoginId(vo.getCertNo());
		po.setMerchCharge(vo.getMerchChargeName());
		po.setMerchChargeId(vo.getMerchChargeId());
		
		List<OmsMerchToUserInofPo> list = merchInfoDirectorMapper.select(po) ;
		
		return Result.success(list);
	}

	
	
}

