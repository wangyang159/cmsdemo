<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>用户注册</title>
<link href="/resource/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/resource/js/jquery.validate.js"></script>
<script type="text/javascript" src="/resource/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
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
				<label for="repassword">确认密码</label> <input type="password"
					class="form-control" name="rePassword" id="repassword">
			</div>
			性别
			<div class="form-group form-check form-check-inline">
				<input type="radio" class="form-check-input" name="gender" value="1" id="boy" checked="checked">
				<label class="form-check-label" for="boy">男</label>
			</div>
			<div class="form-group form-check form-check-inline">
				 <input type="radio" class="form-check-input" name="gender" value="0" id="goril">
				 <label class="form-check-label" for="goril">女</label>
			</div>
			<div>
				<button type="submit" class="btn btn-primary">注册</button>
				<button type="reset" class="btn btn-dark">重置</button>
			</div>
			
		</form>
	</div>
	<script type="text/javascript">
		$("#form1").validate(
				{
					//1.定义校验规则
					rules : {
						username : {
							required : true,//用户名非空
							rangelength : [ 2, 6 ],//用户名长度在2-6之间
							remote:{
								type:"post",
								data:{username:function(){
									return $("[name='username']").val();
								}},
								url:"/passport/checkpass.do"
							}
						},
						password : {
							required : true,//密码非空
							rangelength : [ 6, 12 ]//密码长度在6-10之间
						},
						repassword : {
							equalTo : "#password"
						}
					},
					//2:如果不满足规则，进行提示
					messages : {
						username : {
							required : "用户名不能为空",
							rangelength : "用户名长度在2-6之间",
							remote:"用户已存在"
						},
						password : {
							required : "密码非空",
							rangelength : "密码长度在6-10之间"
						},
						rePassword : {
							equalTo : "两次密码输入不一致"
						}
					},
					submitHandler : function() {
						$.post("/passport/reg.do", $("#form1").serialize(),
								function(result) {
									if (result.code == 200) {//如果注册成功跳转到登录页面
										// $("#msg").text(result.msg);
										$("#modalbody").load("/passport/login.do");
									} else {//注册失败
										$("#msg").text(result.msg);
									}
								});
					}
				}
		);
	</script>
</body>
</html>