var app = angular.module("myApp",['smartTable.table']);
app.controller("customerController", function($scope,$http){
	// 分页配置
	$scope.itemsByPage = 10; // 每页条数
	$scope.maxSize = 8; // 显示的页数
	$scope.currentPage = 1; // 当前页
	$scope.dataNumber = 1; // 数据总条数
	$scope.numberOfPages = Math.ceil($scope.dataNumber/$scope.itemsByPage); // 总页数
	// 监听是否翻页
	$scope.$watch('currentPage + itemsByPage', function (){
		$scope.searchcustomerinfo();
	});
	///新增方法开始*************
	$scope.addForm = {};
	$scope.addCustomer=function(){
		console.log($scope.addForm);
		$http({
			contentType:'application/text;charset=UTF-8',
			data:JSON.stringify($scope.addForm),
			method:'post',
			url:interfaces.customers
		    })
			.success(function(data){
				json=JSON.parse(data.data);
				console.log(json);
				if(json.result){
					showTip("success","新增成功");
					$('#add_customer').modal('hide');
					//新增成功后查询
					$scope.searchForm.name = angular.copy($scope.addForm.name);
					$scope.searchcustomer();
					//将表单数据清空
					$scope.addForm={};
				}else{
					showTip("warning","新增失败");
				}
		    })
		    .error(function(){
		    	showTip("danger","失败");
		    });
	}
	//查询开始
/*	$scope.master={
			infectiousFlag:"",
			symptomFlag:""
	}
	
	$scope.reset=function(){
		$scope.searchForm = angular.copy($scope.master);
	}
	$scope.reset();*/
	$scope.searchForm = {};
	//搜索按钮查询 从1页开始
	$scope.searchcustomer = function(){
		$scope.currentPage = 1;
		$scope.searchcustomerinfo();
	}
	// 查询客户信息
	$scope.searchcustomerinfo = function(){
		$scope.searchForm.pageIndex = $scope.currentPage;
		$scope.searchForm.pageSize = $scope.itemsByPage;
		/*if($scope.searchForm.symptomFlag == ""){
			$scope.searchForm.symptomFlag = null;
		}
		if($scope.searchForm.infectiousFlag == ""){
			$scope.searchForm.infectiousFlag = null;
		}*/
		console.log($scope.searchForm);
		$http({
			method:'GET',
			/*url:'http://10.136.19.217:8015/customerapp/customers', */
			url:interfaces.customers,
			params:$scope.searchForm
			})
			.success(function(data){
				json=JSON.parse(data.data);
				jsonrows = JSON.parse(json.rows);
		/*		for(var i = 0 ; i < jsonrows.length ; i++){
					if(jsonrows[i].symptomFlag == true){
						jsonrows[i].symptomFlag = "是"
					}else if(jsonrows[i].symptomFlag == false){
						jsonrows[i].symptomFlag = "否"
					}else{
						jsonrows[i].symptomFlag = "未知"
					}
					if(jsonrows[i].infectiousFlag == true){
						jsonrows[i].infectiousFlag = "是"
					}else if(jsonrows[i].infectiousFlag == false){
						jsonrows[i].infectiousFlag = "否"
					}else{
						jsonrows[i].infectiousFlag = "未知"
					}
				}*/
				console.log($scope.numberOfPages);
				$scope.attributes=jsonrows;
				$scope.dataNumber = json.count;
				console.log($scope.dataNumber);
				
				$scope.numberOfPages = Math.ceil($scope.dataNumber/$scope.itemsByPage);
				console.log($scope.numberOfPages);
			})
		    .error(function(){
		    	showTip("danger","查询失败");
		    });
    }
	//编辑开始
	$scope.alterForm = {};
	$scope.setValue1 = function(obj){

	/*	obj.birthday = new Date(obj.birthday); */
		$scope.alterForm = angular.copy(obj);
		$scope.alterForm.birthday=new Date($scope.alterForm.birthday);
		console.log($scope.alterForm);
		//编辑给 传染病标志 赋值
	/*	if($scope.alterForm.infectiousFlag == null){
			$scope.alterForm.infectiousFlag = "";
		}else{
			if($scope.alterForm.infectiousFlag == "是"){
				$scope.alterForm.infectiousFlag = "true";
			}else{
				$scope.alterForm.infectiousFlag = "false";
			}
		}
		//编辑给 病状标志 赋值
		if($scope.alterForm.symptomFlag == null){
			$scope.alterForm.symptomFlag = "";
		}else{
			if($scope.alterForm.symptomFlag == "是"){
				$scope.alterForm.symptomFlag = "true";
			}else{
				$scope.alterForm.symptomFlag = "false";
			}
		}*/
	}
	
	$scope.Edit = function () {
		//编辑给 传染病标志 赋值
		/*if($scope.alterForm.infectiousFlag == ""){
			$scope.alterForm.infectiousFlag = null;
		}else{
			if($scope.alterForm.infectiousFlag == "true"){
				$scope.alterForm.infectiousFlag = true;
			}else if($scope.alterForm.infectiousFlag == "false"){
				$scope.alterForm.infectiousFlag = false;
			}
		}
		//编辑给 病状标志 赋值
		if($scope.alterForm.symptomFlag == ""){
			$scope.alterForm.symptomFlag = null;
		}else{
			if($scope.alterForm.symptomFlag == "true"){
				$scope.alterForm.symptomFlag = true;
			}else if($scope.alterForm.symptomFlag == "false"){
				$scope.alterForm.symptomFlag = false;
			}
		}*/
		console.log($scope.alterForm);
		$http({
			contentType:'application/text;charset=UTF-8',
			data:JSON.stringify($scope.alterForm),
			method:'PUT',
			url:interfaces.customers+'/'+$scope.alterForm.id
		    })
			.success(function(data){
				json=JSON.parse(data.data);
				if(json.result){
					showTip(json.result);
					/*showTip("success","修改成功");*/
					$('#edit_customer').modal('hide');
					//修改成功后执行查询
				/*	$scope.searchForm.name = angular.copy($scope.searchForm.name);*/
					$scope.searchcustomer();
				}else{
					showTip("warning","修改失败");
				}
		    })
		    .error(function(){
		    	showTip("danger","失败");
		    });
	}
	//删除方法开始
	$scope.setValue2 = function(obj){
		$scope.id = obj.id;
	}
    $scope.Remove = function () {
    	console.log($scope.id);
    	console.log($scope.searchForm.name);
    	$http({
			contentType:'application/text;charset=UTF-8',
			method:'DELETE',
			url:interfaces.customers+'/'+$scope.id
		    })
			.success(function(data){
				console.log(data);
				if(data.data=="1"){
					showTip("success","删除成功");
					$('#delete').modal('hide');
					$scope.searchcustomer();
				}else{
					showTip("warning","删除失败");
				}
		    })
		    .error(function(){
		    	showTip("danger","失败");
		    });
    };
    
  /*    新增模态框 汉字转拼音码和五笔码   --开始--	
    $("#add_name").blur(function (){
    	$http({
			contentType:'application/text;charset=UTF-8',
			method:"get",
			url:'http://10.136.19.217:8015/commapp/love/life/pyjm/'+$scope.addForm.name
		})
		.success(function(data){
			json = JSON.parse(data.data);
			$scope.addForm.spellNo = json.pyjc;
			$scope.addForm.wubiNo = json.wubi;
		})
    })
	 新增模态框  汉字转拼音码和五笔码  --结束-- 
	 修改模态框 汉字转拼音码和五笔码   --开始--	
    $("#alter_name").blur(function (){
    	$http({
			contentType:'application/text;charset=UTF-8',
			method:"get",
			url:'http://10.136.19.217:8015/commapp/love/life/pyjm/'+$scope.alterForm.name
		})
		.success(function(data){
			json = JSON.parse(data.data);
			$scope.alterForm.spellNo = json.pyjc;
			$scope.alterForm.wubiNo = json.wubi;
		})
    })
	 修改模态框  汉字转拼音码和五笔码  --结束-- 
*/
})

