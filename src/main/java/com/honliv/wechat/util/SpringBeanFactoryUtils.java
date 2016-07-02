package com.honliv.wechat.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

/**
 * 为线程提供SpringBean的注入方法
 * 
 * @author wangdesen
 * */
public class SpringBeanFactoryUtils implements BeanFactoryAware {

	private static BeanFactory beanFactory = null;
	private static SpringBeanFactoryUtils factoryUtils = null;
	
	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		SpringBeanFactoryUtils.beanFactory = beanFactory;
	}
	
	public static BeanFactory getBeanFactory(){
		return beanFactory;
	}
	
	public static SpringBeanFactoryUtils getInstance(){
		if(factoryUtils == null){
			factoryUtils = new SpringBeanFactoryUtils();
		}
		return factoryUtils;
	}
	
	public static Object getBean(String name){
		return beanFactory.getBean(name);
	}

}
