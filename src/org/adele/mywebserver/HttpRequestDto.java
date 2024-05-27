package org.adele.mywebserver;
/**
 * 
 * @author Adele
 * WebSocket 에서 받은 request data string 을 활용하기
 * 편하게 하기 위해 적절히 변환한것
 */
public class HttpRequestDto {
	private String method;
	private String requestUrl;
	public HttpRequestDto(String httpRequest) {
		String[] requestLines = httpRequest.split("\n");
		for(int i=0;i<requestLines.length;i++) {
			if(i == 0) {
				// GET /abc HTTP/1.1 와 같은 형식일때
				String[] parsedLine = requestLines[i].split(" ");
				method = parsedLine[0];
				requestUrl = parsedLine[1];
			}
		}
	}
	
	public String getMethod() {
		return method;
	}
	
	public String getRequestUrl() {
		return requestUrl;
	}
}
