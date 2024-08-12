package com.intone.auth.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class UserRecord {
	@Id
	private String userId;
	private String token;
	public UserRecord() {
		
	}
	public UserRecord(String userId, String token) {
		this.userId=userId;
		this.token=token;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
