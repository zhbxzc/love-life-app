package com.taikang.healthcare.cis.dig.service;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.taikang.healthcare.cis.dig.bean.AliasBean;
import com.taikang.healthcare.cis.dig.common.Constants;

@Service
public class AliasAppService {
	@Autowired
	RestTemplate client;

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate()
	{
		//构造器中指定编码格式
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		messageConverters.add(new StringHttpMessageConverter(StandardCharsets.UTF_8));
		messageConverters.add(new AllEncompassingFormHttpMessageConverter());
		RestTemplate template = new RestTemplate(messageConverters);
		return template;
	}
	
	/**
	 * 诊断别名管理，疾病-别名列表
	 * @param name 名称
	 * 		  secondName 俗名
	 *        aliasFlag 非空为1
	 *        offset    分页-当前页
	 *        limit     分页-页面尺寸
	 * @return JSONArray
	 */
	@HystrixCommand(fallbackMethod = "hystrixqueryDicList")
	public JSONArray queryDicAliasList(String name,String secondName,String aliasFlag,Integer offset,Integer limit)
	{				
		ResponseEntity<String> response = client.getForEntity(Constants.selectDicAliasList+"/"+name+"/"+secondName+"/"+aliasFlag+"/"+offset+"/"+limit, String.class);						
		return  JSONArray.parseArray(response.getBody());
	}
	
	public JSONArray hystrixqueryDicList(String name,String secondName,String aliasFlag,Integer offset,Integer limit)
	{
		return null;
	}
	/**
	 * 根据诊断别名id查询别名及对应的诊断项信息
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "hystrixOneParam")
	public JSONObject selectAliasInfoById(String id){
		ResponseEntity<String> response = client.getForEntity(Constants.selectAliasInfoById+"/" + id,String.class);
		return (JSONObject) JSONObject.parse(response.getBody());
	}
	/**
	 * 根据诊断别名id修改别名信息
	 * @param id
	 * @param body
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "hystrixOneParam")
	public JSONObject updateAlias(String body){
		RequestEntity<String> request = new RequestEntity<String>(body, HttpMethod.PUT,
				URI.create(Constants.updateAlias));
		ResponseEntity<String> response = client.exchange(request, String.class);
		return (JSONObject) JSONObject.parse(response.getBody());
	}
	/**
	 * 根据诊断别名id删除别名信息
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "hystrixOneParam")
	public JSONObject deleteAlias(String id){
		ResponseEntity<String> response = client.exchange(Constants.deleteAlias+"/" + id,
				HttpMethod.DELETE, null, String.class);
		return (JSONObject) JSONObject.parse(response.getBody());
	}
	public JSONObject hystrixOneParam(String id){
		JSONObject jsonObject  =new  JSONObject();
		jsonObject.put("flag", false);
		jsonObject.put("message","调用服务出错了");
        return  jsonObject;
	}
	/**
	 * 新增诊断别名 
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "hystrixInsertAliasException")
	public boolean  insertAlias(AliasBean aliasBean){
		ResponseEntity<Boolean> response = client.postForEntity(Constants.insertAlias,aliasBean,Boolean.class);   
		return  Boolean.parseBoolean(response.getBody().toString());
	}
	//新增诊断别名 异常回调函数
	public  boolean hystrixInsertAliasException(AliasBean aliasBean){
		return  false;
		
	}
}
