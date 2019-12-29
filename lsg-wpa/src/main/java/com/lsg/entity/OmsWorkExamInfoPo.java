package com.lsg.entity;

import java.io.Serializable;

import javax.persistence.Table;

@Table(name = "t_oms_work_exam_info")
public class OmsWorkExamInfoPo  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	//alias
	public static final String TABLE_ALIAS = "OmsWorkExamInfo";
	public static final String ALIAS_USER_ID = "用户ID";
	public static final String ALIAS_POSTION_ID = "职位名称";
	public static final String ALIAS_MERCH_ID = "企业ID";
	public static final String ALIAS_POSTION_APPLY_ID = "职位申请码";
	public static final String ALIAS_PLATFORM_EXAM_STAT1 = "平台审核状态1";
	public static final String ALIAS_PLATFOR_MCHARGE1 = "平台负责人1";
	public static final String ALIAS_MERCH_CHARGE = "企业负责人";
	public static final String ALIAS_MERCHEXAM_STAT = "企业审核状态";
	public static final String ALIAS_FINANCE_OPERNO = "财务操作员";
	public static final String ALIAS_FINANCE_EXAM_STAT = "财务审核状态";
	public static final String ALIAS_CREATE_TIME = "创建时间";
	public static final String ALIAS_MODIFY_TIME = "更新时间";
	public static final String ALIAS_REMARK1 = "备用1";
	

	//columns START
    /**
     * 用户ID       db_column: USER_ID 
     */ 	
	private java.lang.String userId;
    /**
     * 职位名称       db_column: POSTION_ID 
     */ 	
	private java.lang.String postionId;
    /**
     * 企业ID       db_column: MERCH_ID 
     */ 	
	private java.lang.String merchId;
	
	private java.lang.String postionApplyId;
    /**
     * 平台审核状态1       db_column: platform_exam_stat1 
     */ 	
	private java.lang.String platformExamStat1;
    /**
     * 平台负责人1       db_column: PLATFOR_MCHARGE1 
     */ 	
	private java.lang.String platforMcharge1;
    /**
     * 企业负责人       db_column: MERCH_CHARGE 
     */ 	
	private java.lang.String merchCharge;
    /**
     * 企业审核状态       db_column: MERCH_exam_stat 
     */ 	
	private java.lang.String merchExamStat;
    /**
     * 财务操作员       db_column: finance_OPER_NO 
     */ 	
	private java.lang.String financeOperNo;
    /**
     * 财务审核状态       db_column: finance_exam_stat 
     */ 	
	private java.lang.String financeExamStat;
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
	
	
	
	
	public java.lang.String getPlatformExamStat1() {
		return this.platformExamStat1;
	}
	
	public void setPlatformExamStat1(java.lang.String value) {
		this.platformExamStat1 = value;
	}
	
	
	public java.lang.String getPlatforMcharge1() {
		return this.platforMcharge1;
	}
	
	public void setPlatforMcharge1(java.lang.String value) {
		this.platforMcharge1 = value;
	}
	
	
	public java.lang.String getMerchCharge() {
		return this.merchCharge;
	}
	
	public void setMerchCharge(java.lang.String value) {
		this.merchCharge = value;
	}
	
	
	public java.lang.String getMerchExamStat() {
		return this.merchExamStat;
	}
	
	public void setMerchExamStat(java.lang.String value) {
		this.merchExamStat = value;
	}
	
	
	public java.lang.String getFinanceOperNo() {
		return this.financeOperNo;
	}
	
	public void setFinanceOperNo(java.lang.String value) {
		this.financeOperNo = value;
	}
	
	
	public java.lang.String getFinanceExamStat() {
		return this.financeExamStat;
	}
	
	public void setFinanceExamStat(java.lang.String value) {
		this.financeExamStat = value;
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

