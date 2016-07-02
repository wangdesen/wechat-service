package com.honliv.wechat.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.honliv.wechat.bean.AccessToken;
import com.honliv.wechat.service.AccessTokenService;
import com.honliv.wechat.util.SpringBeanFactoryUtils;
import com.honliv.wechat.util.WeixinUtil;

/**
 * Token Thread
 * 
 * @author wangdesen
 * */
public class TokenThread implements Runnable {
	
	private static Logger log = LoggerFactory.getLogger(TokenThread.class);  
	// 第三方用户唯一凭证  
    public String appid = "";  
    // 第三方用户唯一凭证密钥  
    public String appsecret = "";  
    public static AccessToken accessToken = null;
    
    public TokenThread(String appid, String appsecret) {
		this.appid = appid;
		this.appsecret = appsecret;
	}
    
  	private AccessTokenService accessTokenService = (AccessTokenService) SpringBeanFactoryUtils.getBean("accessTokenService");
  	
	@Override
	public void run() {
		while (true) {  
            try {
                accessToken = WeixinUtil.getAccessToken(appid, appsecret);  
                if (null != accessToken) {  
                	log.info("公众号ID：" + appid + "获取access_token成功，有效时长{}秒 token:{}", accessToken.getExpiresIn(), accessToken.getToken());  
                	//accessToken.setId(1);
                	accessToken.setAppid(appid);
                	this.accessTokenService.updateAccessToken(accessToken);
                	// 休眠一小时  
                    Thread.sleep((accessToken.getExpiresIn() - 3600) * 1000);  
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
