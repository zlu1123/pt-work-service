package com.lsg.entity;

import java.io.Serializable;

import javax.persistence.Table;

@Table(name = "t_oms_postion_info")
public class OmsPostionInfoPo implements Serializable{
	
private static final long serialVersionUID = 1L;
	
	//alias
	public static final String TABLE_ALIAS = "OmsPostionInfo";
	public static final String ALIAS_POSTION_ID = "职位ID";
	public static final String ALIAS_POSTION_NAME = "职位名称";
	public static final String ALIAS_POSTION_ADDR = "职位工作地点";
	public static final String ALIAS_POSTION_WELFARE = "职位福利";
	public static final String ALIAS_POSTION_REQUIRE = "职位要求";
	public static final String ALIAS_WORK_TIME = "工作时间";
	public static final String ALIAS_PRICE = "单价";
	public static final String ALIAS_PRICE_UNIT = "单位";
	public static final String ALIAS_BILLTYPE = "结算方式";
	public static final String ALIAS_POSITIONDES = "职位详细描述";
	public static final String ALIAS_INSURANCE = "保险选择";
	public static final String ALIAS_MARGIN = "保证金";
	public static final String ALIAS_HEALTH = "健康证选择";
	public static final String ALIAS_RELEAS_EMERCH = "发布企业";
	public static final String ALIAS_PLATFOR_MCHARGE1 = "平台负责人1";
	public static final String ALIAS_PLATFOR_MCHARGE2 = "平台负责人2";
	public static final String ALIAS_PLATFOR_MCHARGE3 = "平台负责人3";
	public static final String ALIAS_PLATFOR_MCHARGE4 = "平台负责人4";
	public static final String ALIAS_MERCH_CHARGE = "企业负责人";
	public static final String ALIAS_OPER_NO = "操作员";
	public static final String ALIAS_CREATE_TIME = "创建时间";
	public static final String ALIAS_MODIFY_TIME = "更新时间";
	public static final String ALIAS_REMARK1 = "备用1";
	

	//columns START
    /**
     * 职位ID       db_column: POSTION_ID 
     */ 	
	private java.lang.String postionId;
    /**
     * 职位名称       db_column: POSTION_NAME 
     */ 	
	private java.lang.String postionName;
    /**
     * 职位工作地点       db_column: POSTION_ADDR 
     */ 	
	private java.lang.String postionAddr;
    /**
     * 职位福利       db_column: POSTION_WELFARE 
     */ 	
	private java.lang.String postionWelfare;
    /**
     * 职位要求       db_column: POSTION_REQUIRE 
     */ 	
	private java.lang.String postionRequire;
    /**
     * 工作时间       db_column: WORK_TIME 
     */ 	
	private java.lang.String workTime;
    /**
     * 单价       db_column: PRICE 
     */ 	
	private java.lang.String price;
    /**
     * 结算方式       db_column: BILLTYPE 
     */ 	
	private java.lang.String billtype;
    /**
     * 职位详细描述       db_column: POSITIONDES 
     */ 	
	private java.lang.String positiondes;
    /**
     * 保险选择       db_column: INSURANCE 
     */ 	
	private java.lang.String insurance;
    /**
     * 保证金       db_column: MARGIN 
     */ 	
	private java.lang.String margin;
    /**
     * 健康证选择       db_column: HEALTH 
     */ 	
	private java.lang.String health;
    /**
     * 发布企业       db_column: RELEAS_EMERCH 
     */ 	
	private java.lang.String releasEmerch;
    /**
     * 平台负责人1       db_column: PLATFOR_MCHARGE1 
     */ 	
	private java.lang.String platforMcharge1;
    /**
     * 平台负责人2       db_column: PLATFOR_MCHARGE2 
     */ 	
	private java.lang.String platforMcharge2;
    /**
     * 平台负责人3       db_column: PLATFOR_MCHARGE3 
     */ 	
	private java.lang.String platforMcharge3;
    /**
     * 平台负责人4       db_column: PLATFOR_MCHARGE4 
     */ 	
	private java.lang.String platforMcharge4;
    /**
     * 企业负责人       db_column: MERCH_CHARGE 
     */ 	
	private java.lang.String merchCharge;
    /**
     * 操作员       db_column: OPER_NO 
     */ 	
	private java.lang.String operNo;
    /**
     * 创建时间       db_column: CREATE_TIME 
     */ 	
	private java.util.Date createTime;
    /**
     * 更新时间       db_column: MODIFY_TIME 
     */ 	
	private java.util.Date modifyTime;
    /**
     * 备用1       db_column: REMARK1 
     */ 	
	private java.lang.String remark1;
	//columns END
	
	private java.lang.String releasEmerchName;
	private java.lang.String releasEmerchAddr;
	private java.lang.String releasEmerchImg;
	
	private java.lang.String postionStat;
	
	private java.lang.String workBeginDate;
	
	private java.lang.String workEndDate;
	
	private java.lang.String clockBeginDate;
	
	private java.lang.String clockEndDate;
	
	private java.lang.String workCount;

	
	
	public java.lang.String getPostionId() {
		return this.postionId;
	}
	
	public void setPostionId(java.lang.String value) {
		this.postionId = value;
	}
	
	
	public java.lang.String getPostionName() {
		return this.postionName;
	}
	
