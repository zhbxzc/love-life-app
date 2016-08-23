package com.taikang.healthcare.cis.dig.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.taikang.healthcare.cis.dig.common.Constants;

@Service("userAppService")
public class UserAppService
{
	@Autowired
	RestTemplate client;

	/*
	 * @HystrixCommand为断路器注解，当此方法报错后，会走fallbackMethod指定的方法，具体可根据业务来判断方法上是否增加此注解。
	 */
	@HystrixCommand(fallbackMethod = "hystrixNoParam")
	public List<Map<String,Object>> queryAllRole()
	{
		ResponseEntity<List> response = client.getForEntity(Constants.queryAllUser, List.class);

		return response.getBody();
	}


	public List<Map<String,Object>> hystrixNoParam()
	{
		return null;
	}
}
