package com.jsp.action.member;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.util.GetUploadPath;

public class MemberGetPictureAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String url = "";
		
		String fileName = request.getParameter("picture");
		String savePath = GetUploadPath.getUploadPath("member.picture.upload");
		
		
		String filepath = savePath + File.separator + fileName;
		
		File downloadFile = new File(filepath);
		FileInputStream inStream = new FileInputStream(downloadFile);
		
		ServletContext context = request.getServletContext();
		
		String mimeType = context.getMimeType(filepath);
		
		if(mimeType == null) {
			mimeType = "application/octet-stream";
		}
		
		
		response.setContentType(mimeType);
		response.setContentLength((int)downloadFile.length());
		
		
		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
		response.setHeader(headerKey, headerValue);
		
		
		OutputStream outStream = response.getOutputStream();
		byte[] buffer = new byte[4096];
		int bytesRead = -1;
		
		while((bytesRead = inStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, bytesRead);
		}
		
		
		inStream.close();
		outStream.close();
		
		
		return url;
	}

}
