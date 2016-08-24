package com.taikang.healthcare.cis.dig.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.taikang.healthcare.cis.dig.bean.DiagnosisBean;
import com.taikang.healthcare.cis.dig.common.CommonResponse;
import com.taikang.healthcare.cis.dig.common.ConsantsCodeAndMessage;
import com.taikang.healthcare.cis.dig.common.Constants;

@Service("diagnosisAppService")
@SuppressWarnings("all")
public class DiagnosisAppService {
	
	@Autowired
	private  RestTemplate client;
	
	@HystrixCommand(fallbackMethod = "hystrixNoParam")
	public JSONObject createDiagnosis(Integer encounterId,String diagnosisJsonArray){
		ResponseEntity<String> response = client.postForEntity(Constants.createDiagnosis+"/"+encounterId,diagnosisJsonArray,String.class);
		//获取返回结果.
		return (JSONObject) JSONObject.parse(response.getBody().toString());
		
	}
	/**
	 * 调用服务失败  
	 * @param encounterId
	 * @param diagnosisJsonArray
	 * @return  诊断内容校验
	 * @param digs
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "hystrixCheckDiagnosis")
	public JSONObject checkDiagnosis(String body)
	{
		ResponseEntity<String> response = client.postForEntity(Constants.checkDiagnosis+"/",body,String.class);
		return (JSONObject) JSONObject.parse(response.getBody().toString());
	}
	
	/**
	 * 诊断显示查询
	 * @param encounterId 就诊ID
	 * @return JSONArray
	 */
	@HystrixCommand(fallbackMethod = "hystrixNoParamString")
	public JSONArray queryDiagnosisListByEncounterId(String encounterId)
	{
		ResponseEntity<String> response = client.getForEntity(Constants.queryDiagnosisListByEncounterId+"/"+encounterId, String.class);								
		return  JSONArray.parseArray(response.getBody());
	}
	/**
	 * 通用异常回调方法 
	 */
	public JSONObject hystrixNoParam(Integer encounterId,String diagnosisJsonArray){
		JSONObject jsonObject  =new  JSONObject();
		jsonObject.put("flag", false);
		jsonObject.put("mesaage","调用服务错处了");
        return  jsonObject;
	}
	/**
	 * 通用异常回调方法 
	 */
	public JSONArray hystrixNoParamString(String encounterId)
	{	
		return null;
	}
	
	/**
	 * 通用异常回调方法 
	 */
	public JSONObject hystrixCheckDiagnosis(String body){
		JSONObject jsonObject  =new  JSONObject();
		jsonObject.put("flag", false);
		jsonObject.put("mesaage","调用服务出错了");
        return  jsonObject;
	}
	
	 /**
     * 根据科室ID，查询该科室常用诊断
     * @param deptId  就诊id
     * @return List<Map<String, Object>> 诊断结果列表
     */
	@HystrixCommand(fallbackMethod = "hystrixNoParamString")
	public JSONArray selectdiagnosisListByDeptId(String deptId)
	{
		ResponseEntity<String> response = client.getForEntity(Constants.selectdiagnosisListByDeptId+"/"+deptId, String.class);								
		return  JSONArray.parseArray(response.getBody());
	}
}
