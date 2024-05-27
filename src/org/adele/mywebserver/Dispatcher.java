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
		System.out.println("Dispatcher method: " + method + " requestUrl: "+ requestUrl);
		if(method.equals("GET")) {
			if(requestUrl.equals("/")) {
				// root
				return new StaticFileReadHttpHandler(200,"static/index.html");
			} else if(requestUrl.startsWith("/static")) {
				return new StaticFileReadHttpHandler(200, requestUrl.substring(1));
			}
		} else {
			throw new Exception("지원하지 않는 메소드");
		}
		return null;
	}
}
