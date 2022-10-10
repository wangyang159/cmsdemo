<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <!-- bookstap视窗 -->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
<link href="/resource/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/resource/js/bootstrap.min.js"></script>
</head>
<body>
	<form action="">
		文章标题：<input type="text" name="title" value="${article.title }">
		<input type="button" onclick="selects()" value="查询" name="title" class="btn btn-info btn-sm">
	</form>
	<!-- 显示文章内容 -->
	<div id="sections">
		
	</div>
	<script type="text/javascript">
		function selects() {
			$("#sections").load("/admin/sections.do?title="+$("[name='title']").val());
		}
	</script>
</body>
</html>