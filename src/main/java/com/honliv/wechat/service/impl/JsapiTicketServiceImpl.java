package com.honliv.wechat.service.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.honliv.wechat.bean.JsapiTicket;
import com.honliv.wechat.dao.JsapiTicketMapper;
import com.honliv.wechat.service.JsapiTicketService;

/**
 * JsapiTicketService实现类
 * 
 * @author wangdesen
 * */
@Service("jsapiTicketService")
public class JsapiTicketServiceImpl implements JsapiTicketService {

	private static Logger logger = Logger.getLogger(JsapiTicketServiceImpl.class);
	
	//持久化JsapiTicket
	@Resource
	private JsapiTicketMapper jsapiTicketDao;
	
	
	/**
	 * 插入保存的AccessToken
	 * 
	 * @param				accessToken			access_token
	 * 
	 * @return
	 * */
	public int updateJsapiTicket(JsapiTicket jsapiTicket) {
		try{
			return this.jsapiTicketDao.updateJsapiTicket(jsapiTicket);
		}catch(DataAccessException e){
			logger.error(e.getMessage());
			return 0;
		}
	}

}
