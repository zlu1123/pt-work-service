package com.lsg.entity;

import java.io.Serializable;

import javax.persistence.Table;

@Table(name = "t_oms_work_wage_list")
public class OmsWorkWageListPo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	//alias
	public static final String TABLE_ALIAS = "OmsWorkWageList";
	public static final String ALIAS_USER_ID = "用户ID";
	public static final String ALIAS_POSTION_ID = "职位名称";
	public static final String ALIAS_MERCH_ID = "企业ID";
	public static final String ALIAS_FINANCE_OPERNO = "财务操作员";
	public static final String ALIAS_WAGE_AMT = "工资金额";
	public static final String ALIAS_ACCT_NO = "卡/微信支付号";
	public static final String ALIAS_TXN_SEQ = "转账流水";
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
     * 财务操作员       db_column: finance_OPER_NO 
     */ 	
	private java.lang.String financeOperno;
    /**
     * 工资金额       db_column: WAGE_AMT 
     */ 	
	private java.lang.String wageAmt;
    /**
     * 卡/微信支付号       db_column: ACCT_NO 
     */ 	
	private java.lang.String acctNo;
    /**
     * 转账流水       db_column: TXN_SEQ 
     */ 	
	private java.lang.String txnSeq;
    /**
     * 转账转台     db_column: TXN_STAT
     */ 	
	private java.lang.String txnStat;
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
	
	
	public java.lang.String getFinanceOperno() {
		return this.financeOperno;
	}
	
	public void setFinanceOperno(java.lang.String value) {
		this.financeOperno = value;
	}
	
	
	public java.lang.String getWageAmt() {
		return this.wageAmt;
	}
	
	public void setWageAmt(java.lang.String value) {
		this.wageAmt = value;
	}
	
	
	public java.lang.String getAcctNo() {
		return this.acctNo;
	}
	
	public void setAcctNo(java.lang.String value) {
		this.acctNo = value;
	}
	
	
	public java.lang.String getTxnSeq() {
		return this.txnSeq;
	}
	
	public void setTxnSeq(java.lang.String value) {
		this.txnSeq = value;
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

	public java.lang.String getTxnStat() {
		return txnStat;
	}

	public void setTxnStat(java.lang.String txnStat) {
		this.txnStat = txnStat;
	}
	
	
	
}

