package com.lsg.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.lsg.entity.OmsPostionApplyInfoPo;
import com.lsg.entity.OmsPostionInfoPo;
import com.lsg.entity.OmsWorkExamInfoPo;
import com.lsg.entity.OmsWorkWageListPo;
import com.lsg.entity.OmsWorkerClockListPo;
import com.lsg.entity.WpaUserAcclistPo;
import com.lsg.exception.BusinessException;
import com.lsg.mapper.PostionApplyInfoMapper;
import com.lsg.mapper.PostionInfoMapper;
import com.lsg.mapper.UserAcclistMapper;
import com.lsg.mapper.WorkClockListMapper;
import com.lsg.mapper.WorkeWageListMapper;
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
	WorkeWageListMapper workeWageListMapper;
	
	
	@Autowired
	WorkerExemMapper workerExemMapper;
	

	@Autowired
	PostionInfoMapper postionInfoMapper;
	
	@Autowired
	UserAcclistMapper userAcclistMapper;

	
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
		
		//查询职位需求多少人，校验最多录用人数
		OmsPostionInfoPo po1 = new OmsPostionInfoPo();
		po1.setPostionId(po.getPostionId());
		po1 = postionInfoMapper.selectOne(po1);
		
		if(null == po1) {
			throw new BusinessException("职位信息不存在");
		} 
		int workCount = Integer.parseInt(po1.getWorkCount());
		
		OmsPostionApplyInfoPo po2 = new OmsPostionApplyInfoPo();
		
		po2.setMerchId(po.getMerchId());
		po2.setPostionId(po.getPostionId());
		int selectCount = postionApplyInfoMapper.selectCount(po2);
		
		if(selectCount == workCount) {
			throw new BusinessException("职位录用人数已满");
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
			//查询发放工资金额
			OmsWorkWageListPo po = new OmsWorkWageListPo();
			po.setPostionApplyId(wagePayRollVo.getPostionApplyId());
			po = workeWageListMapper.selectOne(po);
			
			if(null == po) {
				throw new BusinessException("工资信息不存在");
			}
			
			
			BigDecimal wageAmt = new BigDecimal(po.getWageAmt());
			String cardNo = wagePayRollVo.getCardNo();
			String applyId = "";
			String cardName = wagePayRollVo.getCardName();
			String bankCode = wagePayRollVo.getBankCode();
			String merchId = "";
			String mchKey = "";
			try {
				Map<String, String> wxCashBank = WeiXinUtil.wxCashBank(applyId, cardNo, cardName, bankCode, wageAmt, merchId, mchKey);
				
				Map<String,Object> map = new HashMap<String,Object>();
				//更新员工工资发放记录
				map.put("postionApplyId", wagePayRollVo.getPostionApplyId());
				map.put("acctNo", cardNo);	
				map.put("wageAmt", wageAmt+"");
				map.put("txnSeq", UUID.randomUUID()+"");
				map.put("modifyTime", new Date());
				if(wxCashBank.get("result_code").equals("SUCCESS")) {
					map.put("txnStat", "01");//提现成功
				}else {
					map.put("txnStat", "02");//提现成功
				}
				int num = workeWageListMapper.updateWageList(map);
				
				if(num!=1) {
					throw new BusinessException("数据库更新数量不符");
				}
				
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
		
		List<Map<String,Object>> out = new ArrayList<Map<String,Object>>();
		
		if(null!=list&&list.size()>0) {
			for(OmsPostionApplyInfoPo po1 : list) {
				OmsPostionInfoPo postion = new OmsPostionInfoPo();
				Map<String,Object> map = new HashMap<String, Object>();
				postion.setPostionId(po1.getPostionId());
				
				postion = postionInfoMapper.selectOne(postion);
				
				map.put("postionApplyId", po1.getPostionApplyId());
				map.put("merchId", postion.getReleasEmerch());
				map.put("merchName", postion.getReleasEmerchName());
				map.put("postionId", po1.getPostionId());
				map.put("postionId", postion.getPostionName());
				map.put("clockBeginDate", postion.getClockBeginDate());
				map.put("clockEndDate", postion.getClockEndDate());
				
				out.add(map);
			}
				
			
			
			return Result.success(out);
		}
		return Result.success(out);
		
		
		
	}

	@Override
	public Result cardQuery(@Valid UserInfoVo userInfoVo, String userId) {
		
		WpaUserAcclistPo po = new WpaUserAcclistPo();
		po.setUserId(userId);
		
		po = userAcclistMapper.selectOne(po);
		return Result.success(po);
	}

	@Override
	public Result cardAdd(@Valid UserInfoVo vo, String userId) throws BusinessException {
		// TODO Auto-generated method stub
		WpaUserAcclistPo po = new WpaUserAcclistPo();
		po.setUserId(userId);
		po.setAcctNo(vo.getCardNo());
		po.setAcctName(vo.getCardName());
		po.setAcctType("1");
		po.setModifyTime(new Date());
		po.setCreateTime(new Date());
		
		int num = userAcclistMapper.insert(po);
		
		if(num!=1) {
			throw new BusinessException("数据库更新数量不符");
		}
		
		return Result.success();
	}




}
