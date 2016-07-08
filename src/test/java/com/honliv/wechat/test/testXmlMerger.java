package com.honliv.wechat.test;

import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.honliv.wechat.bean.AccessToken;
import com.honliv.wechat.service.AccessTokenService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:root-config.xml"})
public class testXmlMerger {

	@Autowired
	private AccessTokenService accessTokenService;
	
	@Test
	public void testGetAccesstoken(){
		AccessToken accessToken = this.accessTokenService.getAccessToken("wxcdcd8df5eb2758a1");
		System.out.println(accessToken.getToken());
	}
}
