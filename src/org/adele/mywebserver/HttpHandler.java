package org.adele.mywebserver;

import java.io.IOException;

/**
 * 
 * @author Adele
 * Dispatcher 를 통해 request 를 해석해서 적절한 response dto 를 반환하는 HttpHandler 를 만들게 된다.
 */
public interface HttpHandler {
	HttpResponseDto handle() throws IOException;
}
