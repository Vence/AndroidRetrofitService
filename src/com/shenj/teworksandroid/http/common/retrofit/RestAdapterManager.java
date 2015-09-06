package com.shenj.teworksandroid.http.common.retrofit;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.shenj.teworksandroid.http.common.HttpAPIUtil;

/**
 * RestAdapter生成器
 * 
 * @author 王文路
 * @date 2015-8-3
 *
 * @param <T>
 */
public class RestAdapterManager {
	
	private static  RestAdapter instance = null; 
	private static Gson gson = null;
	
	public static RestAdapter  getInstance (){
		
		if(instance == null) {
			synchronized (RestAdapterManager.class) {
				if (instance == null){
					
					gson = new GsonBuilder()
						.registerTypeAdapterFactory(new ItemTypeAdapterFactory())
						.create();
					
					instance  = new RestAdapter.Builder()
						.setEndpoint(HttpAPIUtil.getInstance().getEndpoint())
						.setConverter(new GsonConverter(gson))
						.build();
				}
			}
		}
		
		return instance;
		 
	}
}
