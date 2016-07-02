package com.honliv.wechat.constants;

/**
 * 错误代码
 * 
 * @author wangdesen
 * */
public interface ErrorCode {
	
		public static final String successRetCode = "0000";
		public static final String successRetInfo = "操作成功";	
		
		public static final String exceptionRetCode = "0001";
		public static final String exceptionRetInfo = "系统异常:";	
		
		public static final String paraCheckErrorRetCode = "0002";
		public static final String paraCheckErrorRetInfo = "参数校验错误:";
		
		public static final String resultNullRetCode = "0003";
		public static final String resultNullRetInfo = "未查询到符合条件的结果:";
		
		public static final String failRetCode = "0004";
		public static final String failRetInfo = "操作失败";
	
}
