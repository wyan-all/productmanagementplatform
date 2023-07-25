package com.damddos.entity;

import javax.persistence.Entity;

//@Entity
public class LoginVO {

	private Integer id;
	
	private String userName;
	
//	private String password;
	
	private String authCode;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}
	
	
}
