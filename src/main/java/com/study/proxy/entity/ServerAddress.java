package com.study.proxy.entity;

public class ServerAddress {

	private String ip;
	private int port;
	
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getIp() {
		return ip;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public int getPort() {
		return port;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		return super.equals(obj);
	}
	
	@Override
	public int hashCode() {
		
		
		return super.hashCode();
	}
	
}
