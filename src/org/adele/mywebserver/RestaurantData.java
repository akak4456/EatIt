package org.adele.mywebserver;

import java.util.ArrayList;

public class RestaurantData {
	private String name;
	private ArrayList<String> imgUrls = new ArrayList<>();
	private int minPrice;
	private int maxPrice;
	private int distance;
	private double star;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<String> getImgUrls() {
		return imgUrls;
	}
	public void addImgUrl(String imgUrl) {
		this.imgUrls.add(imgUrl);
	}
	public int getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(int minPrice) {
		this.minPrice = minPrice;
	}
	public int getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(int maxPrice) {
		this.maxPrice = maxPrice;
	}
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	public double getStar() {
		return star;
	}
	public void setStar(double star) {
		this.star = star;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("name:");
		builder.append(name);
		builder.append("\nimgUrls:\n");
		for(int i=0;i<imgUrls.size();i++) {
			builder.append(imgUrls.get(i));
			builder.append("\n");
		}
		builder.append("minPrice:");
		builder.append(minPrice);
		builder.append("\nmaxPrice:");
		builder.append(maxPrice);
		builder.append("\ndistance:");
		builder.append(distance);
		builder.append("\nstar:");
		builder.append(star);
		return builder.toString();
	}
	
	
}
