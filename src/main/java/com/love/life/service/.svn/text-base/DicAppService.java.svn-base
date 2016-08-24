package com.taikang.healthcare.cis.dig.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.taikang.healthcare.cis.dig.common.Constants;

@Service
public class DicAppService
{
	@Autowired
	RestTemplate client;

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate()
	{
		RestTemplate template = new RestTemplate();
		//		SimpleClientHttpRequestFactory factory = (SimpleClientHttpRequestFactory) template.getRequestFactory();
		//		factory.setConnectTimeout(10000);
		//		factory.setReadTimeout(3000);
		return template;
	}


	/*
	 * @HystrixCommand为断路器注解，当此方法报错后，会走fallbackMethod指定的方法，具体可根据业务来判断方法上是否增加此注解。
	 */
	@HystrixCommand(fallbackMethod = "hystrixOneParam")
	public JSONArray selectItemsByDeptId(String deptId)
	{
		ResponseEntity<String> response = client.getForEntity(Constants.selectItemsByDeptId+"/"+deptId, String.class);
		return JSONArray.parseArray(response.getBody());
	}
	@HystrixCommand(fallbackMethod = "hystrixOneParam")
	public JSONArray selectItemsByUserId(String userId)
	{
		ResponseEntity<String> response = client.getForEntity(Constants.selectItemsByUserId+"/"+userId, String.class);
		return JSONArray.parseArray(response.getBody());
	}
	/**
	 * 通过token从redis中获取用户下的所有部门
	 * @param token
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "hystrixOneStringParam")
	public String getValuesFromRedisByToken(String token)
	{
		 String url = Constants.getValuesFromRedisByToken;  
		 HttpPost  httpPost =new  HttpPost(url); 
		 List<NameValuePair> params =new ArrayList<NameValuePair>();
		 params.add(new BasicNameValuePair("token", token));
		 String result="" ;
		 try {
			 httpPost.setEntity(new UrlEncodedFormEntity(params,"UTF-8"));
			 HttpResponse httpResponse =new  DefaultHttpClient().execute(httpPost);
			 if(httpResponse.getStatusLine().getStatusCode()==200){
				 HttpEntity   httpEntity = httpResponse.getEntity();
				 result = EntityUtils.toString(httpEntity);
			 }
		 }
		 catch(Exception e){
			 e.printStackTrace();
		 }
		return result;
	}
	/**
	 * 新增科室诊断模板
	 * @param favoriteDepts
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "hystrixParamReturnBl")
	public Boolean createFavoriteDept(String body)
	{
		ResponseEntity<String> response = client.postForEntity(Constants.createFavoriteDept,body, String.class);
		return Boolean.parseBoolean(response.getBody());
	}
	/**
	 * 删除科室诊断模板
	 * @param favoriteDepts
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "hystrixParamReturnBl")
	public Boolean deleteFavoriteDept(String body)
	{	
		ResponseEntity<String> response = client.postForEntity(Constants.deletFavoriteDept ,body, String.class);
		return Boolean.parseBoolean(response.getBody());
	}
	/**
	 * 新增个人诊断模板
	 * @param favoriteDepts
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "hystrixParamReturnBl")
	public Boolean createFavoriteUser(String body)
	{
		ResponseEntity<String> response = client.postForEntity(Constants.createFavoriteUser,body, String.class);
		return Boolean.parseBoolean(response.getBody());
	}
	/**
	 * 删除个人诊断模板
	 * @param favoriteDepts
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "hystrixParamReturnBl")
	public Boolean deleteFavoriteUser(String body)
	{
		ResponseEntity<String> response = client.postForEntity(Constants.deleteFavoriteUser,body, String.class);
		return Boolean.parseBoolean(response.getBody());
	}

	public String hystrixNoParam()
	{
		return "{\"result\":\"error\"}";
	}
	
	public JSONArray hystrixOneParam(String id)
	{	
		return null;
	}
	
	public Boolean hystrixParamReturnBl(String str)
	{
		return false;
	}
	public String hystrixOneStringParam(String str)
	{
		return "{\"result\":\"error\"}";
	}
}
