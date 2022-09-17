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
				<!-- 收藏文章 -->
				<div align="right">
					<c:if test="${isCollect==0 || isCollect==null}">
						<a href="javascript:collect()">☆ 收藏</a>
					</c:if>
					<c:if test="${isCollect==1}">
						<span class="text-danger">★ 已收藏</span>
					</c:if>
				</div>
				<!-- 增加评论 -->
				<c:if test="${null!=sessionScope.user}">
					<div>
						输入评论：
						<textarea rows="8" cols="110" name="content"></textarea>
						<br>
						<button class="btn btn-info" onclick="addComment()">提交评论</button>
					</div>
				</c:if>
				<!-- 显示评论 -->
				<div>
					<c:forEach items="${info.list}" var="comment">
						${comment.user.username }  <fmt:formatDate
							value="${comment.created}" pattern="yyyy-MM-dd HH:mm:ss" />
						<br>
						<p class="mt-3">${comment.content }</p>
						<hr>
					</c:forEach>
				</div>

			</div>

			<!-- 右侧同样站位作用 -->
			<div class="col-md-3"></div>

		</div>

	</div>

	<script type="text/javascript">
		//收藏
		function collect() {
			var text = '${article.title}';//获取文章标题
			var url = window.location.href;//获取文章的url

			$.post("/my/collect.do", {
				text : text,
				url : url
			}, function(flag) {
				if (flag) {
					alert("收藏成功");
					window.location.reload();
				} else {
					alert("收藏失败，请登录后再试")
				}
			})
		}
		//增加评论
		function addComment() {
			var content = $("[name='content']").val();
			var articleId = '${article.id}';
			$.post("/my/addComment.do", {
				content : content,
				articleId : articleId
			}, function(flag) {
				if (flag) {
					alert("评论成功");
					window.location.reload();
				} else {
					alert("评论失败。请登录后重试")
				}
			})
		}
	</script>
</body>
</html>