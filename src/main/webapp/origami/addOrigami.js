var app = angular.module("myApp",[]);
app.controller("addOrigamiController", function($scope,$http){
	var ue = UE.getEditor('editor');
	///新增方法开始*************
	 /* var htmlContent=UE.getEditor('editor').getContent();*/
   
	$scope.addOrigami=function(){
		   var title=$("#title").val();
		      var typeId=$("#typeId").val();
		      console.log(title);
		      if(title.trim()==""){
		      	alert("标题不能为空");
		      	return;
		      }
		      var st={
		          		"title":title,
		          		"typeId":typeId,
		          		"content":""
		      }
		console.log(st);
		$http({
			contentType:'application/text;charset=UTF-8',
			data:JSON.stringify(st),
			method:'POST',
			url:"http://"+window.location.host+"/origamiapp/origami/oriinns"
		    })
			.success(function(data){
				json=JSON.parse(data.data);
				console.log(json);
				if(json.result){
					alert("新增成功");
				
					//将表单数据清空
					st={};
				}else{
					alert("新增失败");
				}
		    })
		    .error(function(){
		    	alert("新增失败");
		    });
	}
	

})

