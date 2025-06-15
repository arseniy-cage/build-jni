package com.saint.blackrussia.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Servers {

	@SerializedName("ip")
	@Expose
	private String ip;

	@SerializedName("port")
	@Expose
	private int port;

	@SerializedName("x2")
	@Expose
	private Boolean x2;

	@SerializedName("name")
	@Expose
	private String name;

	@SerializedName("online")
	@Expose
	private int online;

	@SerializedName("maxonline")
	@Expose
	private int maxonline;

	public Servers(String ip, int port, Boolean x2, String name, int online, int maxonline) {
		this.ip = ip;
		this.port = port;
		this.x2 = x2;
		this.name = name;
		this.online = online;
		this.maxonline = maxonline;
	}
	 
	public String getname() {
		return name;
	}

	public String getIP() {
		return ip;
	}

	public int getPORT(){
		return port;
	}

    public Boolean getx2() {
		 return x2;
	}
	
	public int getOnline(){
		return online;
	}

	public int getmaxOnline(){
		return maxonline;
	}
}