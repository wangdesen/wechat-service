package com.honliv.wechat.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.honliv.wechat.base.BaseResult;
import com.honliv.wechat.bean.AccessToken;
import com.honliv.wechat.bean.WechatConfig;
import com.honliv.wechat.constants.ErrorCode;
import com.honliv.wechat.service.AccessTokenService;
import com.honliv.wechat.thread.JSTicketThread;
import com.honliv.wechat.util.SignUtil;

/**
 * JS-SDK调用凭证生成
 * 
 * @author wangdesen
 */
@Controller
@RequestMapping("/sign")
public class SignController implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private HttpServletRequest request;
	
	@Resource
	private AccessTokenService accessTokenService;
	
	/**
	 * 获取微信JS-SDK配置信息
	 * */
	@RequestMapping(value="/config", method = RequestMethod.GET)
	@ResponseBody
	public BaseResult getWechatConfig(){
		
//		String url = "";
		String referer_url = "";
//		url = request.getScheme() +"://" + request.getServerName()  
//                        + ":" +request.getServerPort() 
//                        + request.getServletPath();
		referer_url = request.getHeader("Referer");
		System.out.println("referer_url：" + referer_url);
//		if (request.getQueryString() != null){
//			url += "?" + request.getQueryString();
//		}
//		
//		System.out.println(url);
		
//		String encode_url = "";
//		try {
//			encode_url = URLDecoder.decode(url, "utf-8");
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
		
		Map<String, String> sign = SignUtil.sign(JSTicketThread.jsapiTicket.getTicket(), referer_url);
		WechatConfig config = new WechatConfig();
		config.setAppid("wxcdcd8df5eb2758a1");
		config.setNonceStr(sign.get("nonceStr"));
		config.setTimestamp(sign.get("timestamp"));
		config.setSignature(sign.get("signature"));
		return BaseResult.success(config);
	}
	
	
	@RequestMapping(value="/access-token", method = RequestMethod.GET)
	@ResponseBody
	public BaseResult getAccessToken(){
		
		AccessToken accessToken = this.accessTokenService.getAccessToken("wxcdcd8df5eb2758a1");
		if(accessToken!=null){
			return BaseResult.success(accessToken);
		}
		return BaseResult.fail(ErrorCode.resultNullRetCode, "获取AccessToken失败！");
		
	}
	
	@RequestMapping(value="/test-post", method = RequestMethod.POST)
	@ResponseBody
	//@RequestBody List<String> idList
	//@RequestParam("idList[]") String[] idList
	public BaseResult testPostArrayJson(@RequestBody ModelMap modelMap){
		
		List<String> lists = (List<String>) modelMap.get("idList");
		return BaseResult.success(lists);
	}
	
}
