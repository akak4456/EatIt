package org.adele.mywebserver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * 
 * @author Adele
 * HttpHandler 중에 HttpResponseDto 를 WebServer 에 있는 파일들을 이용해 구성하는 핸들러
 */
public class FileReadHttpHandler implements HttpHandler {
	private int responseCode;
	private String filePath;
	
	public FileReadHttpHandler(int responseCode, String filePath) {
		this.responseCode = responseCode;
		this.filePath = filePath;
	}
	@Override
	public HttpResponseDto handle() throws IOException {
		StringBuilder responseData = new StringBuilder();
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		
		String str;
		while(true) {
			str = br.readLine();
			responseData.append(str);
			if(str == null) break;
		}
		HttpResponseDto dto = new HttpResponseDto(responseData.toString());
		return null;
	}

}
