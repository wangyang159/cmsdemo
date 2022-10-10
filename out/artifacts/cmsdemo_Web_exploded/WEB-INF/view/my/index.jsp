<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <!-- bookstap视窗 -->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
<link href="/resource/css/bootstrap.min.css" rel="stylesheet">
<link href="/resource/css/index.css" rel="stylesheet">
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/resource/js/popper.min.js"></script>
<script type="text/javascript" src="/resource/js/bootstrap.min.js"></script>
</head>
<body>
	<!-- 首頁容器 -->
	<div class="container_fluid">
		<!-- 第一行展示标题 -->
		<div class="row">
			<div class="col-md-12 bg-warning" style="height: 50px;line-height: 50px">
				<img alt="" src="/resource/imgs/logo.jpg" class="rounded" style="width: 30px;height: 30px">
				<span>个人中心</span>
				<div class="btn-group dropleft" style="float: right;">
					<button type="button" class="btn btn-dark btn-sm dropdown-toggle"
						data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">${sessionScope.user.username }</button>
					<div class="dropdown-menu">
						<!-- Dropdown menu links -->
						<ul>
							<li><a href="/passport/logout.do">注销</a></li>
							<li><a href="/index.do">返回首页</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<!-- 第一行结束 -->
		
		<!-- 第二行展示内容 -->
		<div class="row mt-1">
			<!-- 导航 -->
			<div class="col-md-2 list-group bg-light" style="height: 500px">
				<div class=" text-center pt-4 pl-5 pr-3">
					<a href="#" data="/my/articles.do" class="list-group-item list-group-item-action active">我的文章</a>
					<a href="#" data="/my/push.do" class="list-group-item list-group-item-action">发布文章</a> 
					<a href="#" class="list-group-item list-group-item-action">我的收藏</a>
					<a href="#" class="list-group-item list-group-item-action">我的评论</a> 
					<a href="#" class="list-group-item list-group-item-action disabled" tabindex="-1" aria-disabled="true">个人设置</a>
				</div>
			</div>
			
			<!-- 显示内容 -->
			<div class="col-md-10" id="articles" style="height: 500px">
				<!-- 这里引入富文本是固定格式 -->
				<div style="display: none">
					<jsp:include page="/resource/kindeditor/jsp/demo.jsp"></jsp:include>
				</div>
			</div>
		</div>
		<!-- 第二行结束 -->
	</div>
	<!-- 首頁容器结束 -->
	
	<script type="text/javascript">
		//默认加载我的文章
		$("#articles").load("/my/articles.do");
		//跳转函数
		$("a").click(function() {
			//删除原本样式
			$("a").removeClass("active");
			$(this).addClass("active");
			var url=$(this).attr("data");
			$("#articles").load(url);
		});
	</script>
</body>
</html>