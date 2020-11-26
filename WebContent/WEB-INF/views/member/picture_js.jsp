<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page trimDirectiveWhitespaces = "true" %>    


<form role = "imageForm" action = "upload/picture.do" method = "post" enctype = "multipart/form-data">
	<input id = "inputFile" name = "pictureFile" type = "file" class = "form-control" style = "display:none;" onchange = "imageChange_go();"/>
	<input id = "oldFile"  type = "hidden" name = "oldPicture" value = ""/>
	<input type = "hidden" name = "checkUpload" value = "0"/>
</form>

<script>
	
	function imageChange_go() {
		$("input[name = 'checkUpload']").val(0);
		preViewPicture($("input#inputFile")[0], $("div#pictureView"));
	}
	
	
	function upload_go() {
/* 		alert("upup"); */
		
		if($("input[name='pictureFile']").val() == ""){
			alert("사진을 선택하세요.");
			$("input[name='pictureFile']").click();
			return;
		};
	
		
		var form = new FormData($("form[role='imageForm']")[0]);
		
		$.ajax({
			url : "<%=request.getContextPath()%>/member/picture.do",
			data : form,
			type : "post",
			processData : false,
			contentType : false,
			success : function(data) {
				
				$("input[name='checkUpload']").val(1);
				
				$("input#oldFile").val(data);
				$("form[role='form'] input[name='picture']").val(data);
				
				alert("사진이 업로드 되었습니다.");
			},
			error : function (error) {
				alert("현재 사진 업로드가 불가합니다. \n 관리자에게 연락바랍니다.")
			}
			
		});
		
	}
	
</script>
