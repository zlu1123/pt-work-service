package com.lsg.entity;

import java.io.Serializable;

import javax.persistence.Table;


@Table(name = "t_oms_postion_apply_info")
public class OmsPostionApplyInfoPo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	//alias
	public static final String TABLE_ALIAS = "OmsPostionApplyInfo";
	public static final String ALIAS_APPLY_USER_ID = "职位申请人ID";
	public static final String ALIAS_POSTION_ID = "申请职位Id";
	public static final String ALIAS_MERCH_ID = "企业ID";
	public static final String ALIAS_EXEM_NO = "审核员ID";
	public static final String ALIAS_EXEM_STAT = "审核状态";
	public static final String ALIAS_EXEM_MSG = "审核信息";
	public static final String ALIAS_IS_NOTICE = "是否信息发布";
	public static final String ALIAS_CREATE_TIME = "创建时间";
	public static final String ALIAS_MODIFY_TIME = "更新时间";
	public static final String ALIAS_REMARK1 = "备用1";
	

	//columns START
    /**
     * 职位申请人ID       db_column: APPLY_USER_ID 
     */ 	
	private java.lang.String applyUserId;
    /**
     * 申请职位Id       db_column: POSTION_ID 
     */ 	
	private java.lang.String postionId;
    /**
     * 企业ID       db_column: MERCH_ID 
     */ 	
	private java.lang.String merchId;
    /**
     * 审核员ID       db_column: EXEM_NO 
     */ 	
	private java.lang.String exemNo;
    /**
     * 审核状态       db_column: EXEM_STAT 
     */ 	
	private java.lang.String exemStat;
    /**
     * 审核信息       db_column: EXEM_MSG 
     */ 	
	private java.lang.String exemMsg;
    /**
     * 是否信息发布       db_column: IS_NOTICE 
     */ 	
	private java.lang.String isNotice;
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

	/**
     * 职位申请ID
     */ 	
	private java.lang.String postionApplyId;
	
	
	public java.lang.String getApplyUserId() {
		return this.applyUserId;
	}
	
	public void setApplyUserId(java.lang.String value) {
		this.applyUserId = value;
	}
	
	
	public java.lang.String getPostionId() {
		return this.postionId;
	}
	
	public void setPostionId(java.lang.String value) {
		this.postionId = value;
	}
	
	
	public java.lang.String getMerchId() {
		return this.merchId;
	}
	
	public void setMerchId(java.lang.String value) {
		this.merchId = value;
	}
	
	
	public java.lang.String getExemNo() {
		return this.exemNo;
	}
	
	public void setExemNo(java.lang.String value) {
		this.exemNo = value;
	}
	
	
	public java.lang.String getExemStat() {
		return this.exemStat;
	}
	
	public void setExemStat(java.lang.String value) {
		this.exemStat = value;
	}
	
	
	public java.lang.String getExemMsg() {
		return this.exemMsg;
	}
	
	public void setExemMsg(java.lang.String value) {
		this.exemMsg = value;
	}
	
	
	public java.lang.String getIsNotice() {
		return this.isNotice;
	}
	
	public void setIsNotice(java.lang.String value) {
		this.isNotice = value;
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

	public java.lang.String getPostionApplyId() {
		return postionApplyId;
	}

	public void setPostionApplyId(java.lang.String postionApplyId) {
		this.postionApplyId = postionApplyId;
	}
	
	
	

}

