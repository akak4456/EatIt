package org.adele.mywebserver;

/**
 * 
 * @author Adele
 * response data 를 담는 dto
 */
public class HttpResponseDto {
	 private String responseData;
	 public HttpResponseDto(String responseData){
		 this.responseData = responseData;
	 }

	 public String getResponseData() {
		 return responseData;
	 }
}
