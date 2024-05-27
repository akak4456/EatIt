package org.adele.mywebserver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
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
	public static ArrayList<RestaurantData> restaurantData = new ArrayList<>();
	public static void main(String[] args) {
		try {
			/*
			 * socket을 시작하기 전에 static/data.txt 읽어서 parse 하기
			 * data.txt 형식
			 * new : 새로운 객체를 생성하라
			 * name 이름 : Restaurant.name = 이름 이다
			 * img imgUrl : Restaurant.imgUrls 에 imgUrl 을 추가해라
			 * minPrice 최소가격 : Restaurant.minPrice = 최소가격 이다
			 * maxPrice 최대가격 : Restaurant.maxPrice = 최대가격 이다
			 * distance 거리 : Restaurant.distance = 거리 이다
			 * star 별점 : Restaurant.star = 별점 이다
			 */
			BufferedReader br = new BufferedReader(new FileReader("static/data.txt"));
			String str;
			RestaurantData data = null;
			while(true) {
				str = br.readLine();
				if(str == null) {
					if(data != null) {
						restaurantData.add(data);
					}
					break;
				}
				if(str.equals("new")) {
					if(data != null) {
						restaurantData.add(data);
					}
					data = new RestaurantData();
				}else {
					String[] parsed = str.split(" ");
					switch(parsed[0]) {
						case "name":
							data.setName(parsed[1]);
							break;
						case "img":
							data.addImgUrl(parsed[1]);
							break;
						case "minPrice":
							data.setMinPrice(Integer.parseInt(parsed[1]));
							break;
						case "maxPrice":
							data.setMaxPrice(Integer.parseInt(parsed[1]));
							break;
						case "distance":
							data.setDistance(Integer.parseInt(parsed[1]));
							break;
						case "star":
							data.setStar(Double.parseDouble(parsed[1]));
							break;
					}
				}
			}
			br.close();
			System.out.println("Server has started on 127.0.0.1:8080\nWaiting for a connection...");
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
