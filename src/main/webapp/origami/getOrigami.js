var app = angular.module("myApp",[]);
app.controller("getOrigamiController", function($scope,$http){
	$scope.searchForm = {};
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
				$scope.searchForm.title=json.title;
				$scope.searchForm.id=getQueryString("id");
				$scope.searchForm.typeId=json.typeId;
				if(json.typeId=="1"){
				$scope.searchForm.typeName="A类";
				}else if(json.typeId=="2"){
				$scope.searchForm.typeName="B类";
				}else
					{
					$scope.searchForm.typeName="C类";
					}
				/*$scope.editor.innerHTML=json.content;*/
				document.getElementById("editor").innerHTML = json.content; 

			})
		    .error(function(){
		    	alert("查询失败");
		    });
    }
	$scope.searchorigamiinfo();
	
    function getQueryString(name) {
	        var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)', 'i');
	        var r = window.location.search.substr(1).match(reg);
	        if (r != null) {
	            return unescape(r[2]);
	        }
	        return null;
	    }
    $scope.Remove = function () {
    	$http({
			contentType:'application/text;charset=UTF-8',
			method:'DELETE',
			url:interfaces.oriinns+'/'+getQueryString("id")
		    })
			.success(function(data){
				if(data.data=="1"){
					alert("删除成功");
					$('#delete').modal('hide');
					window.location.replace("http://"+window.location.host+"/origami/Origami.html");
				}else{
					alert("删除失败");
				}
		    })
		    .error(function(){
		    	alert("删除失败");
		    });
    };
    $scope.Edit = function () {
    	
		window.location.replace("http://"+window.location.host+"/origami/editOrigami.html?id="+getQueryString("id"));
	}
})

