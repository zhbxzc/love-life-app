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
import com.love.life.bean.customer;
import com.love.life.common.Constants;
import com.love.life.common.HanToWB;
import com.love.life.common.Pyjc;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;


@Service("loveCustService")
public class LoveCustService {
	@Autowired
	RestTemplate client;
	
	
	
	//HystrixCommandProperties
/**
 * 
* @Title: search 
* @Description: 查询客户信息
* @param @return    设定文件 
* @return String    返回类型 
* @throws
 */
	@HystrixCommand(fallbackMethod = "searchhystrixMapParam")
	public String search(customer customer) {	
		String s = client.getForObject(Constants.searchCust+toURL(customer), String.class);
		return  s;
	}
	
	/**
	 * 
	* @Title: searchCount customer
	* @Description: 查询客户记录条数
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	@HystrixCommand(fallbackMethod = "searchhystrixMapParam")
	public String searchCount(customer customer) {
		String s = client.getForObject(Constants.searchCountCust+toURL(customer), String.class);
		return  s;
	}
	
	public String searchhystrixMapParam(customer customer)
	{
		return "{\"result\":\"false\",\"mesg\":\"查询失败\"}";
	}
	public static String toURL(customer customer){
    	String url="?";
    	if(customer.getName()!=null){
    		url+="&name="+customer.getName();
    	}
    	if(customer.getIdCardNo()!=null){
    		url+="&idCardNo="+customer.getIdCardNo();
    	}
    	if(customer.getSexId()!=null){
    		url+="&sexId="+customer.getSexId();
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
    @HystrixCommand(fallbackMethod = "deletehystrixMapParam")
	public String delete(String id)
	{   
		ResponseEntity<String> response=null;
	    response = client.exchange(Constants.deleteCust+"/"+id, HttpMethod.DELETE, null, String.class);
		return response.getBody().toString();
	}
	
	public String deletehystrixMapParam(String icdId)
	{
		return "{\"result\":\"false\",\"mesg\":\"删除失败\"}";
	}
	
	/*@HystrixCommand(fallbackMethod = "hystrixMapParam")*/
	public String alter(String customerInfo){
		customer customer=JSON.parseObject(customerInfo, customer.class);
		RequestEntity<Object> request = new RequestEntity<Object>(customerInfo,HttpMethod.PUT,
				URI.create(Constants.alterCust+"/"+customer.getId()));
		ResponseEntity<String> response = client.exchange(request, String.class);
		return response.getBody();
	}
/*	@HystrixCommand(fallbackMethod = "hystrixMapParam")*/
	public String register(String customer)
	{
		ResponseEntity<String> response = client.postForEntity(Constants.registerCust,customer, String.class);
		return response.getBody();
	}
	
	public String hystrixMapParam(String customer)
	{
		return "{\"result\":\"false\",\"mesg\":\"调用失败\"}";
	}
}