	public void setPostionName(java.lang.String value) {
		this.postionName = value;
	}
	
	
	public java.lang.String getPostionAddr() {
		return this.postionAddr;
	}
	
	public void setPostionAddr(java.lang.String value) {
		this.postionAddr = value;
	}
	
	
	public java.lang.String getPostionWelfare() {
		return this.postionWelfare;
	}
	
	public void setPostionWelfare(java.lang.String value) {
		this.postionWelfare = value;
	}
	
	
	public java.lang.String getPostionRequire() {
		return this.postionRequire;
	}
	
	public void setPostionRequire(java.lang.String value) {
		this.postionRequire = value;
	}
	
	
	public java.lang.String getWorkTime() {
		return this.workTime;
	}
	
	public void setWorkTime(java.lang.String value) {
		this.workTime = value;
	}
	
	
	public java.lang.String getPrice() {
		return this.price;
	}
	
	public void setPrice(java.lang.String value) {
		this.price = value;
	}
	
	
	
	public java.lang.String getBilltype() {
		return this.billtype;
	}
	
	public void setBilltype(java.lang.String value) {
		this.billtype = value;
	}
	
	
	public java.lang.String getPositiondes() {
		return this.positiondes;
	}
	
	public void setPositiondes(java.lang.String value) {
		this.positiondes = value;
	}
	
	
	public java.lang.String getInsurance() {
		return this.insurance;
	}
	
	public void setInsurance(java.lang.String value) {
		this.insurance = value;
	}
	
	
	public java.lang.String getMargin() {
		return this.margin;
	}
	
	public void setMargin(java.lang.String value) {
		this.margin = value;
	}
	
	
	public java.lang.String getHealth() {
		return this.health;
	}
	
	public void setHealth(java.lang.String value) {
		this.health = value;
	}
	
	
	public java.lang.String getReleasEmerch() {
		return this.releasEmerch;
	}
	
	public void setReleasEmerch(java.lang.String value) {
		this.releasEmerch = value;
	}
	
	
	public java.lang.String getPlatforMcharge1() {
		return this.platforMcharge1;
	}
	
	public void setPlatforMcharge1(java.lang.String value) {
		this.platforMcharge1 = value;
	}
	
	
	public java.lang.String getPlatforMcharge2() {
		return this.platforMcharge2;
	}
	
	public void setPlatforMcharge2(java.lang.String value) {
		this.platforMcharge2 = value;
	}
	
	
	public java.lang.String getPlatforMcharge3() {
		return this.platforMcharge3;
	}
	
	public void setPlatforMcharge3(java.lang.String value) {
		this.platforMcharge3 = value;
	}
	
	
	public java.lang.String getPlatforMcharge4() {
		return this.platforMcharge4;
	}
	
	public void setPlatforMcharge4(java.lang.String value) {
		this.platforMcharge4 = value;
	}
	
	
	public java.lang.String getMerchCharge() {
		return this.merchCharge;
	}
	
	public void setMerchCharge(java.lang.String value) {
		this.merchCharge = value;
	}
	
	
	public java.lang.String getOperNo() {
		return this.operNo;
	}
	
	public void setOperNo(java.lang.String value) {
		this.operNo = value;
	}
	
	
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	
	
	public java.util.Date getModifyTime() {
		return this.modifyTime;
	}
	
	public void setModifyTime(java.util.Date value) {
		this.modifyTime = value;
	}
	
	
	public java.lang.String getRemark1() {
		return this.remark1;
	}
	
	public void setRemark1(java.lang.String value) {
		this.remark1 = value;
	}

	public java.lang.String getReleasEmerchName() {
		return releasEmerchName;
	}

	public void setReleasEmerchName(java.lang.String releasEmerchName) {
		this.releasEmerchName = releasEmerchName;
	}

	public java.lang.String getPostionStat() {
		return postionStat;
	}

	public void setPostionStat(java.lang.String postionStat) {
		this.postionStat = postionStat;
	}

	public java.lang.String getWorkBeginDate() {
		return workBeginDate;
	}

	public void setWorkBeginDate(java.lang.String workBeginDate) {
		this.workBeginDate = workBeginDate;
	}

	public java.lang.String getWorkEndDate() {
		return workEndDate;
	}

	public void setWorkEndDate(java.lang.String workEndDate) {
		this.workEndDate = workEndDate;
	}

	public java.lang.String getClockBeginDate() {
		return clockBeginDate;
	}

	public void setClockBeginDate(java.lang.String clockBeginDate) {
		this.clockBeginDate = clockBeginDate;
	}

	public java.lang.String getClockEndDate() {
		return clockEndDate;
	}

	public void setClockEndDate(java.lang.String clockEndDate) {
		this.clockEndDate = clockEndDate;
	}

	public java.lang.String getWorkCount() {
		return workCount;
	}

	public void setWorkCount(java.lang.String workCount) {
		this.workCount = workCount;
	}

	public java.lang.String getReleasEmerchAddr() {
		return releasEmerchAddr;
	}

	public void setReleasEmerchAddr(java.lang.String releasEmerchAddr) {
		this.releasEmerchAddr = releasEmerchAddr;
	}

	public java.lang.String getReleasEmerchImg() {
		return releasEmerchImg;
	}

	public void setReleasEmerchImg(java.lang.String releasEmerchImg) {
		this.releasEmerchImg = releasEmerchImg;
	}
	
}

