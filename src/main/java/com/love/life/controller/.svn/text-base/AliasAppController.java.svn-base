package com.taikang.healthcare.cis.dig.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.taikang.healthcare.cis.dig.bean.AliasBean;
import com.taikang.healthcare.cis.dig.common.BaseController;
import com.taikang.healthcare.cis.dig.common.CommonResponse;
import com.taikang.healthcare.cis.dig.common.ConsantsCodeAndMessage;
import com.taikang.healthcare.cis.dig.service.AliasAppService;

@RestController
@RequestMapping("/aliasApp")
public class AliasAppController extends BaseController{
	@Autowired
	AliasAppService aliasAppService;
	
	/**
	 * 诊断别名管理，疾病-别名列表
	 * @param name         名称
	 * 		  secondName   俗名
	 * 		  aliasFlag    是否选中非空俗名，1为选中，非1 未选中
	 * 		  offset 	      分页-起始索引		
	 * 		  limit        分页-页面尺寸
	 * @return CommonResponse 包含俗名列表，及用于分页的total
	 */
	@RequestMapping(value = "/selectAlias/{aliasFlag}", method = RequestMethod.GET)
	public CommonResponse selectDicAliasList(String name,String secondName,
			@PathVariable String aliasFlag,Integer offset,Integer limit) {		
		offset=(offset==null?0:offset);
		limit=(limit==null?8:limit);
		name=("".equals(name.trim())?null:name.trim());
		secondName=("".equals(secondName.trim())?null:secondName.trim());
		JSONArray res = aliasAppService.queryDicAliasList(name, secondName,aliasFlag,offset,limit);	
		if (res==null) {
			return errorReturn(ConsantsCodeAndMessage.ALIAS_SELECT_EXCEPTION_CODE, "查询失败了");
		} else {
			return successReturn(res);
		}

	}
	/**
	 * 根据诊断别名id查询别名及对应的诊断项信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/alias/{id}", method = RequestMethod.GET)
	public CommonResponse selectAliasInfoById(@PathVariable String id){
		JSONObject result = aliasAppService.selectAliasInfoById(id);
		if(result.get("flag")!=null&&result.get("flag").toString().equals("false")){
			return errorReturn(ConsantsCodeAndMessage.ALIAS_SELECT_EXCEPTION_CODE, result.get("message").toString()); 
		}
		return successReturn(result);
	}
	/**
	 * 根据诊断别名id修改别名信息
	 * @param id
	 * @param body
	 * @return
	 */
	@RequestMapping(value = "/alias", method = RequestMethod.PUT)
	public CommonResponse updateAlias(@RequestBody String body){
		JSONObject result = aliasAppService.updateAlias(body);
		if(result.get("flag")!=null&&result.get("flag").toString().equals("false")){
			return errorReturn(ConsantsCodeAndMessage.ALIAS_UPDATE_EXCEPTION_CODE, result.get("message").toString()); 
		}
		return successReturn(result);
	}
	/**
	 * 根据诊断别名id删除别名信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/alias/{id}", method = RequestMethod.DELETE)
	public CommonResponse deleteAlias(@PathVariable String id){
		JSONObject result =aliasAppService.deleteAlias(id);
		if(result.get("flag")!=null&&result.get("flag").toString().equals("false")){
			return errorReturn(ConsantsCodeAndMessage.ALIAS_DELETE_EXCEPTION_CODE, result.get("message").toString()); 
		}
		return successReturn(result);
	}
	
	
	/**
	 * 新增诊断别名
	 * @return  
	 */
	@RequestMapping(value = "/alias", method = RequestMethod.POST)
	public  CommonResponse insertAlias(AliasBean aliasBean){
		Boolean flag =aliasAppService.insertAlias(aliasBean);
		if(flag){
			return successReturn(flag);
		}
		return errorReturn(ConsantsCodeAndMessage.ALIAS__INSERT_EXCEPTION_CODE,"服务出错了");
	}
}
