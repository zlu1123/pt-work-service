package com.lsg.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
import com.lsg.entity.OmsWorkerClockListPo;
import com.lsg.exception.BusinessException;
import com.lsg.mapper.PostionApplyInfoMapper;
import com.lsg.mapper.WorkClockListMapper;
import com.lsg.mapper.WorkerExemMapper;
import com.lsg.model.Result;
import com.lsg.service.WorkerRoleService;
import com.lsg.utils.wechat.RSAUtils;
import com.lsg.vo.AttendanceRecordVo;
import com.lsg.vo.PostionInfoVo;
import com.lsg.vo.UserInfoVo;
import com.lsg.vo.WagePayRollVo;
import com.lsg.wechatPay.WeiXinUtil;

@Service 
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
public class WorkerRoleServiceImpl implements WorkerRoleService{
	
	
	@Autowired
	private PostionApplyInfoMapper postionApplyInfoMapper;
	
	@Autowired
	WorkClockListMapper workClockListMapper;
	
	
	@Autowired
	WorkerExemMapper workerExemMapper;
	



	
	@Override
	public Result enRoll(PostionInfoVo postionInfoVo, String openId) throws Exception {
		
		//通过token获取UserId
		String userId = openId;
		
		//参数检查
		paramCheck(postionInfoVo);
		
		//查询是否有其他报名
		OmsPostionApplyInfoPo po = new OmsPostionApplyInfoPo();
		
		po.setApplyUserId(userId);
		po.setMerchId(po.getMerchId());
		po.setPostionId(po.getPostionId());
		po = postionApplyInfoMapper.selectOne(po);
		
		if(null!=po) {
			return Result.error("您已经申请了该职位");
		}
//		List<OmsPostionApplyInfoPo> list  = postionApplyInfoMapper.select(po);
//		
//		if(null != list && list.size() > 0) {
//			for(OmsPostionApplyInfoPo po1 : list) {
//				 //判断是否存在已录用信息
//				 if("3".equals(po1.getExemStat())) {
//					 return Result.error("您处于录用状态");
//				 }
//				 //同一职位同一公司 职能报名一次
//				 if(postionInfoVo.getPostionId().equals(po1.getPostionId())) {
//					 if(postionInfoVo.getMerchId().equals(po1.getMerchId())) {
//						 return Result.error("您在此公司，已经申请了这个职位了");
//					 }
//				 }
//			}
//		}
		//登记务工人员报名信息
		insertPostionApplyInfo(postionInfoVo,userId);
		
		return Result.success();
	}

	private String creatsPostionApplyId(PostionInfoVo postionInfoVo, String userId) {
		
		//String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
		String postionApplyId = postionInfoVo.getMerchId()+postionInfoVo.getPostionId();
		
		return postionApplyId;
	}

	private void insertPostionApplyInfo(PostionInfoVo postionInfoVo,String userId) {
		
		//生成职位申请ID
		String postionApplyId = creatsPostionApplyId(postionInfoVo,userId);
		
		OmsPostionApplyInfoPo po = new OmsPostionApplyInfoPo();
		
		po.setApplyUserId(userId);
		po.setMerchId(postionInfoVo.getMerchId());
		po.setPostionId(postionInfoVo.getPostionId());
		po.setPostionApplyId(postionApplyId);
		po.setExemStat("1");
		po.setCreateTime(new Date());
		po.setModifyTime(new Date());
		
		postionApplyInfoMapper.insert(po);
		
	}


	private Result paramCheck(PostionInfoVo postionInfoVo) {
		String merchId  = postionInfoVo.getMerchId();
		
		String postionId = postionInfoVo.getPostionId();
		
		if(StringUtils.isEmpty(merchId)) {
			return Result.error("企业名称不能为空");
		}
		if(StringUtils.isEmpty(postionId)) {
			return Result.error("职位信息");
		}
		return null;
	}

