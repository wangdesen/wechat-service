package com.honliv.wechat.servlet;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.honliv.wechat.thread.JSTicketThread;
import com.honliv.wechat.thread.TokenThread;

/**
 * 初始化Servlet，获取access_token并保存到内存
 * 
 * @author wangdesen
 */
public class InitServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
//	private static Logger log = LoggerFactory.getLogger(WeixinUtil.class);
       
	public void init() throws ServletException {  
		
		//设置线程池大小
		ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
		
		scheduledThreadPool.scheduleAtFixedRate(
				new TokenThread(getInitParameter("hljt_appid"), getInitParameter("hljt_appsecret")), 0, 3600, TimeUnit.SECONDS);
		
		scheduledThreadPool.scheduleAtFixedRate(
				new TokenThread(getInitParameter("hnhlyy_appid"), getInitParameter("hnhlyy_appsecret")), 30, 3600, TimeUnit.SECONDS);
		
		scheduledThreadPool.scheduleAtFixedRate(
				new TokenThread(getInitParameter("hlyy_appid"), getInitParameter("hlyy_appsecret")), 60, 3600, TimeUnit.SECONDS);
		
		scheduledThreadPool.scheduleAtFixedRate(
				new TokenThread(getInitParameter("alife_appid"), getInitParameter("alife_appsecret")), 90, 3600, TimeUnit.SECONDS);
		
		scheduledThreadPool.scheduleAtFixedRate(
				new JSTicketThread(), 120, 3600, TimeUnit.SECONDS);
//        TokenThread.appid = getInitParameter("appid");  
//        TokenThread.appsecret = getInitParameter("appsecret");  
//  
//        log.info("weixin api appid:{}", TokenThread.appid);  
//        log.info("weixin api appsecret:{}", TokenThread.appsecret);  
  
        // 未配置appid、appsecret时给出提示  
       /* if ("".equals(TokenThread.appid) || "".equals(TokenThread.appsecret)) {  
            log.error("appid and appsecret configuration error, please check carefully.");  
        } else {  
            // 启动定时获取access_token的线程  
            new Thread(new TokenThread()).start();
            //启动获取jsapi_ticket的线程
            new Thread(new JSTicketThread()).start();
        }  */
    }  

}
