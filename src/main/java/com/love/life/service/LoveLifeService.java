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
	 * 
	* @Title: register 
	* @Description: 新增折纸信息 
	* @param @param origamiinnoInfo
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	/*@HystrixCommand(fallbackMethod = "hystrixMapParam")*/
	public String register(String origamiinnoInfo)
	{
		
		String url = Constants.registerOrigami;
		ResponseEntity<String> response = client.postForEntity(url,origamiinnoInfo, String.class);
		return response.getBody();
	}
	
	public String hystrixMapParam(String origamiinnoInfo)
	{
		return "{\"result\":\"false\",\"mesg\":\"调用失败\"}";
	}
	
	/**
	 * 
	* @Title: delete 
	* @Description: 删除折纸信息 
	* @param @param origamiId
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
    @HystrixCommand(fallbackMethod = "deletehystrixMapParam")
	public String delete(String origamiId)
	{   
		ResponseEntity<String> response=null;
	    response = client.exchange(Constants.deleteOrigami+"/"+origamiId, HttpMethod.DELETE, null, String.class);
		return response.getBody().toString();
	}
	
	public String deletehystrixMapParam(String icdId)
	{
		return "{\"result\":\"false\",\"mesg\":\"删除失败\"}";
	}
	
	//HystrixCommandProperties
	/**
	 * 根据查询条件获取诊断字典信息
	 * @param didic
	 * @return
	 * itw_liyang05
	 */
	@HystrixCommand(fallbackMethod = "searchhystrixMapParam")
	public String search(Paperinnovate paperinnovate) {
		String url="?";
    	if(paperinnovate.getId()!=null){
    		url+="&id="+paperinnovate.getId();
    	}
    	if(paperinnovate.getTitle()!=null){
    		url+="&title="+paperinnovate.getTitle();
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
		
		String s = client.getForObject(Constants.searchOrigami+url, String.class);
		return  s;
	}
	
	/**
	 * 
	* @Title: searchCount 
	* @Description: 查询记录条数 
	* @param @param paperinnovate
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	@HystrixCommand(fallbackMethod = "searchhystrixMapParam")
	public String searchCount(Paperinnovate paperinnovate) {
		String url="?";
    	if(paperinnovate.getId()!=null){
    		url+="&id="+paperinnovate.getId();
    	}
    	if(paperinnovate.getTitle()!=null){
    		url+="&title="+paperinnovate.getTitle();
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
		String s = client.getForObject(Constants.searchOrigami+url, String.class);
		return  s;
	}
	
	public String searchhystrixMapParam(Paperinnovate paperinnovate)
	{
		return "{\"result\":\"false\",\"mesg\":\"查询失败\"}";
	}
	
	/**
	 * 
	* @Title: alter 
	* @Description: 修改信息
	* @param @param paperinnovateInfo
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	@HystrixCommand(fallbackMethod = "hystrixMapParam")
	public String alter(String paperinnovateInfo){
		Paperinnovate paperinnovate=JSON.parseObject(paperinnovateInfo, Paperinnovate.class);
		RequestEntity<Object> request = new RequestEntity<Object>(paperinnovateInfo,HttpMethod.PUT,
				URI.create(Constants.alterOrigami+"/"+paperinnovate.getId()));
		ResponseEntity<String> response = client.exchange(request, String.class);
		return response.getBody();
	}
	
	/**
	 * 
	* @Title: getById 
	* @Description: 根据Id查询信息
	* @param @param id
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	@HystrixCommand(fallbackMethod = "getByIdhystrixMapParam")
	public String getById(String id) {
		ResponseEntity<String> response = client.getForEntity(Constants.getByIdOrigami+"/"+id,String.class);
		return response.getBody();
	}

	public String getByIdhystrixMapParam(String id)
	{
		return "{\"result\":\"false\",\"mesg\":\"查询失败\"}";
	}

	
}
