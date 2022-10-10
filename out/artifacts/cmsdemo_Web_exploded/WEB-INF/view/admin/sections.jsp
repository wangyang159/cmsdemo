<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table class="table table-bordered table-hover text-center">
		<tr>
			<td>序号</td>
			<td>文章标题</td>
			<td>所属栏目</td>
			<td>作者</td>
			<td>发布时间</td>
			<td>是否热门</td>
			<td>操作</td>
		</tr>

		<c:forEach items="${info.list}" var="article" varStatus="index">

			<tr>
				<td>${index.count }</td>
				<td><div class="ex">${article.title }</div></td>
				<td>军事</td>
				<td>${article.user.username }</td>
				<td><fmt:formatDate value="${article.created }"
						pattern="yyyy-MM-dd HH:mm:ss" />
				</td>
				<td>
					<c:if test="${article.hot==0}">
						否
					</c:if> 
					<c:if test="${article.hot==1}">
						是
					</c:if>
				</td>
				<td><button class="btn btn-link btn-sm"
						onclick="detail(${article.id})" data-toggle="modal"
						data-target="#exampleModalLong" >详情</button>
				</td>
			</tr>
		</c:forEach>

		<tr>
			<td colspan="10"><jsp:include
					page="/WEB-INF/view/common/bookstappages.jsp"></jsp:include></td>
		</tr>
	</table>
	
	<!-- Modal 文章详情 -->
		<div class="modal fade" id="exampleModalLong" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
		  <div class="modal-dialog modal-lg" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="exampleModalLongTitle"><span id="mtitle"></span> </h5>
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>
		      <div class="modal-body" id="content">
		        
		      </div>
		      <div class="modal-footer">
		        <span id="msg" class="text-danger"></span>
		       <button type="button" class="btn btn-success" onclick="check(1)">通过</button>
		        <button type="button" class="btn btn-warning" onclick="check(-1)">驳回</button>
		        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
		       
		      </div>
		    </div>
		  </div>
		</div>
	<script type="text/javascript">
	
	//分页
	function goPage(pageNum) {
		 var title =$("[name='title']").val();//标题
		//在中间区域加载分页
		$("#sections").load("/admin/sections.do?pageNum=" + pageNum+"&title="+title);
	}
	//文章详情
	function detail(id){
		
		 articleId=id;//为全局变量赋值  文章ID
		  //先清空
		 $("#mtitle").empty();
		 $("#content").empty();
		 //根据文章id查询文章内容
		 $.get("/admin/article.do",{id:id},function(article){
			
			 $("#mtitle").append(article.title);
			 $("#content").append(article.content);
		 })
	}
	</script>
</body>
</html>