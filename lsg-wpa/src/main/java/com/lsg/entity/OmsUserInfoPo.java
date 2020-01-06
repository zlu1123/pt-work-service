package com.lsg.entity;

import java.io.Serializable;

import javax.persistence.Table;



@Table(name = "t_oms_user_info")
public class OmsUserInfoPo  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	//alias
	public static final String TABLE_ALIAS = "OmsUserInfo";
	public static final String ALIAS_LOGIN_NO = "登陆账号";
	public static final String ALIAS_USER_TYPE = "用户类型";
	public static final String ALIAS_PWD = "密码";
	public static final String ALIAS_USER_STAT = "用户状态";
	public static final String ALIAS_REMARK1 = "备用1";
	

	//columns START
    /**
     * 登陆账号       db_column: LOGIN_NO 
     */ 	
	private java.lang.String loginNo;
    /**
     * 用户类型       db_column: USER_TYPE 
     */ 	
	private java.lang.String userType;
    /**
     * 密码       db_column: PWD 
     */ 	
	private java.lang.String pwd;
    /**
     * 用户状态       db_column: USER_STAT 
     */ 	
	private java.lang.String userStat;
    /**
     * 备用1       db_column: REMARK1 
     */ 	
	private java.lang.String remark1;
	//columns END


	
	
	public java.lang.String getLoginNo() {
		return this.loginNo;
	}
	
	public void setLoginNo(java.lang.String value) {
		this.loginNo = value;
	}
	
	
	public java.lang.String getUserType() {
		return this.userType;
	}
	
	public void setUserType(java.lang.String value) {
		this.userType = value;
	}
	
	
	public java.lang.String getPwd() {
		return this.pwd;
	}
	
	public void setPwd(java.lang.String value) {
		this.pwd = value;
	}
	
	
	public java.lang.String getUserStat() {
		return this.userStat;
	}
	
	public void setUserStat(java.lang.String value) {
		this.userStat = value;
	}
	
	
	public java.lang.String getRemark1() {
		return this.remark1;
	}
	
	public void setRemark1(java.lang.String value) {
		this.remark1 = value;
	}
	

	

}

