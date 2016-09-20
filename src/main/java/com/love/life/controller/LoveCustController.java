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
import com.love.life.bean.Customer;
import com.love.life.common.BaseController;
import com.love.life.common.CommonResponse;
import com.love.life.common.ConsantsCodeAndMessage;
import com.love.life.service.LoveCustService;
import com.love.life.service.LoveLifeService;


@RestController
@RequestMapping("/custapp")
public class LoveCustController  extends BaseController{
	@Resource
	private LoveCustService loveCustService;
			
	/**
	 * 注册
	 * FIXME 
	 * @param 信息的JSON串
	 * @return 注册结果
	 */
	@RequestMapping(value = "/customers", method = RequestMethod.POST)
	public CommonResponse register(@RequestBody String Info){
	
		return successReturn(loveCustService.register(Info));
	}
	
	
	/**
	 * 根据查询条件获取信息
	 * @return 信息集合
     * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/customers", method = RequestMethod.GET)
	public CommonResponse search(Customer customer){
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
	
	/**
	 * 根据ID获取一个信息
	 * @param id
	 * @return 
	 */
	@RequestMapping(value = "/customers/{id}", method = RequestMethod.GET)
	public CommonResponse getById(@PathVariable String id ){
		return successReturn(loveCustService.getById(id));
	}
	
	/**
	 * 根据ID删除一个信息
	 * @param id
	 * @return 
	 */
	@RequestMapping(value = "/customers/{id}", method = RequestMethod.DELETE)
	public CommonResponse delete(@PathVariable String id ){
		return successReturn(loveCustService.delete(id));
	}
	
	/**
	 * FIXME 修改信息 
	 * @param Info
	 * @return 
	 */
	@RequestMapping(value = "/customers/{id}", method = RequestMethod.PUT)
	public CommonResponse<?> alter(@RequestBody String Info){
		return successReturn(loveCustService.alter(Info));
	}
}
