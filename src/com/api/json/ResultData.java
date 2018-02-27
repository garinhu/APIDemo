package com.api.json;

import java.util.List;

public class ResultData {
	
	private int result;
	private List<UserLoginVo> userLogin;
	
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public List<UserLoginVo> getUserLogin() {
		return userLogin;
	}
	public void setUserLogin(List<UserLoginVo> userLogin) {
		this.userLogin = userLogin;
	}

}
