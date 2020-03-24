package com.github.maxwell.base.push;

import java.util.Map;

public class PushResult {	
	private boolean success; 
	
	private Integer total;
	
	private String msg;
	
	PushResult() {}
	
	public static PushResult ok() {
		return new PushResult().setSuccess(true);
	}
	
	public static PushResult fail() {
		return new PushResult().setSuccess(false);
	}
	
	public static PushResult fail(String msg) {
		return new PushResult().setSuccess(false).setMsg(msg);
	}
	
	public static PushResult of(Map<String, Object> response) {
		if (!"ok".equals(response.get("result"))) {
			return PushResult.fail();
		}
		return PushResult.ok().setSuccess(true);
	}
	
	public Integer getTotal() {
		return total;
	}

	public PushResult setTotal(int total) {
		this.total = total;
		return this;
	}

	public boolean isSuccess() {
		return success;
	}

	public PushResult setSuccess(boolean success) {
		this.success = success;
		return this;
	}

	public String getMsg() {
		return msg;
	}

	public PushResult setMsg(String msg) {
		this.msg = msg;
		return this;
	}
		
}
