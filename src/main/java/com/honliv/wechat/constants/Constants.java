package com.honliv.wechat.constants;

/**
 * 结果状态
 * 
 * @author wangdesen
 */
public class Constants {
	
	/**
	 * 体检号不存在或手机号不一致
	 */
	public static final String PEID_AND_PHONE_NOT_MATCH = "体检卡号或手机号出错，请到体检台维护信息";
	
	/**
	 * 已绑定
	 * */
	public static final String HAS_BINDED = "您已经绑定了这张体检卡，请勿重复绑定";
	
	/**
	 * 短信发送失败
	 * */
	public static final String SEND_MESSAGE_ERROR = "短信发送失败";
	
	/**
	 * 验证码错误
	 * */
	public static final String CAPTCHA_ERROR = "验证码错误";
	
	/**
	 * 绑定失败
	 * */
	public static final String BIND_ERROR = "绑定失败";
	
	/**
	 * 解绑失败
	 * */
	public static final String UNBIND_ERROR = "解除绑定失败";
	
	/**
	 * access_token超时
	 * */
	public static final String AUTH_TIME_OUT = "授权超时，请确认已关注河南宏力医院公众号，返回微信重新打开页面";
	
	/**
	 * 套餐列表查询失败
	 * */
	public static final String PACKAGE_LIST_FAILED = "套餐列表查询失败";
	
	/**
	 * 套餐详情查询失败
	 * */
	public static final String PACKAGE_DETAIL_FAILED = "套餐详情查询失败";
	
	/**
	 * 找不到诊疗卡号
	 * */
	public static final String CAN_NOT_FOUND_PEID = "找不到诊疗卡号";
	
	/**
	 * 找不到出生日期
	 * */
	public static final String CAN_NOT_FOUND_BIRTHDAY = "找不到出生日期";
	
	/**
	 * 体检预约失败
	 * */
	public static final String PE_APPOINT_FAILED = "体检预约失败";
	
	/**
	 * 找不到体检记录
	 * */
	public static final String CAN_NOT_FOUND_PE_INFO = "找不到体检记录";
	
	/**
	 * 找不到体检项目类别
	 * */
	public static final String CAN_NOT_FOUND_PE_CLASS = "找不到体检项目类别";
	
	/**
	 * 找不到科室小结
	 * */
	public static final String CAN_NOT_FOUND_DEP_SUMMARY = "找不到科室小结";
	
	/**
	 * 找不到结果汇总
	 * */
	public static final String CAN_NOT_FOUND_RESULT_SUMMARY = "找不到结果汇总";
	
	/**
	 * 找不到体检建议
	 * */
	public static final String CAN_NOT_FOUND_PE_ADVICE = "找不到体检建议";
	
	/**
	 * 找到体检项目详情
	 * */
	public static final String CAN_NOT_FOUND_PE_RESULT = "找不到体检项目详情。类别代码：";
	
	/**
	 * 未找到本次体检记录
	 * */
	public static final String CAN_NOT_FOUND_PE_RECORE = "找不到本次体检记录";
	
	/**
	 * 跳转地址
	 * */
	public static final String PACKAGE_SUBSCRIBE = "package/subscribe.html";
	
	/**
	 * 
	 * */
	public static final String REPORT_STP1 = "report/report-stp1.html";
	
	public static final String REPORT_STP2 = "report/report-stp2.html";
	
	public static final String REPORT_STP3 = "report/report-stp3.html";
	
	public static final String WECHAT_MY_CARD = "wechat/my-card.html";
	
	public static final String WECHAT_BIND = "wechat/wechat-bind.html";
	
}
