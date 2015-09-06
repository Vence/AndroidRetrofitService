package com.shenj.teworksandroid.http.domain;

import java.util.List;

import retrofit.Callback;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;
import retrofit.http.Query;

import com.shenj.teworks.pojo.domain.Domain;
import com.shenj.teworksandroid.http.common.ResultInfo;

/**
 * 组织 Http服务类 ，供RestAdapter使用
 * 
 * @author 王文路
 * @date 2015-8-3
 *
 */
public interface DomainHttpService {
	
	@GET("/api/{sessionid}/domains/current")
	public void findCurrent(@Path("sessionid")String sessioinID , Callback<Domain> cb);
	
	@POST("/api/{sessionid}/domains")
	public void add(@Path("sessionid")String sessionID , @Query("data") String domainJsonData , Callback<Domain> cb);
	
	@PUT("/api/{sessionid}/domains/{domainid}")
	public void modify(@Path("sessionid")String sessionID , @Path("domainid")String domainID, @Query("data")String domainJsonData , Callback<String> cb);
	
	@DELETE("/api/{sessionid}/domains/{domainid}")
	public void delete(@Path("sessionid")String sessionID , @Path("domainid")String domainID ,Callback<String> cb);

	@GET("/api/domains")
	public void findDomainByAccount(@Query("user")String  account , Callback<List<Domain>> cb);
	
	
}
