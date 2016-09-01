console.info('加载','interfacesConfig.js');
//请求主机地址和端口号
var  host =window.location.host;
var  httpUrl="http://"+window.location.host+"/";

var  dev_menu_domain="http://"+host+"/" ;
var  domain = dev_menu_domain  ;
//请求客户信息的restful地址
var interfaces = {
	customers:httpUrl+"customerapp/customers",

};

