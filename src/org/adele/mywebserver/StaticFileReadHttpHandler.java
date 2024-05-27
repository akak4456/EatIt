package org.adele.mywebserver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * 
 * @author Adele
 * HttpHandler 중에 HttpResponseDto 를 WebServer 에 있는 파일들을 이용해 구성하는 핸들러
 */
public class StaticFileReadHttpHandler implements HttpHandler {
	private int responseCode;
	private String filePath;
	
	public StaticFileReadHttpHandler(int responseCode, String filePath) {
		this.responseCode = responseCode;
		this.filePath = filePath;
	}
	@Override
	public HttpResponseDto handle() throws IOException {
		StringBuilder responseData = new StringBuilder();
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		responseData.append("HTTP/1.1 200 OK\r\n");
		responseData.append("Server: \r\n");
		responseData.append("\r\n\r\n");
		String str;
		while(true) {
			str = br.readLine();
			if(str == null) break;
			responseData.append(str);
		}
		HttpResponseDto dto = new HttpResponseDto(responseData.toString());
		br.close();
		return dto;
	}

}
