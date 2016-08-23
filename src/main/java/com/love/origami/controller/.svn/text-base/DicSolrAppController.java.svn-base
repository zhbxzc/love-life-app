package com.taikang.healthcare.cis.dig.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.taikang.healthcare.cis.dig.common.BaseController;
import com.taikang.healthcare.cis.dig.common.CommonResponse;
import com.taikang.healthcare.cis.dig.common.PatternUtil;

@RestController
@RequestMapping("/dicsolrapp")
public class DicSolrAppController extends BaseController{
	
   @Autowired
   private HttpSolrClient  HttpSolrClient;
   
    /**
     * 根据内容检索疾病ICD编码
     * @param context  检索内容
     * @param start    偏移量
     * @param rows     记录数
     * @throws SolrServerException
     * @throws IOException
     */
   @RequestMapping(value = "/DicIndex", method = RequestMethod.GET)
   public CommonResponse selectIDCIndex(@RequestParam String context, @RequestParam Integer start,@RequestParam Integer rows) throws SolrServerException, IOException{
	   
	   Map<String,Object> result =new HashMap<String,Object>();  
	   List<Map<String,Object>> resultList =new ArrayList<Map<String,Object>>();
	   
	   
	   if(context==null||context.equals("")){
		   result.put("total", 0);
		   result.put("data", resultList);
		   return successReturn(result);
	   }
	   SolrQuery solrQuery =new SolrQuery();
	   //启动统计
	   solrQuery.setFacet(true);
	   solrQuery.set("start", start);//偏移量
	   solrQuery.set("rows", rows);//条数
	   //如果是汉语的话
	   if(PatternUtil.isStringContinsChiness(context)){
		   solrQuery.set("df", "name");//查询满足name的字段的信息
	   }else if(PatternUtil.isEnglishChar(context)){
		   solrQuery.set("df", "spell_no");//按拼音查询
	   }else if(PatternUtil.isEnglishNumber(context)){
		   solrQuery.set("df", "icd_code");//按IDC编码查询 
	   }
	   
	      //转译
	     context=translationSpcilStr(context);
		   /*
		    * "*"进行模糊查询,匹配之后的任意多个;
		    * "?"为占位,可多个占位.类似于"A00??" ;
		    * "~" 为模糊检索,例如 A97~ , 可能搜索到 B97 ,A97.0 (也可以检索相似度80%的A97~0.8)
		    *  针对字段进行查询的话,我们需要增加模糊查询.
		    */
	       solrQuery.set("q", context+"*");
	  QueryResponse queryResponse = HttpSolrClient.query(solrQuery);
	  SolrDocumentList  solrDocumentList =  queryResponse.getResults();
	  //获取文档
	  long number = solrDocumentList.getNumFound();
//	  result.put("total", number);
	  for (SolrDocument solrDocument : solrDocumentList) {
		  Map<String, Object> map = solrDocument.getFieldValueMap();
		  resultList.add(map);
	  }
	  JSONObject jo=new JSONObject();
	  jo.put("total", number);
	  JSONArray arr=new JSONArray();
	  for(Map<String,Object> map:resultList){
	        JSONObject obj = new JSONObject();
	        obj.put("icd_code", map.get("icd_code"));
	        obj.put("created_time", map.get("created_time"));
	        obj.put("spell_no", map.get("spell_no"));
	        obj.put("name", map.get("name"));
	        obj.put("wubi_no", map.get("wubi_no"));
	        obj.put("id", map.get("id"));
	        obj.put("_version_", map.get("_version_"));
	        arr.add(obj);
	  }
	  jo.put("data", arr);
	  return successReturn(jo);
   }
   
   /**
    * 在进行查询solr的时候,我们需要将solr中定义的特殊字符进行转译
    * 对文本中保护特殊字符的信息进行转译如下特殊字符    + - & | ! () [] ^  ~ ? : " \ 
    * @param string
    * @return
    */
   private String translationSpcilStr(String s){
	   
	   StringBuilder sb =new StringBuilder();
	   for (int i = 0; i < s.length(); i++) {
		   char c =s.charAt(i);
		   if(c =='\\'||c=='+'||c=='-'||c=='!'||c=='('||c==')'||
				   c==':'||c=='^'||c=='['||c==']'||c=='\"'||c=='{'||c=='}'||c=='~'
				   ||c=='*'||c=='?'||c=='|'||c=='&'||c==';'||c=='/'||Character.isWhitespace(c)){
			   
			   sb.append('\\');
		   }
		   sb.append(c);
	   }
	   return sb.toString();
	}
	
}
