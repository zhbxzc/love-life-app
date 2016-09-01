package com.love.life.common;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.love.life.service.CommService;

/** 
* @author 作者 E-mail: 
* @version 创建时间：2016年8月26日 下午1:48:16 
* 类说明 
*/
@RestController
@RequestMapping("/commapp")
public class CommController extends BaseController{
	/**
	 * 
	* @Title: ConvertPy 
	* @Description: 汉字转五笔码和拼音码 
	* @param @param name
	* @param @return    设定文件 
	* @return CommonResponse    返回类型 
	* @throws
	 */
	@RequestMapping(value = "/love/life/pyjm/{name}", method = RequestMethod.GET)
	public CommonResponse ConvertPy(@PathVariable String name){
		return successReturn(CommService.convertPy(name));
	}
}
 

