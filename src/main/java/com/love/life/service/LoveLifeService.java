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
import com.love.life.bean.Customer;
import com.love.life.bean.Paperinnovate;
import com.love.life.common.Constants;
import com.love.life.common.HanToWB;
import com.love.life.common.Pyjc;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;


@Service("loveLifeService")
public class LoveLifeService {
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
		ResponseEntity<String> response = client.postForEntity(Constants.registerOrigami,Info, String.class);
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
	    response = client.exchange(Constants.deleteOrigami+"/"+Id, HttpMethod.DELETE, null, String.class);
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
	@HystrixCommand(fallbackMethod = "searchhystrixMapParam")
	public String search(Paperinnovate paperinnovate) {
		String s = client.getForObject(Constants.searchOrigami+toURL(paperinnovate), String.class);
		return  s;
	}
	
	/**
	 * 根据查询条件获取信息记录数
	 * @param 
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "searchhystrixMapParam")
	public String searchCount(Paperinnovate paperinnovate) {
		String s = client.getForObject(Constants.searchCountOrigami+toURL(paperinnovate), String.class);
		return  s;
	}
	
	public String searchhystrixMapParam(Paperinnovate paperinnovate)
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
		Paperinnovate paperinnovate=JSON.parseObject(Info, Paperinnovate.class);
		RequestEntity<Object> request = new RequestEntity<Object>(Info,HttpMethod.PUT,
				URI.create(Constants.alterOrigami+"/"+paperinnovate.getId()));
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
		ResponseEntity<String> response = client.getForEntity(Constants.searchOrigami+"/"+id,String.class);
		return response.getBody();
	}

	public String getByIdhystrixMapParam(String id)
	{
		return "{\"result\":\"false\",\"mesg\":\"查询失败\"}";
	}
	
	
	 public static String toURL(Paperinnovate paperinnovate){
	    	String url="?";
	    	if(paperinnovate.getTitle()!=null){
	    		url+="&name="+paperinnovate.getTitle();
	    	}	    
	    	if(paperinnovate.getPageIndex()!=null){
	    		url+="&pageIndex="+paperinnovate.getPageIndex();
	    	}
	    	if(paperinnovate.getPageSize()!=null){
	    		url+="&pageSize="+paperinnovate.getPageSize();
	    	}
	    	if(url.equals("?")){
	    		url="";
	    	}
	    	return url;
	    }
}
