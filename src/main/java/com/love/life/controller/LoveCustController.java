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
import com.love.life.bean.customer;
import com.love.life.common.BaseController;
import com.love.life.common.CommonResponse;
import com.love.life.common.ConsantsCodeAndMessage;
import com.love.life.service.LoveCustService;
import com.love.life.service.LoveLifeService;


@RestController
@RequestMapping("/customerapp")
public class LoveCustController  extends BaseController{
	@Resource
	private LoveCustService loveCustService;
			
	/**
	 * 
	* @Title: search 
	* @Description: 根据条件查询客户信息
	* @param @param paperinnovate
	* @param @return    设定文件 
	* @return CommonResponse    返回类型 
	* @throws
	 */
	@RequestMapping(value = "/customers", method = RequestMethod.GET)
	public CommonResponse search(customer customer){
		if (customer.getPageIndex()==null) {
			customer.setPageIndex(1);
		}
		if (customer.getPageSize()==null) {
			customer.setPageSize(10);
		}
		
		String res = loveCustService.search(customer);
		String count=loveCustService.searchCount(customer);
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
	@RequestMapping(value = "/customers/{id}", method = RequestMethod.DELETE)
	public CommonResponse delete(@PathVariable String id ){
		return successReturn(loveCustService.delete(id));
	}	
	@RequestMapping(value = "/customers/{id}", method = RequestMethod.PUT)
	public CommonResponse<?> alter(@RequestBody String customer){
		return successReturn(loveCustService.alter(customer));
	}
	@RequestMapping(value = "/customers", method = RequestMethod.POST)
	public CommonResponse register(@RequestBody String customer){
		return successReturn(loveCustService.register(customer));
	}
}
