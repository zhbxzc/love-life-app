package com.love.life;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * 边缘服务层的启动类
 * @author itw_huomb
 */
@EnableDiscoveryClient
@EnableCircuitBreaker
@SpringBootApplication
public class LoveOrigamiDigAppApplication
{
	/*@Bean
	Sampler sampler()
	{
		return span -> true;
	}*/
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
	public static void main(String[] args)
	{
		SpringApplication.run(LoveOrigamiDigAppApplication.class, args);
	}
}
