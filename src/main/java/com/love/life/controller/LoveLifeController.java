package com.love.life.controller;

import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.love.life.bean.PageBean;
import com.love.life.bean.Paperinnovate;
import com.love.life.common.BaseController;
import com.love.life.common.CommonResponse;
import com.love.life.common.ConsantsCodeAndMessage;
import com.love.life.service.LoveLifeService;


@RestController
@RequestMapping("/origamiapp")
public class LoveLifeController  extends BaseController{
	@Resource
	private LoveLifeService loveLifeService;
	/**
	 * 
	* @Title: register 
	* @Description: 注册信息 
	* @param @param didicInfo
	* @param @return    设定文件 
	* @return CommonResponse    返回类型 
	* @throws
	 */
	@RequestMapping(value = "/origami/oriinns", method = RequestMethod.POST)
	public CommonResponse register(@RequestBody String origamiinnoInfo){
		return successReturn(loveLifeService.register(origamiinnoInfo));
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
	@RequestMapping(value = "/origami/oriinns", method = RequestMethod.GET)
	public CommonResponse search(Paperinnovate paperinnovate){
		if (paperinnovate.getPageIndex()==null) {
			paperinnovate.setPageIndex(1);
		}
		if (paperinnovate.getPageSize()==null) {
			paperinnovate.setPageSize(10);
		}
		String res = loveLifeService.search(paperinnovate);
		String count=loveLifeService.searchCount(paperinnovate);
		PageBean<String> pagebean=new PageBean<String>();
		if(count!=null&&!"".equals(count)){
			pagebean.setCount(Integer.parseInt(count));
		}else{
			pagebean.setCount(0);
		}
		
		pagebean.setRows(res);
		if (res==null) {
			return errorReturn(ConsantsCodeAndMessage.ALIAS_SELECT_EXCEPTION_CODE, "查询失败了");
		} else {
			return successReturn(JSON.toJSONString(pagebean));
		}
	}
	
	/**
	 * 
	* @Title: getById 
	* @Description: 根据ID查询信息 
	* @param @param id
	* @param @return    设定文件 
	* @return CommonResponse    返回类型 
	* @throws
	 */
	@RequestMapping(value = "/origami/oriinns/{id}", method = RequestMethod.GET)
	public CommonResponse getById(@PathVariable String id ){
		return successReturn(loveLifeService.getById(id));
	}
	
	/**
	 * 
	* @Title: delete 
	* @Description: 根据Id删除信息 
	* @param @param id
	* @param @return    设定文件 
	* @return CommonResponse    返回类型 
	* @throws
	 */
	@RequestMapping(value = "/origami/oriinns/{id}", method = RequestMethod.DELETE)
	public CommonResponse delete(@PathVariable String id ){
		return successReturn(loveLifeService.delete(id));
	}
	
	/**
	 * 
	* @Title: alter 
	* @Description: 修改信息
	* @param @param origamiinnoInfo
	* @param @return    设定文件 
	* @return CommonResponse<?>    返回类型 
	* @throws
	 */
	@RequestMapping(value = "/origami/oriinns/{id}", method = RequestMethod.PUT)
	public CommonResponse<?> alter(@RequestBody String origamiinnoInfo){
		return successReturn(loveLifeService.alter(origamiinnoInfo));
	}
		
}
