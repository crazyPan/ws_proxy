package com.study.proxy.thread;

import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import com.study.proxy.entity.CenterInfo;
import com.study.proxy.entity.SendQueue;
import com.study.proxy.entity.SendQueueList;
import com.study.proxy.entity.ServerAddress;
import com.study.proxy.entity.ServerInfo;
import com.study.proxy.util.ConfUtil;

/*
 * 
 * TODO：线程池要再优化下
 * 
 */
public class ThreadPool {
	
	private static int threadnums = 0;
	//配置项：最大线程数
	private SendQueueList queuelist;
	private static final SendThread[] threads = null;


	public int getThreadnums() {
		return threadnums;
	}
	public static void  init(){
		threadnums = ConfUtil.getServernum();
		CenterInfo centers = ConfUtil.getBillingCenterInfo();
		Iterator<Entry<String, ServerInfo>> iter = centers.getCenterMap().entrySet().iterator();
		while(iter.hasNext()){
			Entry<String, ServerInfo> entry = (Entry<String, ServerInfo>) iter.next();
			ServerInfo center = entry.getValue();
			List<ServerAddress> servers = center.getServerInfo();
			for(ServerAddress address : servers){
				SendThread sendthread = new SendThread();
				String addressStr = address.getIp() + ":" + address.getPort();
				SendQueue sendqueue = SendQueueList.getSendQueue().get(addressStr);
				sendthread.init(address, sendqueue);
			}
		}
	}
	
	public SendThread[] getThreads() {
		return threads;
	}

	public void setQueuelist(SendQueueList queuelist) {
		this.queuelist = queuelist;
	}

	public SendQueueList getQueuelist() {
		return queuelist;
	}
}
