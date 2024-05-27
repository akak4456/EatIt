package org.adele.mywebserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * eclipse 에서 자바독 생성시 한국어가 포함되면 오류가 난다.<br>
 * 그때 VM options 에 <strong>-locale ko_KR -encoding UTF-8 -charst UTF-8 -docencoding UTF-8</strong> 을 추가하자<br>
 * 웹 서버를 띄우는 클래스, ServerSocket 을 이용했다.
 * @author Adele
 * @see <a href="https://velog.io/@jmsuper_97/Java-%EC%9E%90%EB%B0%94%EB%A1%9C-%EA%B5%AC%ED%98%84%ED%95%98%EB%8A%94-HTTP-%EC%84%9C%EB%B2%84">웹서버 만들기 예제 코드</a>
 *
 */
public class WebSocket {
	public static void main(String[] args) {
		System.out.println("Server has started on 127.0.0.1:8080\nWaiting for a connection...");
		try {
			while(true) {
				ServerSocket socket = new ServerSocket(8080);
				Socket client = socket.accept();
				System.out.println("Client connected!");
				
				InputStream in = client.getInputStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(in));
				StringBuilder requestData = new StringBuilder();
				String line;
				while((line = reader.readLine()) != null) {
					if(line.isBlank()) {
						break;
					}
					requestData.append(line);
					requestData.append("\n");
				}
				OutputStream out = null;
				try {
					HttpRequestDto requestDto = new HttpRequestDto(requestData.toString());
					Dispatcher dispatcher = new Dispatcher();
					HttpHandler handler = dispatcher.dispatch(requestDto);
					HttpResponseDto responseDto = null;
					if(handler != null) {
						responseDto = handler.handle();
					} else {
						System.out.println("적절한 handler 를 찾지 못했습니다.");
					}
					out = client.getOutputStream();
					if(responseDto != null) {
						out.write(responseDto.getResponseData().getBytes());
					} else {
						StringBuilder responseData = new StringBuilder();
						responseData.append("HTTP/1.1 200 OK\r\n");
						responseData.append("Server: \r\n");
						responseData.append("\r\n\r\n");
						out.write(responseData.toString().getBytes());
					}
				} catch(IOException e) {
					e.printStackTrace();
				} catch(Exception e) {
					e.printStackTrace();
				} finally {
					in.close();
					reader.close();
					if(out != null) {
						out.close();
					}
					socket.close();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
