package com.lsg.entity;

import java.io.Serializable;

import javax.persistence.Table;


@Table(name = "t_oms_role_info")
public class OmsUserRolePo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	//alias
	public static final String TABLE_ALIAS = "OmsUserRole";
	public static final String ALIAS_USER_ID = "用户号";
	public static final String ALIAS_ROLE_ID = "角色ID";
	public static final String ALIAS_REMARK1 = "备用1";
	

	//columns START
    /**
     * 用户号       db_column: USER_ID 
     */ 	
	private java.lang.String userId;
    /**
     * 角色ID       db_column: ROLE_ID 
     */ 	
	private java.lang.String roleId;
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
	
	
	public java.lang.String getRoleId() {
		return this.roleId;
	}
	
	public void setRoleId(java.lang.String value) {
		this.roleId = value;
	}
	
	
	public java.lang.String getRemark1() {
		return this.remark1;
	}
	
	public void setRemark1(java.lang.String value) {
		this.remark1 = value;
	}
	

	

}

