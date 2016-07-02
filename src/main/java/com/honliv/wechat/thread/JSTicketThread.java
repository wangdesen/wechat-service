package com.honliv.wechat.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.honliv.wechat.bean.AccessToken;
import com.honliv.wechat.bean.JsapiTicket;
import com.honliv.wechat.service.AccessTokenService;
import com.honliv.wechat.service.JsapiTicketService;
import com.honliv.wechat.util.SpringBeanFactoryUtils;
import com.honliv.wechat.util.WeixinUtil;

/**
 * JSTicket Thread
 * 
 * @author wangdesen
 * */
public class JSTicketThread implements Runnable {
	
	private static Logger log = LoggerFactory.getLogger(JSTicketThread.class);  
    
    //jsapi_ticket
    public static JsapiTicket jsapiTicket = null;
    
    private AccessTokenService accessTokenService = (AccessTokenService) SpringBeanFactoryUtils.getBean("accessTokenService");
  	
    private JsapiTicketService jsapiTicketService = (JsapiTicketService) SpringBeanFactoryUtils.getBean("jsapiTicketService");
    
	@Override
	public void run() {
		while (true) {  
            try {  
            	AccessToken accessToken = this.accessTokenService.getAccessToken("wxcdcd8df5eb2758a1");
            	if(accessToken != null){
        			String accesstoken = accessToken.getToken();
        			jsapiTicket = WeixinUtil.getJsapiTicket(accesstoken);  
        		}
                if (null != jsapiTicket) {  
                	log.info("获取jsapi_ticket成功，有效时长{}秒 token:{}", jsapiTicket.getExpiresIn(), jsapiTicket.getTicket());  
                	jsapiTicket.setId(1);
                	jsapiTicket.setAppid(accessToken.getAppid());
                	this.jsapiTicketService.updateJsapiTicket(jsapiTicket);
                	// 休眠一小时
                    Thread.sleep((jsapiTicket.getExpiresIn() - 3600) * 1000);  
                } else {  
                    // 如果access_token为null，60秒后再获取  
                    Thread.sleep(60 * 1000);  
                }  
            } catch (InterruptedException e) {  
                try {  
                    Thread.sleep(60 * 1000);  
                } catch (InterruptedException e1) {  
                    log.error("{}", e1);  
                }  
                log.error("{}", e);  
            }  
        }  
	}

}
