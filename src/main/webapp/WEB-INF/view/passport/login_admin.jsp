<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 视窗 -->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>管理员登录</title>
<link href="/resource/css/bootstrap.min.css" rel="stylesheet">
<link href="/resource/css/jquery/screen.css" rel="stylesheet">
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/resource/js/jquery.validate.js"></script>

<script type="text/javascript" src="/resource/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container  col-md-5">
	  <h1>管理员登录</h1>
	  <hr>
     <span id="msg" class="text-danger"></span>
		<form id="form1">
			<div class="form-group">
				<label for="username">用户名</label> <input type="text"
					class="form-control" name="username" id="username">
			</div>
			<div class="form-group">
				<label for="password">密码</label> <input type="password"
					class="form-control" name="password" id="password">
			</div>
			
			<div class="form-group">
				<button type="submit" class="btn btn-info" >登录</button>
				<button type="reset" class="btn btn-primary">重置</button>
			</div>

		</form>
	</div>

</body>

<script type="text/javascript">
 $(function(){
	$("#form1").validate({
		//1.定义校验规则
		rules:{
			username:{
				required:true//用户名非空
				
			},
			password:{
				required:true//密码非空
				
			}
		},
		//2:如果不满足规则，进行提示
		messages:{
			username:{
				required:"用户名不能为空"
			},
			password:{
				required:"密码非空"
			}
		},
		submitHandler:function(){
			$.post("/passport/admin/login.do",$("#form1").serialize(),function(result){
				if(result.code==200){//如果登录成功
					location.href="/admin/index.do";//进入管理员后台页面
				}else{//登录失败
					$("#msg").text(result.msg)
				}
			});
		}
		
	}) 
	 
 })


</script>
</html>