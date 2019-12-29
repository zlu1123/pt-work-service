package com.lsg.entity;

import java.io.Serializable;

import javax.persistence.Table;

@Table(name = "t_wpa_user_info")
public class WpaUserInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//alias
		public static final String TABLE_ALIAS = "WpaUserInfo";
		public static final String ALIAS_USER_ID = "用户号";
		public static final String ALIAS_OPEN_ID = "APPID";
		public static final String ALIAS_USER_TYPE = "用户类型";
		public static final String ALIAS_MAIN_MOBILE = "手机号";
		public static final String ALIAS_USER_NAME = "用户昵称";
		public static final String ALIAS_USER_STAT = "用户状态";
		public static final String ALIAS_USER_LEVEL = "劳工等级";
		public static final String ALIAS_IS_CERT = "是否实名认证";
		public static final String ALIAS_IS_HEALTH = "是否健康认证";
		public static final String ALIAS_LOGO_ADDR = "头像地址";
		public static final String ALIAS_IDENT_IMAGE_ADDR = "身份证地址";
		public static final String ALIAS_USER_SEX = "性别";
		public static final String ALIAS_BIRTH_DAY = "出生年月日";
		public static final String ALIAS_CERT_NO = "身份证";
		public static final String ALIAS_CUST_NAME = "姓名";
		public static final String ALIAS_REMARK1 = "备用1";
		public static final String ALIAS_REMARK2 = "备用2";
		public static final String ALIAS_REMARK3 = "备用3";
		

		//columns START
	    /**
	     * 用户号       db_column: USER_ID 
	     */ 	
		private java.lang.String userId;
	    /**
	     * APPID       db_column: OPEN_ID 
	     */ 	
		private java.lang.String openId;
	    /**
	     * 用户类型       db_column: USER_TYPE 
	     */ 	
		private java.lang.String userType;
	    /**
	     * 手机号       db_column: MAIN_MOBILE 
	     */ 	
		private java.lang.String mainMobile;
	    /**
	     * 用户昵称       db_column: USER_NAME 
	     */ 	
		private java.lang.String userName;
	    /**
	     * 用户状态       db_column: USER_STAT 
	     */ 	
		private java.lang.String userStat;
	    /**
	     * 劳工等级       db_column: USER_LEVEL 
	     */ 	
		private java.lang.String userLevel;
	    /**
	     * 是否实名认证       db_column: IS_CERT 
	     */ 	
		private java.lang.String isCert;
	    /**
	     * 是否健康认证       db_column: IS_HEALTH 
	     */ 	
		private java.lang.String isHealth;
	    /**
	     * 头像地址       db_column: LOGO_ADDR 
	     */ 	
		private java.lang.String logoAddr;
	    /**
	     * 身份证地址       db_column: IDENT_IMAGE_ADDR 
	     */ 	
		private java.lang.String identImageAddr;
	    /**
	     * 性别       db_column: USER_SEX 
	     */ 	
		private java.lang.String userSex;
	    /**
	     * 出生年月日       db_column: BIRTH_DAY 
	     */ 	
		private java.util.Date birthDay;
	    /**
	     * 身份证       db_column: CERT_NO 
	     */ 	
		private java.lang.String certNo;
	    /**
	     * 姓名       db_column: CUST_NAME 
	     */ 	
		private java.lang.String custName;
	    /**
	     * 备用1       db_column: REMARK1 
	     */ 	
		private java.lang.String remark1;
	    /**
	     * 备用2       db_column: REMARK2 
	     */ 	
		private java.lang.String remark2;
	    /**
	     * 备用3       db_column: REMARK3 
	     */ 	
		private java.lang.String remark3;
		//columns END


		
		
		public java.lang.String getUserId() {
			return this.userId;
		}
		
		public void setUserId(java.lang.String value) {
			this.userId = value;
		}
		
		
		public java.lang.String getOpenId() {
			return this.openId;
		}
		
		public void setOpenId(java.lang.String value) {
			this.openId = value;
		}
		
		
		public java.lang.String getUserType() {
			return this.userType;
		}
		
		public void setUserType(java.lang.String value) {
			this.userType = value;
		}
		
		
		public java.lang.String getMainMobile() {
			return this.mainMobile;
		}
		
		public void setMainMobile(java.lang.String value) {
			this.mainMobile = value;
		}
		
		
		public java.lang.String getUserName() {
			return this.userName;
		}
		
		public void setUserName(java.lang.String value) {
			this.userName = value;
		}
		
		
		public java.lang.String getUserStat() {
			return this.userStat;
		}
		
		public void setUserStat(java.lang.String value) {
			this.userStat = value;
		}
		
		
		public java.lang.String getUserLevel() {
			return this.userLevel;
		}
		
		public void setUserLevel(java.lang.String value) {
			this.userLevel = value;
		}
		
		
		public java.lang.String getIsCert() {
			return this.isCert;
		}
		
		public void setIsCert(java.lang.String value) {
			this.isCert = value;
		}
		
		
		public java.lang.String getIsHealth() {
			return this.isHealth;
		}
		
		public void setIsHealth(java.lang.String value) {
			this.isHealth = value;
		}
		
		
		public java.lang.String getLogoAddr() {
			return this.logoAddr;
		}
		
		public void setLogoAddr(java.lang.String value) {
			this.logoAddr = value;
		}
		
		
		public java.lang.String getIdentImageAddr() {
			return this.identImageAddr;
		}
		
		public void setIdentImageAddr(java.lang.String value) {
			this.identImageAddr = value;
		}
		
		
		public java.lang.String getUserSex() {
			return this.userSex;
		}
		
		public void setUserSex(java.lang.String value) {
			this.userSex = value;
		}
		
		
		public java.util.Date getBirthDay() {
			return this.birthDay;
		}
		
		public void setBirthDay(java.util.Date value) {
			this.birthDay = value;
		}
		
		
		public java.lang.String getCertNo() {
			return this.certNo;
		}
		
		public void setCertNo(java.lang.String value) {
			this.certNo = value;
		}
		
		
		public java.lang.String getCustName() {
			return this.custName;
		}
		
		public void setCustName(java.lang.String value) {
			this.custName = value;
		}
		
		
		public java.lang.String getRemark1() {
			return this.remark1;
		}
		
		public void setRemark1(java.lang.String value) {
			this.remark1 = value;
		}
		
		
		public java.lang.String getRemark2() {
			return this.remark2;
		}
		
		public void setRemark2(java.lang.String value) {
			this.remark2 = value;
		}
		
		
		public java.lang.String getRemark3() {
			return this.remark3;
		}
		
		public void setRemark3(java.lang.String value) {
			this.remark3 = value;
		}
		
	
	
}
