package org.adele.mywebserver;

public class IndexHttpHandler extends StaticFileReadHttpHandler {

	public IndexHttpHandler(int responseCode, String filePath) {
		super(responseCode, filePath);
	}
	
	@Override
	public String parseSpecialResponseData(String originResponseData) {
		return originResponseData.replace("%index-card%", "index card 로 바꿀꼬얌");
	}

}
