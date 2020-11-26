<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page trimDirectiveWhitespaces = "true" %>    

<form name = "postForm">
	<input type = "hidden" name = "id" value = "${member.id }">
</form>



<script>

	window.onload = function() {
		
		var imageURL = "getPicture.do?picture=${member.picture}";
		
/* 		<img src = "url(getPicture.do?picture=${member.picture})"> */
		
		$("div#pictureView").css({
			"background-image":"url("+imageURL+")",
			"background-position" : "center",
			"background-size" : "cover",
			"background-repeat" : "no-repeat"
		});
		
		$("input").css("border", "none").prop("readonly", true);
		
		
		var form = $("form[name = 'postForm']");
		
		
		$("button#modifyBtn").on("click", function(event) {
			
			form.attr("action", "modifyForm.do");
			form.submit();
			
		});
		
		$("button#deleteBtn").on("click", function(event) {
			
			form.attr("action", "remove.do");
			form.submit();
			
		});
		
		$("button#stopBtn").on("click", function(event) {
			
			form.attr("action", "stop.do");
			form.submit();
			
		});
		
	}

</script>