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
import com.love.life.service.LoveLifeService;


@RestController
@RequestMapping("/origamiapp")
public class LoveLifeController  extends BaseController{
	@Resource
	private LoveLifeService loveLifeService;
	/**
	 * 注册
	 * FIXME 
	 * @param 信息的JSON串
	 * @return 注册结果
	 */
	@RequestMapping(value = "/oriinns", method = RequestMethod.POST)
	public CommonResponse register(@RequestBody String Info){
		return successReturn(loveLifeService.register(Info));
	}
	
	
	/**
	 * 根据查询条件获取信息
	 * @return 信息集合
     * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/oriinns", method = RequestMethod.GET)
	public CommonResponse search(Paperinnovate paperinnovate){
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
	 * 根据ID获取一个信息
	 * @param id
	 * @return 
	 */
	@RequestMapping(value = "/oriinns/{id}", method = RequestMethod.GET)
	public CommonResponse getById(@PathVariable String id ){
		return successReturn(loveLifeService.getById(id));
	}
	
	/**
	 * 根据ID删除一个信息
	 * @param id
	 * @return 
	 */
	@RequestMapping(value = "/oriinns/{id}", method = RequestMethod.DELETE)
	public CommonResponse delete(@PathVariable String id ){
		return successReturn(loveLifeService.delete(id));
	}
	
	/**
	 * FIXME 修改信息 
	 * @param Info
	 * @return 
	 */
	@RequestMapping(value = "/oriinns/{id}", method = RequestMethod.PUT)
	public CommonResponse<?> alter(@RequestBody String Info){
		return successReturn(loveLifeService.alter(Info));
	}
		
}
