var app = angular.module("myApp",[]);
app.controller("addOrigamiController", function($scope,$http){
	var ue = UE.getEditor('editor');
	///新增方法开始*************
   
	$scope.addOrigami=function(){
		   var title=$("#title").val();
		      if(title.trim()==""){
		      	alert("标题不能为空");
		      	return;
		      }
		 var typeId=$("#typeId").val();
		 var htmlcontent=ue.getContent();
		      var st={
		          		"title":title,
		          		"typeId":typeId,
		          		"content":htmlcontent
		      }
		console.log(st);
		$http({
			contentType:'application/text;charset=UTF-8',
			data:JSON.stringify(st),
			method:'POST',
			url:interfaces.oriinns
		    })
			.success(function(data){
				json=JSON.parse(data.data);
				if(json.result){
					showTip("success","新增成功");
					window.location.replace("http://"+window.location.host+"/origami/Origami.html");
					//将表单数据清空
					st={};
				}else{
					showTip("danger","操作失败");
				}
		    })
		    .error(function(){
		    	showTip("danger","操作失败");
		    });
	}
	

})

