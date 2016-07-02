package com.honliv.wechat.dao;

import org.springframework.dao.DataAccessException;

import com.honliv.wechat.bean.JsapiTicket;

/**
 * 保存AccessTokenDao
 * 
 * @author wangdesen
 * */
public interface JsapiTicketMapper {
	
	//保存AccessToken
	int updateJsapiTicket(JsapiTicket jsapiTicket)throws DataAccessException;
}