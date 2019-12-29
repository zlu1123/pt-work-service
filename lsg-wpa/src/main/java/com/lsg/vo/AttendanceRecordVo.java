package com.lsg.vo;

import javax.validation.constraints.NotBlank;

public class AttendanceRecordVo {
	

	private String clockType;
	
	private String clockAddr;

	private String postionId;

	private String merchId;

	private String currentDay;
	
	private String postionApplyId;

	public String getClockType() {
		return clockType;
	}
	
	public void setClockType(String clockType) {
		this.clockType = clockType;
	}

	public String getClockAddr() {
		return clockAddr;
	}

	public void setClockAddr(String clockAddr) {
		this.clockAddr = clockAddr;
	}

	public String getPostionId() {
		return postionId;
	}

	public void setPostionId(String postionId) {
		this.postionId = postionId;
	}

	public String getMerchId() {
		return merchId;
	}

	public void setMerchId(String merchId) {
		this.merchId = merchId;
	}

	public String getCurrentDay() {
		return currentDay;
	}

	public void setCurrentDay(String currentDay) {
		this.currentDay = currentDay;
	}

	public String getPostionApplyId() {
		return postionApplyId;
	}

	public void setPostionApplyId(String postionApplyId) {
		this.postionApplyId = postionApplyId;
	}
	
	
	
}
