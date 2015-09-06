package com.shenj.teworksandroid.http.login;

import retrofit.Callback;

/**
 * 登录服务类
 * 
 * @author 王文路
 * @date 2015-8-3
 *
 */
public interface LoginService {
	
	/**
	 * 登录
	 * 
	 * @author 王文路
	 * @date 2015-8-3
	 * @param domainID
	 * @param userName 用户名
	 * @param password 密码
	 * @param cb
	 */
	public void login(String domainID , String userName , String password , Callback<String> cb);
	
	/**
	 * 登出
	 * 
	 * @author 王文路
	 * @date 2015-8-3
	 * @param sessionID
	 * @param cb
	 */
	public void logout(String sessionID , Callback<String> cb);

}
