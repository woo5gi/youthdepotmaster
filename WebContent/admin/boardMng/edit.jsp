<%@page import="vo.Post"%>
<%@page import="vo.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../template/top.jsp"%>
<%@include file="../template/aside.jsp"%>

<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.9/summernote.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.9/summernote.js"></script>
<script>
$(document).ready(function() {
    $('#summernote').summernote({
        height: 300,
        minHeight: null,
        maxHeight: null,
        focus: true,
        callbacks: {
          onImageUpload: function(files, editor, welEditable) {
            for (var i = files.length - 1; i >= 0; i--) {
              sendFile(files[i], this);
            }
          }
        }
      });
    $
});
</script>
<div id="page-wrapper">
  <div class="row">
	<div class="col-lg-12">
	  <h2 class="page-header">게시 관리 > 공지사항 글쓰기</h2>
	</div>
  </div>
  <div class="row">
	<div class="col-lg-12">
		<div class="container" style=" margin:0px; padding:0px;padding-top: 20px;">
		  <form class="boardSubmit" action="<%=request.getContextPath()%>/PostController?type=adminboardwrite" method="post" enctype="multipart/form-data">
		    <input type="hidden" name="brd_id" value="<%= request.getParameter("brd_id") %>">
		    <table class="table table-bordered" style="width: 100%;">
		      <tr>
		        <th>글 제목</th>
		        <td><input type="text" name="title" class="form-control" /></td>
		      </tr>
		      <tr>
		        <th>글 내용</th>
		        <td><textarea class="form-control" id="summernote"
		                      name="content" placeholder="content" maxlength="140" rows="7"></textarea>
		        </td>
		      </tr>
		      <tr>
		        <th>파일</th>
		        <td>
		          <div class="fileForm">
		            <input type="file" name="uploadFiles"/>
		          </div>
		        </td>
		      </tr>
		      <tr>
		        <th>공지</th>
		        <td><label><input type="checkbox"/> 이 글을 공지글로 설정합니다.</label></td>
		      </tr>
		    </table>
		    <button class="btn btn-primary">확인</button>
		  </form>
		</div>
	</div>
  </div>
</div>
