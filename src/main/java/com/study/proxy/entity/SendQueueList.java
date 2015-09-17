package com.study.proxy.entity;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;


import com.study.proxy.util.ConfUtil;

public class SendQueueList {
	private static final Map<String, SendQueue> sendQueue = new ConcurrentHashMap<String, SendQueue>();

	public static Map<String, SendQueue> getSendQueue() {
		return sendQueue;
	}

	public static void initQueueList() {
			if (null == sendQueue || sendQueue.size() == 0) {
				CenterInfo centers = ConfUtil.getCenterInfo();
				Iterator<Entry<String, ServerInfo>> iter = centers.getCenterMap().entrySet().iterator();
				while (iter.hasNext()) {
					Entry<String, ServerInfo> entry = (Entry<String, ServerInfo>) iter
							.next();
					
					ServerInfo center = entry.getValue();
					List<ServerAddress> servers = center.getServerInfo();
					for(ServerAddress address: servers){
						String addressStr = address.getIp() + ":" + address.getPort();
						SendQueue queue = new SendQueue();
						sendQueue.put(addressStr, queue);
					}
			}
		}

	}
}
