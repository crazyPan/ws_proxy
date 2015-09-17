package com.study.proxy.main;

import javax.xml.ws.Endpoint;

import org.apache.log4j.Logger;

import com.study.proxy.entity.SendQueueList;
import com.study.proxy.thread.ThreadPool;
import com.study.proxy.util.ConfUtil;

public class TransMain {
    
    private static org.apache.log4j.Logger log = Logger.getLogger(TransServerImpl.class);

    public static void main(String[] args) {
    	log.info("Starting Server");  
    	
    	init();
    	
        TransServerImpl demo = new TransServerImpl();  
//      String address = ConfUtil.getPropertyValue("ws_server_address");
//      System.out.println("address-------------"+address);
        Endpoint.publish("http://localhost:9000/ws2socket", demo);
        log.info("Start success");
	}
    
	public static void init(){
		//1.初始化配置信息
		ConfUtil.initConfig();
		//2.初始化消息队列
		SendQueueList.initQueueList();
		//3.创建与center数目一样的线程
		ThreadPool.init();
	}
}