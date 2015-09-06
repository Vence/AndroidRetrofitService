package com.shenj.teworksandroid.http.test;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import com.shenj.teworks.pojo.domain.Domain;
import com.shenj.teworksandroid.http.common.HttpAPIUtil;
import com.shenj.teworksandroid.http.domain.DomainService;
import com.shenj.teworksandroid.http.domain.DomainServiceImpl;

public class DomainServiceTest {

	public static void main(String[] args){
		
		DomainService domainService = new DomainServiceImpl();
		
		System.out.println(HttpAPIUtil.getInstance(). getSessionID());
		domainService.findCurrent(HttpAPIUtil.getInstance().getSessionID(), new Callback<Domain>(){

			@Override
			public void success(Domain t, Response response) {
				// TODO Auto-generated method stub
				System.out.println(t.getName());
			}

			@Override
			public void failure(RetrofitError error) {
				// TODO Auto-generated method stub
				System.out.println(error);
			}
			
		});
		
	}
}
