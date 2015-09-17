package com.study.proxy.entity;

import java.util.ArrayList;
import java.util.List;

public class ServerInfo {
	private List<ServerAddress> serverInfo = new ArrayList<ServerAddress>();

	public void setServerInfo(List<ServerAddress> serverInfo) {
		this.serverInfo = serverInfo;
	}

	public List<ServerAddress> getServerInfo() {
		return serverInfo;
	}
}
