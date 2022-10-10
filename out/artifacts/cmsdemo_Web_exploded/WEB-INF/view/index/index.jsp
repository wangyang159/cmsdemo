<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 声明视窗 -->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>今日头条</title>
<link href="/resource/css/bootstrap.min.css" rel="stylesheet">
<link href="/resource/css/index.css" rel="stylesheet">
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
<!-- 视窗需要的插件 -->
<script type="text/javascript" src="/resource/js/popper.min.js"></script>
<script type="text/javascript" src="/resource/js/bootstrap.min.js"></script>

<style type="text/css">
	.ex{
		/*如何处理元素内的空白部分*/
		white-space:initial;
		/*超出部分隐藏*/
		overflow : hidden;
		text-overflow: ellipsis;
		display: -webkit-box;
		-webkit-line-clamp: 2;
		-webkit-box-orient: vertical;
	}
</style>
</head>
<body>
	<!-- 最外面一个大盒子，是bootstrap的网格 -->
	<div class="container-fluid">
		<!-- 网页头部的黑框 -->
		<div class="row" style="height: 50px">
			<!-- 黑框左侧放个文字也可以是一个logo -->
			<div class="col-md-10 bg-dark pt-2">
				<font color="white">下载APP</font>
			</div>

			<!-- 黑框右侧的内容 -->
			<div class="col-md-2 bg-dark pt-2">
				<!-- 如果没有登录 -->
				<c:if test="${sessionScope.user==null }">
					<button class="btn btn-link btn-sm text-decoration-none "
						data-toggle="modal" data-target="#exampleModal" onclick="reg()">
						<font color="white">注册</font>
					</button>
					<button class="btn btn-link btn-sm text-decoration-none"
						data-toggle="modal" data-target="#exampleModal" onclick="login()">
						<font color="white">登录</font>
					</button>
				</c:if>
				<!-- 如果登录 -->
				<c:if test="${sessionScope.user!=null }">
					<div class="btn-group dropleft">
						<button type="button" class="btn btn-dark btn-sm dropdown-toggle"
							data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">${sessionScope.user.username }</button>
						<div class="dropdown-menu">
							<!-- Dropdown menu links -->
							<ul>
								<li><a href="/my/index.do">个人中心</a></li>
								<li><a href="/passport/logout.do">注销</a></li>
							</ul>
						</div>
					</div>
				</c:if>
			</div>
		</div>
		<!-- 网页头部黑框结束 -->


		<!--黑框下面的主体内容 -->
		<div class="row mt-2">
			<!-- 左侧栏目菜单 -->
			<div class="col-md-2 mt-5">
				<ul>
					<li><img alt="" src="/resource/imgs/logo.jpg" class="rounded" style="width: 110px;height: 40px"></li>
					<li class="mt-2"><a class="channel-item ${article.channelId==null?"active":"" }" href="/index.do">热点</a></li>
					<c:forEach items="${channels}" var="channel">
						<li><a class="channel-item ${article.channelId==channel.id?"active":"" }" href="/index.do?channelId=${channel.id}">${channel.name }</a></li>
					</c:forEach>
				</ul>
			</div>

			<!-- 中间内容 -->
			<div class="col-md-7" style="height: 700px">
				<!-- 轮播图的显示 显示的条件是当前焦点是热点栏目 -->
				<c:if test="${article.channelId==null }">
					<div id="carouselExampleCaptions" class="carousel slide" data-ride="carousel">
							<ol class="carousel-indicators">
								<c:forEach items="${slides}" varStatus="i">
									<li data-target="#carouselExampleCaptions" data-slide-to="${i.index }" class="active"></li>
								</c:forEach>
							</ol>
							<div class="carousel-inner">
							   <c:forEach items="${slides}"  var="slide" varStatus="i">
									<div class="carousel-item ${i.index==0?"active":"" }">
										<img style="height: 350px" src="/pic/${slide.picture }" class="d-block w-100 rounded"  alt="...">
										<div class="carousel-caption d-none d-md-block">
											<h5>${slide.title }</h5>
										</div>
									</div>
								</c:forEach>
							</div>
							<a class="carousel-control-prev" href="#carouselExampleCaptions"
								role="button" data-slide="prev"> <span
								class="carousel-control-prev-icon" aria-hidden="true"></span> <span
								class="sr-only">Previous</span>
							</a>
							<a class="carousel-control-next" href="#carouselExampleCaptions"
								role="button" data-slide="next">
								<span class="carousel-control-next-icon" aria-hidden="true"></span> <span
								class="sr-only">Next</span>
							</a>
					</div>
				</c:if>

				<!-- 栏目分类的显示 如果不是热点则展示子栏目-->
				<c:if test="${article.channelId!=null }">
					<div>
						<ul class="subchannel">
						    <li class="sub-item ${article.categoryId==null?"sub-selected":"" }"><a href="/index.do?channelId=${article.channelId }">全部</a></li>
							<c:forEach items="${categorys}" var="category">
								<li class="sub-item ${article.categoryId==category.id?"sub-selected":"" }"><a href="/index.do?channelId=${article.channelId}&categoryId=${category.id }">${category.name }</a></li>
							</c:forEach>
						</ul>
					</div>
				</c:if>

				<!-- 栏目先的文章  :内容代码共用如果后期有差别，那么提前将代码分开放在if标签中 -->
				<div class="mt-5">
					<!-- 无序列表 -->
					<ul class="list-unstyled">
						<c:forEach items="${info.list}" var="article">
							<li class="media">
								<a href="/detail.do?id=${article.id}" target="_blank"><img style="width: 100px;height: 80px" src="/pic/${article.picture }" class="mr-3 rounded" alt="..."></a>
								<div class="media-body">
									<h5 class="mt-0 mb-1"><a href="/detail.do?id=${article.id}" target="_blank">${article.title }</a></h5>
									<p class="pt-1">
										<fmt:formatDate value="${article.created }" pattern="yyyy-MM-dd HH:mm:ss"/>
									</p>
								</div>
							</li>
							<hr>
						</c:forEach>
					</ul>
				</div>
				<!-- 分页 -->
				<div>
					<jsp:include page="/WEB-INF/view/common/bookstappages.jsp"></jsp:include>
				</div>
			</div>

			<!-- 右侧边栏:显示最新的五条新闻 -->
			<div class="col-md-3">
				<!-- 卡片样式 -->
				<div class="card" style="width: 18rem;">
					<div class="card-header">最新文章</div>
					<div class="card-body">
						<!-- 列表样式 -->
						<ul class="list-unstyled">
							<c:forEach items="${lastArticles.list}" var="lastArticle">
								<li class="media">
									<a href="/detail.do?id=${lastArticle.id}" target="_blank"><img src="/pic/${lastArticle.picture }" class="mr-3 rounded" alt="..." style="width: 60px;height: 60px"></a>
									<div class="media-body ex">
										<a href="/detail.do?id=${lastArticle.id}" target="_blank"> ${lastArticle.title }</a>
									</div>
								</li>
  								<hr>
							</c:forEach>
						</ul>
					</div>
				</div>
			</div>

		</div>

	</div>
	
	<!-- 登录注册的模态框 前面的视窗支持就是为了这个 -->
	<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel"></h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body" id="modalbody">
	        
	      </div>
	    </div>
	  </div>
	</div>

	<script type="text/javascript">
		function goPage(pageNum) {
			var channelId ="${article.channelId}";
			var categoryId="${article.categoryId}";
			location.href="/index.do?pageNum="+pageNum+"&channelId="+channelId+"&categoryId="+categoryId;
		}
		//注册
		function reg() {
			$("#exampleModalLabel").text("用户注册");
			$("#modalbody").load("/passport/reg.do");
		}
		function login() {
			$("#exampleModalLabel").text("用户登录");
			$("#modalbody").load("/passport/login.do");
		}

	</script>
</body>
</html>