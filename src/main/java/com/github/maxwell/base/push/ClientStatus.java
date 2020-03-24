package com.github.maxwell.base.push;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ClientStatus {
	
	public static enum Status {NoUser, AppIdErr, ONLINE, OFFLINE, UNKNOW_ERR};
	
	private String cid;
	
	private String sid;
	
	private String uid;
	
	private Status status; 
	
	private String lastLoginTime;
	
	private String reportTime;
	
	private String reportApp;
	
	private String reportPlatform;
	
	private final static SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public ClientStatus(String cid) {
		this.cid = cid;
	}

	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(long lastLoginTime) {
		this.lastLoginTime = SDF.format(new Date(lastLoginTime));
	}

	public String getReportTime() {
		return reportTime;
	}

	public void setReportTime(long reportTime) {
		this.reportTime = SDF.format(new Date(reportTime));
	}
	
	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public void setReportTime(String reportTime) {
		this.reportTime = reportTime;
	}

	public String getReportPlatform() {
		return reportPlatform;
	}

	public void setReportPlatform(String reportPlatform) {
		this.reportPlatform = reportPlatform;
	}

	public String getReportApp() {
		return reportApp;
	}

	public void setReportApp(String reportApp) {
		this.reportApp = reportApp;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

}
