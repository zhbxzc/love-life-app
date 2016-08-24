package com.taikang.healthcare.cis.dig.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.taikang.healthcare.cis.dig.bean.DiagnosisBean;
import com.taikang.healthcare.cis.dig.common.BaseController;
import com.taikang.healthcare.cis.dig.common.CommonResponse;
import com.taikang.healthcare.cis.dig.common.ConsantsCodeAndMessage;
import com.taikang.healthcare.cis.dig.service.DiagnosisAppService;


@RestController
@RequestMapping("/digapp")
@SuppressWarnings("all")
public class DiagnosisAppController extends BaseController {

	@Autowired
	private DiagnosisAppService diagnosisAppService;
	/**
	 * 诊断信息录入
	 * @param   encounterId        患者的就诊id
	 * @param   diagnosisJsonArray 就诊json数组
	 * @return 通用包装对象  成功 :ture  失败 :fasle
	 * @author itw_zhanghao01
	 */
	
	
	@RequestMapping(value = "/diagnosis/{encounterId}", method = RequestMethod.POST)
	public CommonResponse createDiagnosis(@PathVariable Integer encounterId , String  diagnosisJsonArray ){
	    JSONObject result = diagnosisAppService.createDiagnosis(encounterId, diagnosisJsonArray);
	    
	    if(result.get("flag").toString().equals("true")){
	    return	successReturn(true);
	    }
	    return errorReturn(ConsantsCodeAndMessage.DIAGNOSIS__CREATE_EXCEPTION_CODE,result.get("mesaage").toString());
	}
	
	
	/**
	 * 诊断内容校验
	 * @param diagnosisJsonArray 诊断内容json数组
	 * @return
	 */
	@RequestMapping(value = "/checkDiagnosis", method = RequestMethod.POST)
	public CommonResponse checkDiagnosis(@RequestBody String body)
	{	
		return successReturn(diagnosisAppService.checkDiagnosis(body));
	}


	/**
	 * 查询诊断信息
	 * @param encounterId 就诊Id
	 * @return CommonResponse
	 * 		   诊断信息
	 * 		   诊断状态：诊中：0，挂起：1，诊毕：2
	 */
	@RequestMapping(value = "/queryDiagnosisListByEncounterId/{encounterId}", method = RequestMethod.GET)
	public CommonResponse queryDiagnosisByByEncounterId(@PathVariable String encounterId ){
		JSONArray array = diagnosisAppService.queryDiagnosisListByEncounterId(encounterId);
		if(array==null){
			return errorReturn(ConsantsCodeAndMessage.DIAGNOSIS__SELECT_EXCEPTION_CODE, "查询失败了");
		}else{
			return successReturn(array);
		}
	}
	
	/**
	 * 查询诊断信息
	 * @param encounterId 就诊Id
	 * @return CommonResponse
	 */
	@RequestMapping(value = "/queryDiagnosisListByDeptId/{deptId}", method = RequestMethod.GET)
	public CommonResponse selectdiagnosisListByDeptId(@PathVariable String deptId ){
		JSONArray array = diagnosisAppService.selectdiagnosisListByDeptId(deptId);
		if(array==null){
			return errorReturn(ConsantsCodeAndMessage.DIAGNOSIS__SELECT_BY_DEPTID_EXCEPTION_CODE, "查询失败了");
		}else{
			return successReturn(array);
		}
	}
	
	
}
