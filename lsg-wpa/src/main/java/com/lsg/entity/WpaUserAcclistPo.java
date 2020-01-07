package com.lsg.entity;

import java.io.Serializable;

import javax.persistence.Table;

@Table(name = "t_wpa_user_acclist")
public class WpaUserAcclistPo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	//alias
	public static final String TABLE_ALIAS = "WpaUserAcclist";
	public static final String ALIAS_USER_ID = "用户ID";
	public static final String ALIAS_ACCT_NO = "卡/微信支付号";
	public static final String ALIAS_ACCT_NAME = "户名";
	public static final String ALIAS_ACCT_TYPE = "账户类型 1-卡,2-微信号";
	public static final String ALIAS_CREATE_TIME = "创建时间";
	public static final String ALIAS_MODIFY_TIME = "更新时间";
	public static final String ALIAS_REMARK1 = "备用1";
	

	//columns START
    /**
     * 用户ID       db_column: USER_ID 
     */ 	
	private java.lang.String userId;
    /**
     * 卡/微信支付号       db_column: ACCT_NO 
     */ 	
	private java.lang.String acctNo;
    /**
     * 户名       db_column: ACCT_NAME 
     */ 	
	private java.lang.String acctName;
    /**
     * 账户类型 1-卡,2-微信号       db_column: ACCT_TYPE 
     */ 	
	private java.lang.String acctType;
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


	
	
	public java.lang.String getUserId() {
		return this.userId;
	}
	
	public void setUserId(java.lang.String value) {
		this.userId = value;
	}
	
	
	public java.lang.String getAcctNo() {
		return this.acctNo;
	}
	
	public void setAcctNo(java.lang.String value) {
		this.acctNo = value;
	}
	
	
	public java.lang.String getAcctName() {
		return this.acctName;
	}
	
	public void setAcctName(java.lang.String value) {
		this.acctName = value;
	}
	
	
	public java.lang.String getAcctType() {
		return this.acctType;
	}
	
	public void setAcctType(java.lang.String value) {
		this.acctType = value;
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
	

	

}

