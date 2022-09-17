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
<style type="text/css">
.ex {
    width: 200px;
	white-space: nowrap; /*不换行的*/
	overflow: hidden; /*超出范围隐藏*/
	text-overflow: ellipsis; /*超出用省略号 */
}
</style> 
</head>
<body>

	
	<div class="form-group form-inline">
		<div class="form-group">
			<label for="title">文章标题</label> <input id="title"
				class="form-control" type="text" name="title"
				value="${article.title }">
		</div>
		<div class="form-group form-inline">
			审核状态：
			<div class="form-check">
				<input id="check1" class="form-check-input" type="radio"
					name="status" value="0" ${article.status==0?"checked":""}>
				<label class="form-check-label" for="check1">待审</label>
			</div>
			<div class="form-check">
				<input id="check2" class="form-check-input" type="radio"
					name="status" value="1" ${article.status==1?"checked":"" }>
				<label class="form-check-label" for="check2">已审</label>
			</div>

			<div class="form-check">
				<input id="check3" class="form-check-input" type="radio"
					name="status" value="-1" ${article.status==-1?"checked":"" }>
				<label class="form-check-label" for="check3">驳回</label>
			</div>
		</div>

		<div>
			<button class="btn btn-info" onclick="query()">查询</button>

		</div>
	</div>

	<table class="table table-bordered table-hover text-center">
		<tr>
			<td>序号</td>
			<td>文章标题</td>
			<td>所属栏目</td>
			<td>所属分类</td>
			<td>作者</td>
			<td>发布时间</td>
			<td>是否热门</td>
			<td>审核状态</td>
			<td>操作</td>
		</tr>

		<c:forEach items="${info.list}" var="article" varStatus="index">

			<tr>
				<td>${index.count }</td>
				<td><div class="ex">${article.title }</div></td>
				<td>${article.channel.name }</td>
				<td>${article.category.name }</td>
				<td>${article.user.username }</td>
				<td><fmt:formatDate value="${article.created }"
						pattern="yyyy-MM-dd HH:mm:ss" /></td>
				<td>
					<c:if test="${article.hot==0}">
						<button class="btn btn-info btn-sm" type="button" onclick="update(${article.id},this)">否</button>
					</c:if> 
					
					<c:if test="${article.hot==1}">
						<button class="btn btn-danger btn-sm" type="button" onclick="update(${article.id},this)">是</button>
					</c:if>
				</td>
				<td>${article.status==0?"待审":article.status==1?"已审":"驳回" }</td>
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

</body>
<script type="text/javascript">
	function update(id,obj){
		 //如果按钮的值为否则应该传递1让它变成是，否则就是 0，并且记录当前的值
		 var hot = $(obj).text()=='否'?1:0;
		 
		 $.post("/admin/update.do",{id:id,hot:hot},function(flag){
			 if(flag){
				//改变按钮的内容
				hot==1?$(obj).text("是"):$(obj).text("否");
				//改变颜色
				$(obj).attr("class",$(obj).text()=="是"?"btn btn-danger btn-sm":"btn btn-info btn-sm");
			 }
		 })
	}
	
	var articleId;//文章ID
	//审核文章
	function check(status){
		$.post("/admin/update.do",{id:articleId,status:status},function(flag){
			  if(flag){
				  $("#msg").text("操作成功");
			  }else{
				  $("#msg").text("操作失败");
			  }
		})	
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
	
	//查询
	function query(){
		 var title =$("[name='title']").val();//标题
		 var status= $("[name='status']:checked").val();//审核状态
		//在中间区域加载分页
		$("#center").load("/admin/articles.do?title="+title+"&status="+status);
		  
	}

	//分页
	function goPage(pageNum) {
		 var title =$("[name='title']").val();//标题
		 var status=$("[name='status']:checked").val();//审核状态
		//在中间区域加载分页
		$("#center").load("/admin/articles.do?pageNum=" + pageNum+"&title="+title+"&status="+status);
	}
</script>

</html>