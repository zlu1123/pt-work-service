package com.lsg.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lsg.common.ComonUtil;
import com.lsg.entity.OmsPostionInfoPo;
import com.lsg.entity.OmsUserPwdInfoPo;
import com.lsg.entity.OmsWorkerClockListPo;
import com.lsg.entity.WpaUserInfo;
import com.lsg.exception.BusinessException;
import com.lsg.mapper.PostionInfoMapper;
import com.lsg.mapper.UserPwdInfoMapper;
import com.lsg.mapper.WorkClockListMapper;
import com.lsg.mapper.WorkerExemMapper;
import com.lsg.mapper.WpaUserInfoMapper;
import com.lsg.model.Result;
import com.lsg.service.PlatformUserService;
import com.lsg.utils.wechat.MD5Utils;
import com.lsg.vo.AttendanceRecordVo;
import com.lsg.vo.PlatformInfoVo;
import com.lsg.vo.PostionInfoVo;

@Service 
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
public class PlatformUserServiceImpl implements PlatformUserService{
 
	
	@Autowired
	private WorkClockListMapper workClockListMapper;
	
	@Autowired
	private PostionInfoMapper postionInfoMapper;
	
	@Autowired
	private WpaUserInfoMapper userInfoMapper;
	

	@Autowired
	private UserPwdInfoMapper userPwdInfoMapper;
	
	
	@Autowired
	private WorkerExemMapper workerExemMapper;

	@Override
	public Result punchCardRecordExam(AttendanceRecordVo vo, String userId) throws BusinessException {
		//职位申请ID不能为空
		if(Strings.isBlank(vo.getPostionApplyId())) {
			throw new BusinessException("职位申请ID不能为空");
		}
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("postionApplyId", vo.getPostionApplyId());
		map.put("workerUserId", vo.getWorkerUserId());
		map.put("platformExamStat1", vo.getExamStat());
		map.put("platforMcharge1", userId);
		
		int num = workerExemMapper.updateWorkerExemStat(map);
		
		if(num != 1) {
			throw new BusinessException("数据库更新数量不符");
			}
			return Result.success();
	}

	@Override
	public Result punchCardRecord(AttendanceRecordVo vo, String userId) {

		//查询负责的职位，通过用户ID。
		OmsPostionInfoPo po = new OmsPostionInfoPo();
		//po.setMerchCharge(userId);
		po.setPlatforMcharge1(userId);
		po.setPlatforMcharge2(userId);
		po.setPlatforMcharge3(userId);
		po.setPlatforMcharge4(userId);
		po.setPostionStat("02");
		List<OmsPostionInfoPo> postionInfolist = postionInfoMapper.select(po);
		
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		if(null!=postionInfolist && postionInfolist.size()>0) {
			for(OmsPostionInfoPo postionPo : postionInfolist) { 
				//通过职位ID，查询此职位的所有打卡记录
				OmsWorkerClockListPo clockListPo = new OmsWorkerClockListPo();
				clockListPo.setPostionId(postionPo.getPostionId());
				List<OmsWorkerClockListPo> clockList = workClockListMapper.select(clockListPo);
				
				if(null!=clockList && clockList.size()>0) {
					//返回打卡记录，以职位ID 作为key
					Map<String,Object> map = new HashMap<String, Object>();
					map.put(postionPo.getPostionId(), clockList);
					list.add(map);
				}else {
					break;
				}
			}
		}
		
		
		return Result.success(list);
	}

	@Override
	public Result punchCardRecordExam(@Valid PostionInfoVo vo) throws BusinessException {
		
		//职位ID不能为空
		if(Strings.isBlank(vo.getPostionId())) {
			throw new BusinessException("职位ID不能为空");
		}
		
		Map<String,Object> map = new HashMap<String, Object>();
		//审核通过
		if("02".equals(vo.getPostionStat())) {
			//更新审核状态-通过,添加平台负责人
			map.put("postionStat", vo.getPostionStat());
			map.put("platforMcharge1", vo.getPlatforMcharge1());
			map.put("platforMcharge2", vo.getPlatforMcharge2());
			map.put("platforMcharge3", vo.getPlatforMcharge3());
			map.put("platforMcharge4", vo.getPlatforMcharge4());
		}
		//审核失败
		else {
			//更新审核状态-通过,添加平台负责人
			map.put("postionStat", vo.getPostionStat());
		}
		
		int num  = postionInfoMapper.updatePostionStat(map);
		
		if(num != 1) {
			throw new BusinessException("数据库更新数量不符");
		}
		
		
		return Result.success(vo.getPostionStat());
	}

	@Override
	public Result insert(PlatformInfoVo vo) throws BusinessException {
		
		if(Strings.isBlank(vo.getCertNo())) {
			throw new BusinessException("身份证信息不能为空");
		}
		
		if(Strings.isBlank(vo.getCustName())) {
			throw new BusinessException("平台负责人姓名不能为空");
		}
		
		//查询企业负责人是否存在
		WpaUserInfo po = new WpaUserInfo();
		po.setCertNo(vo.getCertNo());
		po.setUserType(vo.getUserType());
	
		po = userInfoMapper.selectOne(po);
		if(null!=po) {
			throw new BusinessException("平台负责人信息已存在");
		}
		String userId = ComonUtil.createUserId();
		
		//插入平台负责人信息
		insertUserInfo(vo,userId);
		//维护负责人默认密码  身份证后6位
		insertPwdInfo(vo,userId);
			
		
		return Result.success();
	}
	
	
	


	private void insertPwdInfo(PlatformInfoVo vo, String userId) throws BusinessException {
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

	private void insertUserInfo(PlatformInfoVo vo, String userId) throws BusinessException {
		WpaUserInfo po = new WpaUserInfo();
	     
		po.setUserId(userId);
		po.setUserType(vo.getUserType());
		po.setMainMobile(vo.getMobile());
		po.setUserStat("01");
		po.setUserLevel("01");
		po.setIsCert("1");
		po.setCertNo(vo.getCertNo());
		po.setCustName(vo.getCustName());
		po.setIsHealth("0");
		po.setUserName(vo.getCustName());
		
        int num = userInfoMapper.insert(po);
        
      
        if( num != 1	) {
			throw new BusinessException("数据库插入数量不符");
		}
		
	}


	
	




}
