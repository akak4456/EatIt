package org.adele.mywebserver;

/**
 * 
 * @author Adele
 * request 를 분석해서 이를 처리할 만한 HttpHandler 를 반환하는 것
 */
public class Dispatcher {
	public HttpHandler dispatch(HttpRequestDto requestDto) throws Exception {
		String method = requestDto.getMethod();
		String requestUrl = requestDto.getRequestUrl();
		if(method.equals("GET")) {
			if(requestUrl.equals("/")) {
				// root
				return new FileReadHttpHandler(200,"index.html");
			}
		} else {
			throw new Exception("지원하지 않는 메소드");
		}
		return null;
	}
}
