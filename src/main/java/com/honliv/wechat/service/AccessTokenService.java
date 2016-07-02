package com.honliv.wechat.service;

import com.honliv.wechat.bean.AccessToken;

/**
 * AccessTokenService
 * 
 * @author wangdesen
 * */
public interface AccessTokenService {
	
	//更新AccessToken
	public abstract int updateAccessToken(AccessToken accessToken);
	
	//获取AccessToken
	public abstract AccessToken getAccessToken(String appid);
	
}
