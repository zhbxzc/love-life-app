package com.taikang.healthcare.cis.dig.common;

/**
 * @author itw_zhanghao01
 * @version 1.0
 * @des 定义静态的常量编码,此处的常量编码,主要包括定义的一些编码(异常,特别的状态码等等)
 */
public class ConsantsCodeAndMessage {
	//编码定义格式    500 为服务通用异常,以500异常为开头.每个domain的服务层定为一个序号,以数字1开始,每个服务层递增,
	//每个服务层的接口(方法)定为一个异常,以数字1开始, 每个(方法递增).
	
	//DIAGNOSIS 服务
	//诊断服务创建失败 500+1+1   5001为诊断服务编码  
	public  static  final int  DIAGNOSIS__CREATE_EXCEPTION_CODE=50011;
	//诊断服务查询失败
	public  static  final int  DIAGNOSIS__SELECT_EXCEPTION_CODE=50012;	
	//诊断服务查询失败(使用deptId查询)
	public  static  final int  DIAGNOSIS__SELECT_BY_DEPTID_EXCEPTION_CODE=50013;	
	
	
	//ALIAS  服务
	//别名新增失败    500+2+1   5002为别名服务编码
	public  static  final int  ALIAS__INSERT_EXCEPTION_CODE=50021;
	//查询别名列表失败
	public static final int ALIAS_SELECT_EXCEPTION_CODE=50022;
	//删除别名列表失败
	public  static  final int  ALIAS_DELETE_EXCEPTION_CODE=50023;
	//更新别名列表失败
	public  static  final int  ALIAS_UPDATE_EXCEPTION_CODE=50024;
	//查询科室常用诊断失败
	public  static  final int  DIC_SELECTBYDEPTID_EXCEPTION_CODE=50031;
	//查询个人常用诊断失败
	public  static  final int  DIC_SELECTBYUSERID_EXCEPTION_CODE=50032;
	
}
