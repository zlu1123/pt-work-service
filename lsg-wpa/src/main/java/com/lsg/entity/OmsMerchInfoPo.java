package com.lsg.entity;

import java.io.Serializable;

import javax.persistence.Table;

@Table(name = "t_oms_merch_info")
public class OmsMerchInfoPo  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	//alias
	public static final String TABLE_ALIAS = "OmsMerchInfo";
	public static final String ALIAS_MERCH_ID = "企业ID";
	public static final String ALIAS_MERCH_NAME = "企业名称";
	public static final String ALIAS_MERCH_IMG = "企业图片";
	public static final String ALIAS_MERCH_INFO = "企业介绍";
	public static final String ALIAS_OPER_NO = "操作员";
	public static final String ALIAS_CREATE_TIME = "创建时间";
	public static final String ALIAS_MODIFY_TIME = "更新时间";
	public static final String ALIAS_REMARK1 = "备用1";
	

	//columns START
    /**
     * 企业ID       db_column: MERCH_ID 
     */ 	
	private java.lang.String merchId;
    /**
     * 企业名称       db_column: MERCH_NAME 
     */ 	
	private java.lang.String merchName;
    /**
     * 企业图片       db_column: MERCH_IMG 
     */ 	
	private java.lang.String merchImg;
    /**
     * 企业介绍       db_column: merch_info 
     */ 	
	private java.lang.String merchInfo;
    /**
     * 企业负责人       db_column: MERCH_CHARGE 
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


	
	
	public java.lang.String getMerchId() {
		return this.merchId;
	}
	
	public void setMerchId(java.lang.String value) {
		this.merchId = value;
	}
	
	
	public java.lang.String getMerchName() {
		return this.merchName;
	}
	
	public void setMerchName(java.lang.String value) {
		this.merchName = value;
	}
	
	
	public java.lang.String getMerchImg() {
		return this.merchImg;
	}
	
	public void setMerchImg(java.lang.String value) {
		this.merchImg = value;
	}
	
	
	public java.lang.String getMerchInfo() {
		return this.merchInfo;
	}
	
	public void setMerchInfo(java.lang.String value) {
		this.merchInfo = value;
	}
	
	
	
	public java.lang.String getOperNo() {
		return operNo;
	}

	public void setOperNo(java.lang.String operNo) {
		this.operNo = operNo;
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

