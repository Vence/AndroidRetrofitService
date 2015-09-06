package com.shenj.teworksandroid.http.test;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import com.shenj.teworks.pojo.domain.Domain;
import com.shenj.teworksandroid.http.common.HttpAPIUtil;
import com.shenj.teworksandroid.http.domain.DomainService;
import com.shenj.teworksandroid.http.domain.DomainServiceImpl;
import com.shenj.teworksandroid.http.login.LoginService;
import com.shenj.teworksandroid.http.login.LoginServiceImpl;

public class LoginServiceTest {

	public static void main(String[] args){
		
		final String account = "wangwenlu@163.com";
		final String password = "123456";
		
		DomainService domainService = new DomainServiceImpl();
		
		domainService.findDomainByAccount(account , new Callback<List<Domain>>(){

			@Override
			public void success(List<Domain> domains, Response response) {
				// TODO Auto-generated method stub
				
				if (domains.size() > 0) {
					
					String domainID = domains.get(0).getId();
					
					LoginService loginService = new LoginServiceImpl();
					
					loginService.login(domainID ,  account , password, new Callback<String>(){

						@Override
						public void success(String t, Response response) {
							// TODO Auto-generated method stub
							HttpAPIUtil.getInstance().setSessionID(t);
							
							System.out.println(t);
							System.out.println("登录成功");
							
						}

						@Override
						public void failure(RetrofitError error) {
							// TODO Auto-generated method stub
							
						}
						
					});
					
					
				} else {
					throw new IllegalArgumentException("未发现组织");
				}
			}

			@Override
			public void failure(RetrofitError error) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		
		
	}
	
}
