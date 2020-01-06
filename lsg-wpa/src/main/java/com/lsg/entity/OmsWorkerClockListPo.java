package com.lsg.entity;

import java.io.Serializable;

import javax.persistence.Table;

@Table(name = "t_oms_worker_clock_list")
public class OmsWorkerClockListPo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	//alias
	public static final String TABLE_ALIAS = "OmsWorkerClockList";
	public static final String ALIAS_USER_ID = "职位申请人ID";
	public static final String ALIAS_POSTION_ID = "职位Id";
	public static final String ALIAS_MERCH_ID = "企业ID";
	public static final String ALIAS_CLOCK_TYPE = "打卡类型 1-签到 2-签退";
	public static final String ALIAS_CLOCK_ADDR = "打开地点";
	public static final String ALIAS_CLOCK_TIME = "更新时间";
	public static final String ALIAS_CREATE_TIME = "创建时间";
	public static final String ALIAS_REMARK1 = "备用1";
	

	//columns START
    /**
     * 职位申请人ID       db_column: USER_ID 
     */ 	
	private java.lang.String userId;
    /**
     * 打卡类型 1-签到 2-签退       db_column: clock_type 
     */ 	
	private java.lang.String clockType;
    /**
     * 打开地点       db_column: clock_addr 
     */ 	
	private java.lang.String clockAddr;
    /**
     * 更新时间       db_column: clock_time 
     */ 	
	private java.util.Date clockTime;
    /**
     * 创建时间       db_column: CREATE_TIME 
     */ 	
	private java.util.Date createTime;
    /**
     * 备用1       db_column: REMARK1 
     */ 	
	private java.lang.String remark1;
	//columns END
	
    /**
     * 申请职位Id       db_column: POSTION_ID 
     */ 	
	private java.lang.String postionId;
    /**
     * 企业ID       db_column: MERCH_ID 
     */ 	
	private java.lang.String merchId;


	private String postionApplyId;
	
	public java.lang.String getUserId() {
		return this.userId;
	}
	
	public void setUserId(java.lang.String value) {
		this.userId = value;
	}
	
	
	public java.lang.String getClockType() {
		return this.clockType;
	}
	
	public void setClockType(java.lang.String value) {
		this.clockType = value;
	}
	
	
	public java.lang.String getClockAddr() {
		return this.clockAddr;
	}
	
	public void setClockAddr(java.lang.String value) {
		this.clockAddr = value;
	}
	
	
	public java.util.Date getClockTime() {
		return this.clockTime;
	}
	
	public void setClockTime(java.util.Date value) {
		this.clockTime = value;
	}
	
	
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	
	
	public java.lang.String getRemark1() {
		return this.remark1;
	}
	
	public void setRemark1(java.lang.String value) {
		this.remark1 = value;
	}

	public String getPostionApplyId() {
		return postionApplyId;
	}

	public void setPostionApplyId(String postionApplyId) {
		this.postionApplyId = postionApplyId;
	}

	public java.lang.String getPostionId() {
		return postionId;
	}

	public void setPostionId(java.lang.String postionId) {
		this.postionId = postionId;
	}

	public java.lang.String getMerchId() {
		return merchId;
	}

	public void setMerchId(java.lang.String merchId) {
		this.merchId = merchId;
	}
	
}

