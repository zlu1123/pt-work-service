package com.lsg.entity;

import java.io.Serializable;

import javax.persistence.Table;

@Table(name = "t_oms_user_pwd_info")
public class OmsUserPwdInfoPo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	//alias
	public static final String TABLE_ALIAS = "OmsUserPwdInfo";
	public static final String ALIAS_USER_ID = "用户ID";
	public static final String ALIAS_PWD = "密码";
	public static final String ALIAS_PWD_STAT = "密码状态";
	public static final String ALIAS_PWD_ERROR_NUM = "密码错误次数";
	public static final String ALIAS_CREATE_TIME = "创建时间";
	public static final String ALIAS_MODIFY_TIME = "更新时间";
	public static final String ALIAS_REMARK1 = "备用1";
	

	//columns START
    /**
     * 用户ID       db_column: USER_ID 
     */ 	
	private java.lang.String userId;
    /**
     * 密码       db_column: PWD 
     */ 	
	private java.lang.String pwd;
    /**
     * 密码状态       db_column: PWD_STAT 
     */ 	
	private java.lang.String pwdStat;
    /**
     * 密码错误次数       db_column: PWD_ERROR_NUM 
     */ 	
	private java.lang.Integer pwdErrorNum;
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
	
	
	public java.lang.String getPwd() {
		return this.pwd;
	}
	
	public void setPwd(java.lang.String value) {
		this.pwd = value;
	}
	
	
	public java.lang.String getPwdStat() {
		return this.pwdStat;
	}
	
	public void setPwdStat(java.lang.String value) {
		this.pwdStat = value;
	}
	
	
	public java.lang.Integer getPwdErrorNum() {
		return this.pwdErrorNum;
	}
	
	public void setPwdErrorNum(java.lang.Integer value) {
		this.pwdErrorNum = value;
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

