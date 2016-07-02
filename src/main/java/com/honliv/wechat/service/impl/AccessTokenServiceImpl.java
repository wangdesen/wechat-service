package com.honliv.wechat.service.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;


import com.honliv.wechat.bean.AccessToken;
import com.honliv.wechat.dao.AccessTokenMapper;
import com.honliv.wechat.service.AccessTokenService;

/**
 * 微信绑定信息接口实现类
 * 
 * @author wangdesen
 * */
@Service("accessTokenService")
public class AccessTokenServiceImpl implements AccessTokenService {

	private static Logger logger = Logger.getLogger(AccessTokenServiceImpl.class);
	
	//持久化AccessToken
	@Resource
	private AccessTokenMapper accessTokenDao;
	
	
	/**
	 * 插入保存的AccessToken
	 * 
	 * @param				accessToken			access_token
	 * 
	 * @return
	 * */
	public int updateAccessToken(AccessToken accessToken) {
		try{
			return this.accessTokenDao.updateAccessToken(accessToken);
		}catch(DataAccessException e){
			logger.error(e.getMessage());
			return 0;
		}
	}


	/**
	 * 获取最新Access_Token
	 * */
	@Override
	public AccessToken getAccessToken(String appid) {
		return this.accessTokenDao.getAccessToken(appid);
	}

}
