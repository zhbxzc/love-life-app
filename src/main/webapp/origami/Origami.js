var app = angular.module("myApp",['smartTable.table']);
app.controller("origamiController", function($scope,$http){
	// 分页配置
	$scope.itemsByPage = 10; // 每页条数
	$scope.maxSize = 8; // 显示的页数
	$scope.currentPage = 1; // 当前页
	$scope.dataNumber = 1; // 数据总条数
	$scope.numberOfPages = Math.ceil($scope.dataNumber/$scope.itemsByPage); // 总页数
	// 监听是否翻页
	$scope.$watch('currentPage + itemsByPage', function (){
		$scope.searchorigamiinfo();
	});
	///新增方法开始*************
	$scope.addOrigami=function(){
		window.location.replace("http://"+window.location.host+"/origami/addOrigami.html");
	}
	//查询开始

	$scope.searchForm = {};
	//搜索按钮查询 从1页开始
	$scope.searchorigami = function(){
		$scope.currentPage = 1;
		$scope.searchorigamiinfo();
	}
	// 查询折纸信息
	$scope.searchorigamiinfo = function(){
		$scope.searchForm.pageIndex = $scope.currentPage;
		$scope.searchForm.pageSize = $scope.itemsByPage;
		$http({
			method:'GET',
			url:interfaces.oriinns,
			params:$scope.searchForm
			})
			.success(function(data){
				json=JSON.parse(data.data);
				jsonrows = JSON.parse(json.rows);
				$scope.attributes=jsonrows;
				$scope.dataNumber = json.count;
				
				$scope.numberOfPages = Math.ceil($scope.dataNumber/$scope.itemsByPage);
			
			})
		    .error(function(){
		    	showTip("danger","查询失败");
		    });
    }
	//查看开始
	$scope.get = function (obj) {
	
		window.location.replace("http://"+window.location.host+"/origami/getOrigami.html?id="+obj.id);
	}
	//删除方法开始
	$scope.setValue2 = function(obj){
		$scope.id = obj.id;
	}
    $scope.Remove = function () {
    	$http({
			contentType:'application/text;charset=UTF-8',
			method:'DELETE',
			url:interfaces.oriinns+'/'+$scope.id
		    })
			.success(function(data){
				if(data.data=="1"){
					showTip("success","删除成功");
					$('#delete').modal('hide');
					$scope.searchorigamiinfo();
				}else{
					showTip("warning","删除失败");
				}
		    })
		    .error(function(){
		    	showTip("danger","失败");
		    });
    };
  

 
})

