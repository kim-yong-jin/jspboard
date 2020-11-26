package com.jsp.action.member;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.jsp.action.Action;
import com.jsp.exception.NotMultipartFormDataException;
import com.jsp.util.GetUploadPath;
import com.jsp.util.MakeFileName;
import com.jsp.util.ServletFileUploadBuilder;

public class MemberPictureUploadAction implements Action {

	
	private static final int MEMORY_THRESHOLD = 1024 * 500;     // 500KB
	private static final int MAX_FILE_SIZE    = 1024 * 1024 * 1;// 1MB
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 2;// 2MB
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String url = "";
		
		ServletFileUpload upload = null;
		
		try {
			upload = ServletFileUploadBuilder.build(request, MEMORY_THRESHOLD, MAX_FILE_SIZE, MAX_REQUEST_SIZE);
		} catch (NotMultipartFormDataException e) {
			System.out.println(e.getMessage());
		}
		
		
		String uploadPath = GetUploadPath.getUploadPath("member.picture.upload");
		
		File file = new File(uploadPath);
		if(!file.mkdirs()) {
			System.out.println(uploadPath + "가 이미 존재하거나 실패했습니다.");
		}
		
		try {
			List<FileItem> formItems = upload.parseRequest(request);
			
			//파일 개수 확인
			if(formItems != null && formItems.size() > 0) {
				for(FileItem item : formItems) { //form items 반복하여 꺼내는 구문
					if(!item.isFormField()) { // 파일일 경우 해당
						// uuid + 구분자 + 파일명
						String fileName = MakeFileName.toUUIDFileNAme(".jpg", "");
						String filePath = uploadPath + File.separator + fileName;
						File storeFile = new File(filePath);
						
						// local HDD에 저장
						item.write(storeFile);
						
						PrintWriter out = response.getWriter();
						out.print(fileName);
					}else { // 일반 parameter text
						
						switch (item.getFieldName()) {
						case "oldPicture":
							String oldFileName = item.getString("utf-8").trim();
							File oldFile = new File(uploadPath + File.separator + oldFileName);
							if(oldFile.exists()) {
								oldFile.delete();
							}
						}
					}
				}
			}
			
		} catch (Exception e) {
			url = null;
		}
		
		
		
		

		return url;
	}

}
