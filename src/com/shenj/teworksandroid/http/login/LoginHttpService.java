package com.shenj.teworksandroid.http.login;

import java.util.Map;

import retrofit.Callback;
import retrofit.http.DELETE;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.QueryMap;

public interface LoginHttpService {

	@POST("/api/{domainid}/loginnocaptcha")
	public void login(@Path("domainid")String domainID , @QueryMap Map<String , String> options , Callback<String> cb);
	
	@DELETE("/api/{sessionid}/logout")
	public void logout(@Path("sessionid")String sessionID , Callback<String> cb);
}
