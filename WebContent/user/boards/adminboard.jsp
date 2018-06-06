<%@page import="java.util.ArrayList"%>
<%@page import="vo.Post"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--페이지 요청됬을때 : UserBoardListController >> 받은데이터 출력 --%>
<%--검색기능 : ajax요청 >> UserBoardSerchController >> boardserchrslt.jsp에서 데이터받아옴  --%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
<%@include file="../template/header.jsp"%>
<c:set var="pb" value="${requestScope.pagebean}" />
<%-- 페이징 설정을 위한 변수들 --%>
<c:set var="totalCount" value="${pb.totalCount}" />
<c:set var="currentPage" value="${pb.currentPage}" />
<c:set var="cntPerPage" value="${pb.cntPerPage}" />

<script type="text/javascript">
	function goUrl(url) {
		location.href = url;
	}
</script>

<section id="content" class="gray-area">
	<div class="container">
		<div class="row">
			<div class="booking-information travelo-box">
				<h1>게시판</h1>
				<hr>
				<br>
				<h3>공지사항</h3>
				<%-- <h3><%=p.getBoard_id().getBrd_name() %></h3> --%>
				<p style="text-align: right">
					<%
						String root = request.getContextPath();
					%>
				
				<form name="f" method="GET" action="<%=root%>/PostController">
					<select name="type">
						<option value="searchAll">전체검색</option>
						<option value="searchTitle">제목</option>
						<option value="searchWriter">작성자</option>
						<option value="searchContent">내용</option>
					</select> <input type="text" name="searchText" value="" /> <input
						type="submit" value="검색" />
				</form>
				</p>
				<form name="f"
					action="<%=request.getContextPath()%>/PostController?type=boardView"
					method="get">
					<table class="table">
						<thead>
							<tr>
								<th>글 번호</th>
								<th>제목</th>
								<th>작성자</th>
								<th>등록 일시</th>
								<th>조회수</th>
							</tr>
						</thead>
						<tbody>
							<!-- 게시물이 0개일때,
						<tr>
							<td align="center" colspan="5">등록된 게시물이 없습니다.</td>
						</tr>
						 -->
							<%-- 	<c:forEach var="data" items =""> --%>
							<%
								ArrayList<Post> data = (ArrayList) request.getAttribute("data");
								for (Post p : data) {
							%>
							<tr>
								<td><%=p.getRownum()%></td>
								<!-- 테이블에 post 값 담기-->
								<td><a
									href="<%=request.getContextPath()%>/PostController?type=boardView&brd_id=<%=p.getBoard_id().getBrd_id()%>&post_id=<%=p.getPost_id()%>"><%=p.getPost_title()%></a></td>
								<td><%=p.getAdmin_id()%></td>
								<td><%=p.getPost_dateTime()%></td>
								<td><%=p.getPost_view_count()%></td>
							</tr>
						</tbody>
						<%
							}
						%>
						<tfoot>
						</tfoot>
					</table>
					<ul>
						<div>
							<c:set var="startPage" value="${pb.startPage}" />
							<c:set var="endPage" value="${pb.endPage}" />
							<a class="page-link" href="#"
								aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
							</a>
							<c:if test="${startPage > 1}">
								<a href="#">&laquo;</a>
							</c:if>
							<c:forEach begin="${startPage}" end="${endPage}" var="i">
								<a href="#">${i}</a>
							</c:forEach>
							<c:if test="${endPage < pb.totalPage}">
								<a href="#">&raquo;</a>
							</c:if>
							<a class="page-link" href="#"
							aria-label="Next"> <span aria-hidden="true">&raquo;</span>
							</a> 
						</div>
					</ul>
				</form>
			</div>
		</div>
	</div>
</section>
<%@include file="../template/footer.jsp"%>