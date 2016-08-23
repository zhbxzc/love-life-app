package com.taikang.healthcare.cis.dig.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.taikang.healthcare.cis.dig.common.BaseController;
import com.taikang.healthcare.cis.dig.common.CommonResponse;
import com.taikang.healthcare.cis.dig.common.ConsantsCodeAndMessage;
import com.taikang.healthcare.cis.dig.service.DicAppService;
import com.taikang.healthcare.cis.dig.service.LuceneService;
@RestController
@RequestMapping("/dicapp")
public class DicAppController extends BaseController {

	@Autowired
	DicAppService dicAppService;
	@Autowired
	LuceneService luceneService;
	/**
	 * 根据科室id查询科室常用诊断
	 * @param deptId
	 * @return
	 */
	@RequestMapping(value = "/selectItemsByDeptId/{deptId}", method = RequestMethod.GET)
	public CommonResponse selectItemsByDeptId(@PathVariable String deptId){
		JSONArray result =dicAppService.selectItemsByDeptId(deptId);
		if(result==null){
			return errorReturn(ConsantsCodeAndMessage.DIC_SELECTBYDEPTID_EXCEPTION_CODE, "服务出错了"); 
		}
		return successReturn(result);
	}
	/**
	 * 根据用户id查询个人常用诊断
	 * @param deptId
	 * @return
	 */
	@RequestMapping(value = "/selectItemsByUserId/{userId}", method = RequestMethod.GET)
	public CommonResponse selectItemsByUserId(@PathVariable String userId){
		JSONArray result = dicAppService.selectItemsByUserId(userId);
		if(result==null){
			return errorReturn(ConsantsCodeAndMessage.DIC_SELECTBYUSERID_EXCEPTION_CODE, "服务出错了"); 
		}
		return successReturn(result);
	}
	
	/**
	 * 通过token从redis中获取用户下的所有部门
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "/getValuesFromRedisByToken/{token}", method = RequestMethod.GET)
	@ResponseBody
	public String getValuesFromRedisByToken(@PathVariable String token) {
		 return dicAppService.getValuesFromRedisByToken(token);
	}
	
	/**
	 * 新增科室常用诊断
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/favoriteDept", method = RequestMethod.POST)
    public CommonResponse  createFavoriteDept (@RequestBody String body){
		return successReturn(dicAppService.createFavoriteDept(body));
    }
	
	/**
	 * 删除科室诊断模板中的一条记录
	 * @param 
	 * @return
	 */
    @RequestMapping(value = "/deletFavoriteDept", method = RequestMethod.POST)
    public CommonResponse  deleteFavoriteDept (@RequestBody String body){
		return successReturn(dicAppService.deleteFavoriteDept(body));
    }
    
    /**
	 * 新增个人常用诊断
	 * @param 
	 * @return
	 */
    @RequestMapping(value = "/favoriteUser", method = RequestMethod.POST)
    public CommonResponse  createFavoriteUser (@RequestBody String body){
		return successReturn(dicAppService.createFavoriteUser(body));
    }
    
    /**
	 * 删除个人诊断模板中的一条记录
	 * @param 
	 * @return
	 */
    @RequestMapping(value = "/deletFavoriteUser", method = RequestMethod.POST)
    public CommonResponse  deleteFavoriteUser (@RequestBody String body){
		return successReturn(dicAppService.deleteFavoriteUser(body));
    }
    /**
	 * 创建疾病的索引文件
	 * @param 
	 * @return
	 */
    @RequestMapping(value = "/createDicIndexFile", method = RequestMethod.GET)
    public CommonResponse createDicIndexFile(){
    	//return successReturn(luceneService.createIndexFile(userAppService.queryAllRole()));
    	return null;
    }
    /**
	 * 搜索疾病的索引文件
	 * @param 
	 * @return
	 */
    @RequestMapping(value = "/selectDicIndexFile", method = RequestMethod.POST)
    public CommonResponse selectDicIndexFile(String content, int pageIndex){
    	return successReturn(luceneService.txtFileSearcher(content,pageIndex));
    }
	/**
     * 
     * 获取登录用户信息及选中的部门
     * @param token
     * @return
     */
	@RequestMapping(value = "/getUserDeptByToken/{token}", method = RequestMethod.POST)
	@ResponseBody
	public String getUserDeptByToken(@PathVariable String token) {
	     String url = "http://10.136.25.20:8080/userapp/api/getUserDeptByToken";
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
}
