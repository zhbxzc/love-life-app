package com.love.life.service;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.love.life.common.HanToWB;
import com.love.life.common.Pyjc;

/** 
* @author 作者 E-mail: 
* @version 创建时间：2016年8月26日 下午1:46:24 
* 类说明 
*/
public class CommService {
	/**
	 * 
	* @Title: convertPy 
	* @Description: 汉字转五笔和拼音
	* @param @param name
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	
	public static String convertPy(String name) {
		Map<String, Object> map = new HashMap<String, Object>();
		String pyjc = Pyjc.getAllFirstLetter(name);
		String wubi = HanToWB.getWBCode(name);
		map.put("pyjc", pyjc);
		map.put("wubi", wubi);
		return JSONObject.toJSONString(map);
	}
}
 

