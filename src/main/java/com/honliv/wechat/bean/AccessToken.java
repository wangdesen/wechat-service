package com.honliv.wechat.bean;

/**
 * Wechat AccessToken
 * 
 * @author wangdesen
 * */
public class AccessToken {
	
	private int id;
	private String appid;
	private String token;
	private int expiresIn;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}
}