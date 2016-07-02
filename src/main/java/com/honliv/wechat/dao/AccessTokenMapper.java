package com.honliv.wechat.dao;

import org.springframework.dao.DataAccessException;

import com.honliv.wechat.bean.AccessToken;

/**
 * 保存AccessTokenDao
 * 
 * @author wangdesen
 * */
public interface AccessTokenMapper {
	
	//保存AccessToken
	int updateAccessToken(AccessToken accessToken)throws DataAccessException;
	
	//获取当前可用AccessToken
	AccessToken getAccessToken(String appid);
}