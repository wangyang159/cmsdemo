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
<!-- 关键字与 文章来源-->
<meta name="keywords" content="${article.keywords }">
<meta name="origin" content="${article.original }">
<title>${article.title }</title>
<link href="/resource/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/resource/js/bootstrap.min.js"></script>
</head>
<body>
	<!-- 网格容器 -->
	<div class="container-fluid">
		<!-- 头部黑框 -->
		<div class="row" style="height: 44px">
			<div class="col-md-12 bg-dark pt-2">
				<font color="white">下载APP</font>
			</div>
		</div>

		<!-- 展示文章内容 -->
		<div class="row">
			<!-- 左侧站位 没有实际内容 -->
			<div class="col-md-2"></div>

			<!-- 中间展示文章详情 -->
			<div class="col-md-7">
				<H3>${article.title }</H3>
				<p>${article.user.username}  <fmt:formatDate value="${article.created}" pattern="yyyy-MM-dd HH:mm:ss " /></p>
                 ${article.content}
			</div>

			<!-- 右侧同样站位作用 -->
			<div class="col-md-3"></div>

		</div>

	</div>
</body>
</html>