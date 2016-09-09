
$(function(){
	$("#links").load("../compage/tipmodal.html");
	initDropDown();
	$('#searchcust').bootstrapTable({
		pageList:[5,10,25,100],
		pagination:true,
		sidePagination:"server",
		dataField:'rows',
		queryParams:getParam,
		showToggle:false,
		cardView:false,
		silent:true,
		columns : [ {				
			field : "id",
			title : "编号",
			visible:false
		}, {
			field : "name",
			title : "姓名"
		}, {
			field : "birthday",
			title : "出生日期",
			formatter: dateFormatter	
		},{
			field : "idCardNo",
			title : "身份证号"
		}, {
			field : "email",
			title : "电子邮箱"
		}, {
			field : "registOrgId",
			title : "注册机构ID"
		}, {
			field : "operate",
			title : "操作",
	        align: 'center',
            events: operateEvents,
            formatter: operateFormatter
		}, ]
	});
	$('#searchcust').bootstrapTable('hideLoading');
});
function getParam(params){
	var temp = {
			"limit":params.limit,
			"offset":params.offset,
			 id:$.trim($("#id").val()),
	         name:$.trim($('#name').val()),
	         birthday:$.trim($('#birthday').val()),
	         idCardNo:$.trim($('#idCardNo').val()),
	         email:$.trim($('#email').val()),	
	         registOrgId:$.trim($('#registOrgId').val())
	 };
	return temp;
}


$("#searchcustForm").html5Validate(function() {
	search();	
});
function search(){
	var email = $('.email');
	var idCardNo = $('.idCardNo');
    var temp = {
   			 id:$("#id").val(),
   	         name:$('#name').val(),
   	         birthday:$('#birthday').val(),
   	         idCardNo:$('#idCardNo').val(),
   	         email:$('#email').val(),	
   	         registOrgId:$('#registOrgId').val(),
    }
         $('#searchcust').bootstrapTable('refresh',{url : interfaces.searchcust,query:temp});
}
function rowStyle(row, index) {
    var classes = ['active', 'success', 'info', 'warning', 'danger'];

    if (index % 2 ==1) {
        return {
            classes: classes[2]
        };
    }
    return {};
}
window.operateEvents = {
        'click .edit': function (e, value, row, index) {
        	altercust(row.id);
        }
    };
function operateFormatter(value, row, index) {
    return [
       '<a class="edit" href="javascript:void(0)" title="详情">',
       '详情 ',
       '</a>  '
    ].join('');
}

function altercust(id) {
	window.location.href="editinfo.html?id="+id;
}

function dateFormatter(value, row, index) {
	
	if(value!=null&&value!=''&&typeof(value)!='undefined'){
		return value.substr(0,10);
	}else{
		return "";
	}
    
}
/*==========顾客信息注册 start==========*/
$("#registercustform").html5Validate(function() {	 
	  regcust();	
  }, {
	   // novalidate: false	
   });

function setImg(obj){
	var file=obj.files[0];
	//type: "image/bmp"
	//name: "图片.bmp"
	//判断上传文件类型
	var picPath=file.name;
    var type =picPath.substring(picPath.lastIndexOf(".") + 1, picPath.length).toLowerCase();
	if (type == "") {
		return false;
	} else if (type != "bmp" && type != "png" && type != "gif"
			&& type != "jpg" && type != "jpeg") {
		alert("图片限于png,gif,jpeg,jpg格式");
		return false;
	}
	if(file.size/1024/1024>2 ){
		 alert("上传的文件大小不能超过2M！");
	}
	var reader=new FileReader();
	reader.readAsDataURL(file);
	reader.onload=function(e){
		photobef=this.result;//转成base64位
		photobef=photobef.substring(photobef.indexOf(",")+1,photobef.length);
		//console.log(photo);
		$("#imgdiv img").attr("src",this.result);
		$("#photo").val(photobef);	
	}  
	//模态框关闭
	$('#popupContact').modal('hide');

}

function regcust(){	
	$.ajax({
	    url:interfaces.editcust,
		type : "POST",
		data : $("#registercustform").serialize(),
		success : function(result) {
		
			if(result.result){
				showTip("success",result.mesg);
				if($("#addAnother").prop("checked")){
					$("#registercustform")[0].reset();
					$("#addAnother").prop("checked",true);
				}else{
					$('#registerCust').modal('hide');
					$("#registercustform")[0].reset();
				}
				addRefreshTable(result.id);
			}else{
				showTip("warning",result.mesg);
			}
		}
	});
}
function addRefreshTable(id){
	var temp = {
		     id:id
		}
	    $('#searchcust').bootstrapTable('refresh',{url:interfaces.editcust,query:temp});
}
//清空注册模态框
function clearModal(){
	$("#registercustform")[0].reset();
	$("img").attr("src", ""); 
/*	$("img").removeAttr("src"); */
}
/*----------初始化下拉数据-----start-------*/
function initDropDown(){
	for(i=0;i<$("select[dropdown]").length;i++){
		var id = $($("select[dropdown]")[i]).attr("id");
		var datasource = $($("select[dropdown]")[i]).attr("datasource");
		$("#"+id).empty();
		$("#"+id).append("<option value=''></option>");
		initobjectid(datasource,id);
	}
}
function initobjectid(datasource,downid){
	$.ajax({
		url : interfaces.searchBasecodeByObjectId + datasource +"/basecodes",
		type : "GET",
		data : {},
		async : false,
		dataType : "JSON",
		success : function(result) {
			if (result != null) {
				for(var i =0;i<result.length;i++){ 
					$("#"+downid).append("<option value='"+result[i].id+"'>"+result[i].name+"</option>");
				}
			}
		},
		error : function() {
			showTip("danger","获取下拉数据失败！");
		}
	});
}	
/*----------初始化下拉数据-----end-------*/
/*==========顾客信息注册 end==========*/



var x = $("#registerCust").offset();
$('#popupContact').modal().css({
    width: 1500,
    top:x.top-window.pageYOffset+5,
/*    'margin-left': function () {
       return -($(this).width() / 2);
   }*/
}); 


