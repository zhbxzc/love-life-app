package com.taikang.healthcare.cis.dig.service;

import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.taikang.healthcare.cis.dig.bean.DidicVo;
import com.taikang.healthcare.cis.dig.common.Constants;

@Service("didicAppService")
public class DidicAppService {
	@Autowired
	RestTemplate client;
	
	@HystrixCommand(fallbackMethod = "hystrixMapParam")
	public String register(String didicInfo)
	{
		ResponseEntity<String> response = client.postForEntity(Constants.registerDidic,didicInfo, String.class);
		return response.getBody();
	}
	
	public String hystrixMapParam(String map)
	{
		return "{\"result\":\"false\",\"mesg\":\"调用失败\"}";
	}
	/**
	 * 
	 * FIXME 
	 * @param icdId
	 * @return
	 * @author xingzy 
	 */
/*  @HystrixCommand(fallbackMethod = "deletehystrixMapParam")*/
	public String delete(String icdId)
	{   
		System.out.println(Constants.deleteDidic+"/"+icdId);
		ResponseEntity<String> response=null;
	    response = client.exchange(Constants.deleteDidic+"/"+icdId, HttpMethod.DELETE, null, String.class);
		return response.getBody().toString();
	}
	
	public Boolean deletehystrixMapParam(String icdId)
	{
		return false;
	}
	
	/**
	 * 根据查询条件获取诊断字典信息
	 * @param map
	 * @return
	 * itw_liyang05
	 */

	@HystrixCommand(fallbackMethod = "searchhystrixMapParam")
	public String search(DidicVo didic) {
		String s = client.getForObject(Constants.searchDidic+DidicVo.toURL(didic), String.class);
	return  s;
	}
	@HystrixCommand(fallbackMethod = "searchhystrixMapParam")
	public String searchCount(DidicVo didic) {
		String s = client.getForObject(Constants.searchCountDidic+DidicVo.toURL(didic), String.class);
	return  s;
	}
	public Integer searchhystrixint(DidicVo didic){
		return null;
	}
	public String searchhystrixMapParam(DidicVo didic)
	{
		return null;
	}
	 
	@HystrixCommand(fallbackMethod = "hystrixMapParam")
	public String alter(String didicInfo)
	{		
		RequestEntity<Object> request = new RequestEntity<Object>(didicInfo,HttpMethod.PUT,
				URI.create(Constants.alterDidic));
		ResponseEntity<String> response = client.exchange(request, String.class);

		return response.getBody();
	}
	
	@HystrixCommand(fallbackMethod = "getByIdhystrixMapParam")
	public String getById(String id) {
		ResponseEntity<String> response = client.getForEntity(Constants.getByIdDidic+"/"+id,String.class);
		return response.getBody();
	}

	public String getByIdhystrixMapParam(String id)
	{
		return "{\"result\":\"error\"}";
	}
	
	
}
