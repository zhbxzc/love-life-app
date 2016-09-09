package com.love.life.common;


public class Constants {
	
	private static String origamiPath="love-origami-domain";
	private static String custPath="cloud-cust-domain";
	   //折纸信息新增
		public static final String registerOrigami="http://"+origamiPath+"/origami/oriinns";
		//折纸信息删除
		public static final String deleteOrigami="http://"+origamiPath+"/origami/oriinns";
		//折纸信息查询
		public static final String searchOrigami="http://"+origamiPath+"/origami/oriinns";
		//折纸信息查询
		public static final String searchCountOrigami="http://"+origamiPath+"/origami/oriinns/counts";
		//折纸信息更新
		public static final String alterOrigami="http://"+origamiPath+"/origami/oriinns";
		
		//客户信息查询
		public static final String searchCust="http://"+custPath+"/cust/api/v1/customers";
		//客户信息数量查询
		public static final String searchCountCust="http://"+custPath+"/cust/api/v1/customers/count";
		//客户信息删除
		public static final String deleteCust="http://"+custPath+"/cust/api/v1/customers";
		public static final String registerCust="http://"+custPath+"/cust/api/v1/customers";
		public static final String alterCust="http://"+custPath+"/cust/api/v1/customers";
		

}
