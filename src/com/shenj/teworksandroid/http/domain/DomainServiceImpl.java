package com.shenj.teworksandroid.http.domain;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

import com.shenj.teworks.pojo.domain.Domain;
import com.shenj.teworksandroid.http.common.JsonBinder;
import com.shenj.teworksandroid.http.common.retrofit.RestAdapterManager;

/**
 * 组织服务类客户端访问
 * 
 * @author 王文路
 * @date 2015-8-3
 *
 */
public class DomainServiceImpl implements DomainService{
	
	private DomainHttpService domainHttpService;
	
	public DomainServiceImpl() {
		super();
		
		domainHttpService = RestAdapterManager.getInstance().create(DomainHttpService.class); 
	}

	@Override
	public void findCurrent(String sessionID , Callback<Domain> cb){
		
		domainHttpService.findCurrent(sessionID , cb);
		
	}
	
	@Override
	public void add(String sessionID, Domain domain, Callback<Domain> cb) {
		
		String domainJsonData = JsonBinder.getInstance().toJson(domain);
		
		domainHttpService.add(sessionID, domainJsonData , cb);
		
	}

	@Override
	public void modify(String sessionID, Domain domain, Callback<String> cb) {
		
		String domainJsonData = JsonBinder.getInstance().toJson(domain);
		
		domainHttpService.modify(sessionID, domain.getId(), domainJsonData, cb);
	}
	

	@Override
	public void delete(String sessionID, String domainID, Callback<String> cb) {
		
		domainHttpService.delete(sessionID, domainID, cb);
		
	}

	
	@Override
	public void findDomainByAccount(String account ,  Callback<List<Domain>> cb){
		
		domainHttpService.findDomainByAccount(account ,cb);
		
	}
	
	
	
	
	public static void main(String[] args){
		
		DomainServiceImpl client = new DomainServiceImpl();
		client.findDomainByAccount("wanghuiyi@expogroup.sh.cn", new Callback<List<Domain>>(){

			
			@Override
			public void success(List<Domain> domains, Response arg1) {
				// TODO Auto-generated method stub
				
				System.out.println(domains.size());
				
			}
			@Override
			public void failure(RetrofitError arg0) {
				// TODO Auto-generated method stub
				
				System.out.println(arg0);
			}
		});
	}

	


}
