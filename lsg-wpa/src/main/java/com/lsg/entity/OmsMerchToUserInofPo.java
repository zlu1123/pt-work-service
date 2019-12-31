package com.lsg.entity;

import java.io.Serializable;

import javax.persistence.Table;

@Table(name = "t_oms_merch_to_user_inof")
public class OmsMerchToUserInofPo  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	//alias
	public static final String TABLE_ALIAS = "OmsMerchToUserInof";
	public static final String ALIAS_MERCH_ID = "企业ID";
	public static final String ALIAS_MERCH_CHARGE = "企业负责人";
	public static final String ALIAS_MERCH_CHARGE_ID = "企业负责人ID";
	public static final String ALIAS_CREATE_TIME = "创建时间";
	public static final String ALIAS_MODIFY_TIME = "更新时间";
	public static final String ALIAS_REMARK1 = "备用1";
	

	//columns START
    /**
     * 企业ID       db_column: MERCH_ID 
     */ 	
	private java.lang.String merchId;
    /**
     * 企业负责人       db_column: MERCH_CHARGE 
     */ 	
	private java.lang.String merchCharge;
    /**
     * 企业负责人ID       db_column: MERCH_CHARGE_ID 
     */ 	
	private java.lang.String merchChargeId;
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


	private java.lang.String loginId;
	
	public java.lang.String getMerchId() {
		return this.merchId;
	}
	
	public void setMerchId(java.lang.String value) {
		this.merchId = value;
	}
	
	
	public java.lang.String getMerchCharge() {
		return this.merchCharge;
	}
	
	public void setMerchCharge(java.lang.String value) {
		this.merchCharge = value;
	}
	
	
	public java.lang.String getMerchChargeId() {
		return this.merchChargeId;
	}
	
	public void setMerchChargeId(java.lang.String value) {
		this.merchChargeId = value;
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

	public java.lang.String getLoginId() {
		return loginId;
	}

	public void setLoginId(java.lang.String loginId) {
		this.loginId = loginId;
	}
	

	

}