	@Override
	public Result queryEmployInfo(String openId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result disRoll(PostionInfoVo postionInfoVo, String openId) throws Exception{
		//通过token获取UserId
		String userId = openId;
		
		OmsPostionApplyInfoPo po = new OmsPostionApplyInfoPo();
		
		po.setPostionApplyId(postionInfoVo.getPostionApplyId());
		
		po = postionApplyInfoMapper.selectOne(po);
		
		if(null==po) {
			throw new BusinessException("用户信息不存在");
		}
		
		//删除报名信息
		int num = postionApplyInfoMapper.delete(po);
		
		if(num != 1) {
			return Result.error("数据库删除数量不符");
		}
		
		//如果已通过什么 删除审核信息
		if("2".equals(po.getExemStat())) {
			OmsWorkExamInfoPo po1 = new OmsWorkExamInfoPo();
			po1.setPostionApplyId(postionInfoVo.getPostionApplyId());
			
			//删除审核信息
			int num1 = workerExemMapper.delete(po1);
			
			if(num1 != 1) {
				return Result.error("数据库删除数量不符");
			}
		}
		
		
		
		
		
		
		return Result.success();
	}

	@Override
	public Result queryBillInfo(String openId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result queryClockIn(String openId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result userInfo(String openId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result userInfoMatn(UserInfoVo userInfovo, String openId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result payRollInfo(String openId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result usrIdtfyCert(String certNo, String custName, String openId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result healthCert(String fileImageAddr, String openId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result defaultRecords(String optType, String openId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result clockInOrSignOut(AttendanceRecordVo attendanceRecordVo, String openId) throws Exception{
		
		checkparm(attendanceRecordVo);
		
		String userId = openId;
		
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		map.put("postionApplyId", attendanceRecordVo.getPostionApplyId());
		map.put("currentDay", attendanceRecordVo.getCurrentDay());
		map.put("clockType", attendanceRecordVo.getClockType());
		OmsWorkerClockListPo queryCurrentDayClock = workClockListMapper.queryCurrentDayClock(map);
		
		if(null!=queryCurrentDayClock ) {
			map.put("clockTime", new Date());
			
			int num = workClockListMapper.updateClockTime(map);
			if(num != 1) {
				throw new BusinessException("数据库更新数据不符");
			}
		}else {
			OmsWorkerClockListPo po = new OmsWorkerClockListPo();
			
			po.setUserId(userId);
			po.setPostionApplyId(attendanceRecordVo.getPostionApplyId());
			po.setClockType(attendanceRecordVo.getClockType());
			po.setClockAddr(attendanceRecordVo.getClockAddr());
			po.setMerchId(attendanceRecordVo.getMerchId());
			po.setPostionId(attendanceRecordVo.getPostionId());
			po.setClockTime(new Date());
			po.setCreateTime(new Date());
			
			int num = workClockListMapper.insert(po);
			
			if(num != 1) {
				throw new BusinessException("数据库插入数据不符");
			}
			return Result.success(po);
		}
		return null;
		
	
	}


	private void checkparm(AttendanceRecordVo attendanceRecordVo) throws BusinessException {

		
		if(Strings.isBlank(attendanceRecordVo.getClockType())) {
			throw new BusinessException("打卡类型不能为空");
		}
		
	}

	@Override
	public Result getPayRoll(WagePayRollVo wagePayRollVo, String openId) throws BusinessException {
		
		//1：微信转账
		if("1".equals(wagePayRollVo.getPayType())) {
			
		}
		//:2：账号转账
		else if ("2".equals(wagePayRollVo.getPayType())) {
			Map<String,String> map = new HashMap<String,String>();
			RSAUtils rsaUtils = new RSAUtils();
			try {
				WeiXinUtil weiXinUtil = new WeiXinUtil();
				weiXinUtil.wxCashBank(openId, openId, openId, openId, null, openId, openId);
//				map.put("mch_id", "1305638280");
//				map.put("partner_trade_no", "mch_id"+WXPayUtil.generateNonceStr());
//				map.put("nonce_str", WXPayUtil.generateNonceStr());
//				map.put("enc_bank_no", rsaUtils.encryptData("6226200107123722"));
//				map.put("enc_bank_no", rsaUtils.encryptData("张三"));
//				map.put("bank_code", "1006");
//				map.put("amount", "1");
//				map.put("desc", "转账");
				
				
//				map.put("sign", WXPayUtil.generateSignature(map, map.get("mch_id"), WXPayConfig.SIGN_TYPE));
//				String xmlWithdrawInfo = wxWithdrawTools.getXMLStringForObj(map);
//		        String response = wxWithdrawTools.postParamesForUrl(xmlWithdrawInfo, WXWithdrawTools.WITHDRAW);
//		        System.out.println(response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			throw new BusinessException ("不支持其他类型转账");
		}
		
		
		
		
		
		return null;
	}

	@Override
	public Result queryCurrentDayClock(AttendanceRecordVo attendanceRecordVo, String openId) throws Exception {
		String userId = openId;
		
		//查询用户是否有申请通过的职位
		OmsPostionApplyInfoPo po =  new OmsPostionApplyInfoPo();
		po.setApplyUserId(userId);
		po.setExemStat("2");
		
		List<OmsPostionApplyInfoPo> list = postionApplyInfoMapper.select(po);
		
		//List<Map<String,Object>> out = new ArrayList<Map<String,Object>>();
		
		if(null!=list) {
			
			//查询是否已经签到/签退.若已经签到或签退，
			//则返回对应结果。
			//每天只能签到或签退一次
		
			/*Map<String,Object> map = new HashMap<String,Object>();
			
			map.put("userId", userId);
			map.put("currentDay", attendanceRecordVo.getCurrentDay());
			List<OmsWorkerClockListPo> queryCurrentDayClock = workClockListMapper.queryCurrentDayClock(map);
			
			for(OmsWorkerClockListPo list : queryCurrentDayClock) {
				Map<String,Object> map1 = new HashMap<String,Object>();
				map1.put("postionApplyId",po.getPostionApplyId());
				map1.put("clockInfo", list);
				out.add(map1);
			}*/
			
			return Result.success(list);
		}
		return Result.success(null);
		
		
		
	}




}
