package com.shenj.teworksandroid.http.common;


/**
 * TEWorksAPI 工具类
 * 
 * @author 王文路
 * @date 2015-7-25
 */
public class HttpAPIUtil {
	
	private String sessionID = null;
	
	private static String HOST = "127.0.0.1";
	private static int PORT = 8080;
	private static String PROTOCOL = "http";
	private static String CONTEXT_PATH = "/teworks-web";
	
	private static String resCode = "2";	// 资源代码，代表android客户端
	
	
	private static  HttpAPIUtil instance = null; 
	
	public static HttpAPIUtil  getInstance (){
		
		if(instance == null) {
			synchronized (HttpAPIUtil.class) {
				if (instance == null)
					System.out.println("2222");
					instance  = new HttpAPIUtil();
			}
		}
		
		return instance;
		 
	}
	
	
	/**
	 * 获取服务端点
	 * 
	 * @author 王文路
	 * @date 2015-8-1
	 * @return
	 */
	public String getEndpoint(){
		String endpoint =  PROTOCOL + "://" + HOST + ":" + PORT + CONTEXT_PATH;
		
		return endpoint;
	}


	public String getSessionID() {
		return sessionID;
	}

	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}


	public static String getResCode() {
		return resCode;
	}



}
