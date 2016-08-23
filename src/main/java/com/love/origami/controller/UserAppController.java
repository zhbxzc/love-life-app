package com.taikang.healthcare.cis.dig.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.taikang.healthcare.cis.dig.common.BaseController;
import com.taikang.healthcare.cis.dig.common.CommonResponse;
import com.taikang.healthcare.cis.dig.service.LuceneService;
import com.taikang.healthcare.cis.dig.service.UserAppService;


/**
 * 边缘服务，聚合数据的控制层，统一访问路径以/userapp/开头，数据传递格式：json字符串
 * @author itw_huomb
 */
@RestController
@RequestMapping("/digapp")
public class UserAppController extends BaseController {

	@Autowired
	UserAppService userAppService;
	@Autowired
	LuceneService luceneService;

	@RequestMapping(value = "/role", method = RequestMethod.GET)
	public CommonResponse create()
	{
		return successReturn(userAppService.queryAllRole());
	}
    
}
