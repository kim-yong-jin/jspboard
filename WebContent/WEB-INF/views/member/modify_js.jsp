<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page trimDirectiveWhitespaces = "true" %>    
<script>

	window.onload = function () {
			
	var imageURL = "getPicture.do?picture=${member.picture}";
		
		$("div#pictureView").css({
			"background-image":"url("+imageURL+")",
			"background-position" : "center",
			"background-size" : "cover",
			"background-repeat" : "no-repeat"
		});
		
		$("#modifyBtn").on("click", function (e) {
			
			var form = $("form[role='form']");
			form.submit();
			
		});

		$("#cancelBtn").on("click", function (e) {
			history.go(-1);
		});
		
	};
	
	
	function imageChange_go() {
		var inputImage = $("input#inputFile")[0];
		
		preViewPicture(inputImage, $("div#pictureView"));
		$("input[name = 'uploadPicture']").val(inputImage.files[0].name);
	}


</script>