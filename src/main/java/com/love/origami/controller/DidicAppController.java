package com.taikang.healthcare.cis.dig.controller;

import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.taikang.healthcare.cis.dig.bean.DidicVo;
import com.taikang.healthcare.cis.dig.bean.PageBean;
import com.taikang.healthcare.cis.dig.common.BaseController;
import com.taikang.healthcare.cis.dig.common.CommonResponse;
import com.taikang.healthcare.cis.dig.common.ConsantsCodeAndMessage;
import com.taikang.healthcare.cis.dig.service.DidicAppService;


@RestController
@RequestMapping("/digapp-qyl")
public class DidicAppController  extends BaseController{
	@Resource
	private DidicAppService didicAppService;
	/**
	 * 注册诊断
	 * FIXME 
	 * @param didicInfo诊断信息的JSON串
	 * @return 注册结果
	 */
	@RequestMapping(value = "/dig/didics", method = RequestMethod.POST)
	public CommonResponse register(@RequestBody String didicInfo){
		return successReturn(didicAppService.register(didicInfo));
	}
	
	
	/**
	 * 根据查询条件获取诊断字典信息
	 * @param didic
	 * 		key         		value
	 * 		id					诊断字典ID
	 * 		icd_code			ICD编码
	 * 		icd_code_add		ICD附加码
	 * 		name				ICD名称
	 * 		symptom_flag		症状标志
	 * 		infectious_flag		传染病标志
	 * 		spell_no			拼音码
	 * 		wubi_no				五笔码
	 * @return 诊断字典信息集合
     * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/dig/didics", method = RequestMethod.GET)
	public CommonResponse search(DidicVo didic){
		if (didic.getPageNo()==null) {
			didic.setPageNo(1);
		}
		if (didic.getPageSize()==null) {
			didic.setPageSize(10);
		}
		String res = didicAppService.search(didic);
		String count=didicAppService.searchCount(didic);
		PageBean<String> pagebean=new PageBean<String>();
		if(count!=null&&!"".equals(count)){
			pagebean.setTotal(Integer.parseInt(count));
		}else{
			pagebean.setTotal(0);
		}
		
		pagebean.setRows(res);
		if (res==null) {
			return errorReturn(ConsantsCodeAndMessage.ALIAS_SELECT_EXCEPTION_CODE, "查询失败了");
		} else {
			return successReturn(JSON.toJSONString(pagebean));
		}
	}
	@RequestMapping(value = "/dig/didics/{id}", method = RequestMethod.GET)
	public CommonResponse getById(@PathVariable String id ){
		return successReturn(didicAppService.getById(id));
	}
	
	/**
	 * 根据诊断字典ID删除一个诊断字典信息
	 * FIXME 
	 * @param map
	 * @return 
	 */
	@RequestMapping(value = "/dig/didics/{id}", method = RequestMethod.DELETE)
	public CommonResponse delete(@PathVariable String id ){
		return successReturn(didicAppService.delete(id));
	}
	
	@RequestMapping(value = "/dig/didics/{id}", method = RequestMethod.PUT)
	public CommonResponse<?> alter(@RequestBody String didicInfo)
	{
		return successReturn(didicAppService.alter(didicInfo));
	}
}
