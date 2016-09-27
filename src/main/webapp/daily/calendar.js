var app = angular.module("myApp",[]);
app.controller("calendarController", function($scope,$http){
	 function aaa() { 
	var i=new Date().getDay()+1;
	    $("#calendar tr").each(function() { 
	      $(this).find("td:nth-child("+i+")").css({color:"red", fontWeight:"bold"});     
	    }); 
	 }
	 aaa();

})

