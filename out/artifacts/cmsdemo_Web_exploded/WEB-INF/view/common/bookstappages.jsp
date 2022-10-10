<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div>
	<nav aria-label="Page navigation example">
		<ul class="pagination">
			<!-- 如果没有记录则显示无记录 -->
			<c:if test="${info.total==0}">
				<i>暂无数据</i>
			</c:if>

			<!-- 如果有记录则显示显示 《 符号-->
			<c:if test="${info.total!=0}">
				<li class="page-item"><a class="page-link"
					href="javascript:goPage(${info.prePage==0?1:info.prePage })"
					aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
				</a></li>
			</c:if>

			<!-- 如果有记录 则展示 该组件items没有值则不会运行-->
			<c:forEach items="${info.navigatepageNums}" var="n">
				<li class="page-item ${n==info.pageNum?"active":""}">
					<a class="page-link" href="javascript:goPage(${n})">${n}</a>
				</li>
			</c:forEach>

			<!-- 如果有记录则显示显示 》 符号-->
			<c:if test="${info.total!=0}">
				<li class="page-item"><a class="page-link"
					href="javascript:goPage(${info.nextPage ==0?info.pages:info.nextPage})"
					aria-label="Next"> <span aria-hidden="true">&raquo;</span>
				</a></li>
			</c:if>
		</ul>
	</nav>

</div>