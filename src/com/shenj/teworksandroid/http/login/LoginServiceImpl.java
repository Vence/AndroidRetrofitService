package com.shenj.teworksandroid.http.login;

import java.util.HashMap;
import java.util.Map;

import retrofit.Callback;

import com.shenj.teworksandroid.http.common.HttpAPIUtil;
import com.shenj.teworksandroid.http.common.retrofit.RestAdapterManager;

/**
 * 服务类 -实现
 * 
 * @author 王文路
 * @date 2015-8-3
 *
 */
public class LoginServiceImpl implements LoginService{
	
	private LoginHttpService loginHttpService;
	
	public LoginServiceImpl() {
		super();
		
		loginHttpService = RestAdapterManager.getInstance().create(LoginHttpService.class);
	}

	@Override
	public void login(String domainID, String userName, String password,
			Callback<String> cb) {
		
		Map<String , String> options = new HashMap<String , String>();
		options.put("username", userName);
		options.put("password", password);
		options.put("res", HttpAPIUtil.getInstance().getResCode());
		
		loginHttpService.login(domainID, options, cb);
		
	}

	@Override
	public void logout(String sessionID, Callback<String> cb) {
		
		loginHttpService.logout(sessionID, cb);
		
	}

}
