package com.api.json;

public class UserLoginVo {
	/**
	 * MD5key锛� aes-11-22-33
	 * {
		"userId":"123",
		"userName":"123",
		"userType":"123",
		"admin":true,
		"desc":"111",
		"userSignature":"ffb8057608468a037296ff689f4441ed"
		}
		userSignature=MD5(userId:userName:userType:admin:desc:MD5key)
	 */
	private String userId;
	private String userName;
	private String userType;
	private String admin;
	private String desc;
	private String userSignature;
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getAdmin() {
		return admin;
	}
	public void setAdmin(String admin) {
		this.admin = admin;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getUserSignature() {
		return userSignature;
	}
	public void setUserSignature(String userSignature) {
		this.userSignature = userSignature;
	}

}
