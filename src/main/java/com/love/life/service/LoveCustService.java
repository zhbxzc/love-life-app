package com.love.life.service;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.love.life.bean.Paperinnovate;
import com.love.life.bean.Customer;
import com.love.life.common.Constants;
import com.love.life.common.HanToWB;
import com.love.life.common.Pyjc;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;


@Service("loveCustService")
public class LoveCustService {
	@Autowired
	RestTemplate client;
	
	/**
	 * 注册
	 * FIXME 
	 * @param 信息的JSON串
	 * @return 注册
	 */
	@HystrixCommand(fallbackMethod = "hystrixMapParam")
	public String register(String Info)
	{
		ResponseEntity<String> response = client.postForEntity(Constants.registerCust,Info, String.class);
		return response.getBody();
	}
	
	public String hystrixMapParam(String Info)
	{
		return "{\"result\":\"false\",\"mesg\":\"调用失败\"}";
	}
	
	/**
	 * 
	 * FIXME 
	 * @param Id
	 * @return
	 * @author
	 */
    @HystrixCommand(fallbackMethod = "deletehystrixMapParam")
	public String delete(String Id)
	{   
		ResponseEntity<String> response=null;
	    response = client.exchange(Constants.deleteCust+"/"+Id, HttpMethod.DELETE, null, String.class);
		return response.getBody().toString();
	}
	
	public String deletehystrixMapParam(String Id)
	{
		return "{\"result\":\"false\",\"mesg\":\"删除失败\"}";
	}
	
	//HystrixCommandProperties
	/**
	 * 根据查询条件获取信息
	 * @param 
	 * @return
	 * 
	 */
	/*@HystrixCommand(fallbackMethod = "searchhystrixMapParam")*/
	public String search(Customer customer) {
		String s = client.getForObject(Constants.searchCust+toURL(customer), String.class);
		return  s;
	}
	
	/**
	 * 根据查询条件获取信息记录数
	 * @param 
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "searchhystrixMapParam")
	public String searchCount(Customer customer) {
		String s = client.getForObject(Constants.searchCountCust+toURL(customer), String.class);
		return  s;
	}
	
	public String searchhystrixMapParam(Customer customer)
	{
		return "{\"result\":\"false\",\"mesg\":\"查询失败\"}";
	}
	
	/**
	 * FIXME 修改信息 
	 * @param Info
	 * @return 
	 */
	@HystrixCommand(fallbackMethod = "hystrixMapParam")
	public String alter(String Info){
		Customer customer=JSON.parseObject(Info, Customer.class);
		RequestEntity<Object> request = new RequestEntity<Object>(Info,HttpMethod.PUT,
				URI.create(Constants.alterCust+"/"+customer.getId()));
		ResponseEntity<String> response = client.exchange(request, String.class);
		return response.getBody();
	}
	
	/**
	 * 根据ID获取一个信息
	 * @param id
	 * @return 
	 */
	@HystrixCommand(fallbackMethod = "getByIdhystrixMapParam")
	public String getById(String id) {
		ResponseEntity<String> response = client.getForEntity(Constants.searchCust+"/"+id,String.class);
		return response.getBody();
	}

	public String getByIdhystrixMapParam(String id)
	{
		return "{\"result\":\"false\",\"mesg\":\"查询失败\"}";
	}
	
	
	 public static String toURL(Customer customer){
	    	String url="?";
	    	if(customer.getName()!=null){
	    		url+="&name="+customer.getName();
	    	}	
	    	if(customer.getSexId()!=null){
	    		url+="&sexId="+customer.getSexId();
	    	}
	    	if(customer.getIdCardNo()!=null){
	    		url+="&idCardNo="+customer.getIdCardNo();
	    	}
	    	if(customer.getPageIndex()!=null){
	    		url+="&pageIndex="+customer.getPageIndex();
	    	}
	    	if(customer.getPageSize()!=null){
	    		url+="&pageSize="+customer.getPageSize();
	    	}
	    	if(url.equals("?")){
	    		url="";
	    	}
	    	return url;
	    }
}
