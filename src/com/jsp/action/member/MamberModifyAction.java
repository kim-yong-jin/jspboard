package com.jsp.action.member;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.jsp.action.Action;
import com.jsp.dto.MemberVO;
import com.jsp.exception.NotMultipartFormDataException;
import com.jsp.request.MemberModifyRequest;
import com.jsp.service.MemberService;
import com.jsp.util.ExceptionLoggerHelper;
import com.jsp.util.GetUploadPath;
import com.jsp.util.MakeFileName;
import com.jsp.util.ServletFileUploadBuilder;

public class MamberModifyAction implements Action {

	private static final int MEMORY_THRESHOLD = 1024 * 500; // 500KB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 1;// 1MB
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 2;// 2MB

	private MemberService memberService;

	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "redirect:/member/detail.do?id=";

		try {
			MemberVO member = modifyFile(request, response);
			memberService.modify(member);

			HttpSession session = request.getSession();
			MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
			
			url = url + member.getId();
			
			if (member.getId().equals(loginUser.getId())) {

				member = memberService.getMember(member.getId());
				session.setAttribute("loginUser", member);

				url = "";

				PrintWriter out = response.getWriter();
				out.print("<script>");
				out.print("location.href='detail.do?id=" + member.getId() + "';");
				out.print("window.opener.parent.location.reload();");
				out.print("</script>");
				out.close();

			}

		} catch (SQLException e) {
			ExceptionLoggerHelper.write(request, e, memberService);
			e.printStackTrace();
			url = null;

		} catch (Exception e) {
			e.printStackTrace();
			url = null;
		}

		return url;
	}

	private MemberVO modifyFile(HttpServletRequest request, HttpServletResponse response) throws Exception {

		MemberModifyRequest modifyReq = new MemberModifyRequest();
		
		ServletFileUpload upload = null;


		try {
			upload = ServletFileUploadBuilder.build(request, MEMORY_THRESHOLD, MAX_FILE_SIZE, MAX_REQUEST_SIZE);
		} catch (NotMultipartFormDataException e) {
			System.out.println(e.getMessage());
		}

		String uploadPath = GetUploadPath.getUploadPath("member.picture.upload");

		File file = new File(uploadPath);
		if (!file.mkdirs()) {
			System.out.println(uploadPath + "가 이미 존재하거나 실패했습니다.");
			
		}

		List<FileItem> formItems = upload.parseRequest(request);

		// 파일 개수 확인
		if (formItems != null && formItems.size() > 0) {
			for (FileItem item : formItems) { // form items 반복하여 꺼내는 구문
				if (!item.isFormField()) { // 파일일 경우 해당
					// uuid + 구분자 + 파일명
					String fileName = MakeFileName.toUUIDFileNAme(".jpg", "");
					String filePath = uploadPath + File.separator + fileName;
					File storeFile = new File(filePath);

					// local HDD에 저장
					item.write(storeFile);

					// MemberVO 에
					modifyReq.setPicture(storeFile);

				} else { // 일반 parameter text

					Class<?> cls = modifyReq.getClass();

					Method[] methods = cls.getMethods();

					for (Method method : methods) {

						if (method.getName().contains("set")) {

							String methodName = method.getName();
							methodName = (methodName.replace("set", "").charAt(0) + "").toLowerCase()
									+ methodName.substring(4);
							
							if(methodName.equals(item.getFieldName())) {
								method.invoke(modifyReq, item.getString("utf-8"));
							}
							

						}

					}

				}
			}
		}
		
		if(modifyReq.getUploadPicture()!= null && !modifyReq.getUploadPicture().isEmpty()) {
			File deleteFile = new File(uploadPath, modifyReq.getOldPicture());
			if(deleteFile.exists()) {
				deleteFile.delete();
			}
		}
		

		return modifyReq.toParseMember();
	}

}
