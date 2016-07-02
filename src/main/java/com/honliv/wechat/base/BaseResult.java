package com.honliv.wechat.base;

import com.honliv.wechat.constants.ErrorCode;

/**
 * 对返回结果集封装
 * */
public class BaseResult {
	private Object data;
	private String retCode;
	private String retMsg;	
	
	public BaseResult( String retCode,String retMsg,Object data) {
		this.data = data;
		this.retCode = retCode;
		this.retMsg = retMsg;
	}
	
	
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public String getRetCode() {
		return retCode;
	}
	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}
	public String getRetMsg() {
		return retMsg;
	}
	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg;
	}
	
	public static BaseResult  success(){
		BaseResult serviceResult = new BaseResult(ErrorCode.successRetCode,ErrorCode.successRetInfo,null);
		return serviceResult;
	}
	
	public static BaseResult  success(Object obj){
		BaseResult serviceResult = new BaseResult(ErrorCode.successRetCode,ErrorCode.successRetInfo,obj);
		return serviceResult;
	}
	
	

	
	public static BaseResult fail(String code,String info){
		BaseResult serviceResult = new BaseResult(code,info,null);
		return serviceResult;
	}
	
	public static BaseResult exception(String info){
		BaseResult serviceResult = new BaseResult(ErrorCode.exceptionRetCode,ErrorCode.exceptionRetInfo+info,null);
		return serviceResult;
	}
	
	
}
