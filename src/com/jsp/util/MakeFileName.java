package com.jsp.util;

import java.util.UUID;

public class MakeFileName {

	public static String toUUIDFileNAme(String fileName, String delimiter) {
		
		String uuid = UUID.randomUUID().toString().replace("-", "");
		
		return uuid + delimiter + fileName;
		
	}
	
}
