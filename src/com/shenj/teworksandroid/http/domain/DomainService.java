package com.shenj.teworksandroid.http.domain;

import java.util.List;

import retrofit.Callback;

import com.shenj.teworks.pojo.domain.Domain;

/**
 * 组织服务类
 * 
 * @author 王文路
 * @date 2015-8-3
 *
 */
public interface DomainService {

	/**
	 * 获取当前所在组织
	 * 
	 * @author 王文路
	 * @date 2015-8-3
	 * @param sessioinID
	 * @param cb
	 */
	public void findCurrent(String sessioinID , Callback<Domain> cb);
	
	public void add(String sessionID ,Domain domain , Callback<Domain> cb);
	
	public void modify(String sessionID , Domain domain , Callback<String> cb);
	
	public void delete(String sessionID , String domainID ,Callback<String> cb);

	/**
	 * 查询账户的所有组织
	 * 
	 * @author 王文路
	 * @date 2015-8-3
	 * @param account
	 * @param cb
	 */
	public void findDomainByAccount(String  account , Callback<List<Domain>> cb);
}
