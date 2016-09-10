var app = angular.module("myApp",[]);
app.controller("editOrigamiController", function($scope,$http){
	var ue = UE.getEditor('editor');
	
	$scope.searchForm = {};
	$scope.editForm = {};
	// 查询折纸信息
	$scope.searchorigamiinfo = function(){
		$http({
			method:'GET',
			url:interfaces.oriinns+"/"+getQueryString("id"),
			params:$scope.searchForm
			})
			.success(function(data){
				json=JSON.parse(data.data);
				console.log(json);
				$scope.editForm.title=json.title;
				 $scope.selected=json.typeId.toString();//id的值，区分类型
				 ue.setContent(json.content); 

			})
		    .error(function(){
		    	alert("查询失败");
		    });
    }
	
	ue.ready(function() { 

		$scope.searchorigamiinfo();
		}); 
	
	$scope.types = [
	                {name:'A类',id:"1"},
	                {name:'B类',id:"2"},
	                {name:'C类',id:"3"}
	            ];
	                  
	///修改方法开始*************
   
	$scope.editOrigami=function(){
		   var title=$("#title").val();
		      if(title.trim()==""){
		      	alert("标题不能为空");
		      	return;
		      }
		 var typeId=$("#typeId").val();
		 var htmlcontent=ue.getContent();
		      var st={   
		    		    "id":getQueryString("id"),
		          		"title":title,
		          		"typeId":Number(typeId),
		          		"content":htmlcontent
		      }
		$http({
			contentType:'application/text;charset=UTF-8',
			data:JSON.stringify(st),
			method:'PUT',
			url:interfaces.oriinns+"/"+getQueryString("id")
		    })
			.success(function(data){
				json=JSON.parse(data.data);
				if(json.result){
					alert("修改成功");
					window.location.replace("http://"+window.location.host+"/origami/getOrigami.html?id="+getQueryString("id"));
					//将表单数据清空
					st={};
				}else{
					alert("修改失败");
				}
		    })
		    .error(function(){
		    	alert("修改失败");
		    });
	}
	
	 function getQueryString(name) {
	        var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)', 'i');
	        var r = window.location.search.substr(1).match(reg);
	        if (r != null) {
	            return unescape(r[2]);
	        }
	        return null;
	    }
})

