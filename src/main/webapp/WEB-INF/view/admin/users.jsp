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
<title></title>

</head>
<body>


	<div class="form-group form-inline">
		<div class="form-group">
			<label for="title">用户名:</label> 
			<input id="title"
				class="form-control" type="text" name="username"
				value="${user.username }">
		</div>
		<div class="form-group form-inline ml-2">
			用户状态：
			<div class="form-check">
				<input id="check1" class="form-check-input" type="radio"
					name="locked" value="0" ${user.locked==0?"checked":""}>
				<label class="form-check-label" for="check1">正常</label>
			</div>
			<div class="form-check">
				<input id="check2" class="form-check-input" type="radio"
					name="locked" value="1" ${user.locked==1?"checked":"" }>
				<label class="form-check-label" for="check2">禁用</label>
			</div>

			
		</div>

		<div class="ml-2">
			<button class="btn btn-info" onclick="query()">查询</button>

		</div>
	</div>

	<table class="table table-bordered table-hover text-center">
		<tr>
			<td>序号</td>
			<td>用户名</td>
			<td>昵称</td>
			<td>注册日期</td>
			<td>生日</td>
			<td>状态</td>
		
		</tr>

		<c:forEach items="${info.list}" var="user" varStatus="index">

			<tr>
				<td>${index.count }</td>
				<td>${user.username }</td>
				<td>${user.nickname }</td>
				<td><fmt:formatDate value="${user.created }"
						pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<td><fmt:formatDate value="${user.birthday }"
						pattern="yyyy-MM-dd" /></td>
				<td>
				   <c:if test="${user.locked==0}">
						<button class="btn btn-info btn-sm" type="button" onclick="update(${user.id},this)">正常</button>
					</c:if> 
					
					<c:if test="${user.locked==1}">
						<button class="btn btn-danger btn-sm" type="button" onclick="update(${user.id},this)">禁用</button>
					</c:if></td>
				
				
			</tr>
		</c:forEach>

		<tr>
			<td colspan="10"><jsp:include
					page="/WEB-INF/view/common/bookstappages.jsp"></jsp:include></td>
		</tr>
	</table>

</body>
<script type="text/javascript">
	  //修改用户
	  //locked:1 禁用，locked:0  正常
	  function update(id,obj){
		 //如果按钮的值为否则应该传递1，否则就是 0
		 var locked = $.trim($(obj).text())=='正常'?1:0;
		 $.post("/admin/user/update.do",{id:id,locked:locked},function(flag){
			 if(flag){
				 //改变按钮的内容
				locked==1?$(obj).text("禁用"):$(obj).text("正常");
				//改变颜色
				$(obj).attr("class",$(obj).text()=="禁用"?"btn btn-danger btn-sm":"btn btn-info btn-sm");
			 }
		 })
	 }

	  //查询
	  function query(){
			 var username =$("[name='username']").val();//姓名
			 var locked= $("[name='locked']:checked").val();//用户状态
		//在中间区域加载分页
		$("#center").load("/admin/users.do?username="+username+"&locked="+locked);
		  
	  }


	function goPage(page) {
		 var username =$("[name='username']").val();//姓名
		 var locked= $("[name='locked']:checked").val();//用户状态
		//在中间区域加载分页
		$("#center").load("/admin/users.do?page=" + page+"&username="+username+"&locked="+locked);
	}
</script>

</html>