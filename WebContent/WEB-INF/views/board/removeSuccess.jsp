<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page trimDirectiveWhitespaces = "true" %>    

<script>
	alert("게시글 ${board.bno}번을 삭제했습니다.");
	window.opener.location.reload(true);
	window.close();
</script>