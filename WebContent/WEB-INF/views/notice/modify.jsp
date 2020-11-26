<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>

<body>


<!-- Content Wrapper. Contains page content -->
  <div >
   <jsp:include page="/WEB-INF/views/content_header.jsp">
   	<jsp:param value="공지사항" name="subject"/>
   	<jsp:param value="수정" name="item"/>
   	<jsp:param value="modifyForm.do" name="url"/>
   </jsp:include>

    <!-- Main content -->
    <section class="content container-fluid">
		<div class="row">
			<div class="col-md-12">
				<div class="card card-outline card-info">
					<div class="card-header row">
						<h4 class="col-md-8">공지 수정</h4>
						<div class="col-md-4">
							<div class="float-right">
								<button type="button" class="btn btn-warning" id="modifyBtn" onclick = "submit();">수 정</button>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<button type="button" class="btn btn-default " id="cancelBtn" onclick = "cancel();">취 소</button>
							</div>
						</div>
					</div><!--end card-header  -->
					<div class="card-body">
						<form role="form" method="post" action="modify.do" name="modifyForm">
							<input class = "form-control" type = "hidden" id = "nno" name = "nno" value = "${notice.nno }">
							<div class="form-group">
								<label for="title">제 목</label> 
								<input class = "form-control" id = "title" name = "title" value = "${notice.title }">
							</div>
							<div class="form-group">
								<label for="writer">작성자</label> 
								<input class = "form-control" id = "writer"name = "writer"  value = "${notice.writer }" readonly>
							</div>
							<div class="form-group">
							<label for="content">내 용</label>
								<textarea class="textarea" name="content" id="content" rows="20"
									placeholder="1000자 내외로 작성하세요." style="display:none">
									${notice.content }
									</textarea>
							</div>
						</form>
					</div><!--end card-body  -->
					
				</div><!-- end card -->				
			</div><!-- end col-md-12 -->
		</div><!-- end row -->
    </section>
    <!-- /.content -->
  </div>
  
   <%@ include file = "/WEB-INF/views/common/summernote.jsp" %>
  
  
  <script>
  
  	window.onload = function () {
	    SmartEditor_summernote($("#content"));
	}
  
  
  	function submit() {
  		
  		var title = $("input[name='title']");
  		if(title.val() == ""){
  			alert("제목은 필수입니다.");
  			title.focus();
  			return;
  		}
  		
  		
  		var content = $("input[name='content']");
  		if(title.val() == ""){
  			alert("내용은 필수입니다.");
  			content.focus();
  			return;
  		}
  		
		$("form").submit();
	}
  	
  	function cancel() {
		history.go(-1);
	}
  
  </script>
  
  
  <!-- /.content-wrapper -->