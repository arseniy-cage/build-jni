package com.saint.blackrussia.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class News {

	@SerializedName("image")
	@Expose
	private String image;

	@SerializedName("title")
	@Expose
	private String title;

	public News (String image, String title) {
		this.image = image;
		this.title = title;
	}

	public String getImageUrl() {
		return image;
	}

	public String getTitle() {
		return title;
	}

}