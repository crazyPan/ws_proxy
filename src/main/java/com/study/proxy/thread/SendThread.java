package com.study.proxy.thread;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import com.study.proxy.entity.SendQueue;
import com.study.proxy.entity.ServerAddress;
import com.study.proxy.util.ConfUtil;

/**
 * 发送线程，维护一个socket长连接
 * 扫描对应的队列
 */
public class SendThread extends Thread {
	 /*
	  * socket长连接
	  */
	 private static Socket client = null;
	 /*
	  * 消息队列
	  */
	 private SendQueue sendqueue;
	/*
	 * 线程对应的server地址
	 */
	 private ServerAddress address;
	/*
	 * 长连接超时时间
	 */
	 private int timeout;

	 
	 private OutputStream out = null;
	 private InputStream in = null;
	 
	public void init(ServerAddress address,SendQueue sendqueue){
		 this.address = address;
		 this.timeout = ConfUtil.getTimeout();
		 connect();
		 this.sendqueue = sendqueue;
	 }
	 
	 @Override
	public void run() {
		 while(true){
			 
			 synchronized (sendqueue) {
				//1.扫描消息队列，读取消息
				 String message = sendqueue.getRemoveFirst();
				 send(message);

				 receive();
				 try {
					 Thread.sleep(1000);
				 } catch (InterruptedException e) {
					 e.printStackTrace();
				 }
			 }
		 }
	}
	 
	 private void connect(){
		 try {
			 if(client != null && !client.isClosed())
				 return;
			 client = new Socket(address.getIp(),address.getPort());
			 client.setKeepAlive(true);
			 client.setSoTimeout(timeout);
			 out = client.getOutputStream();
			 in = client.getInputStream();
		 } catch (Exception e) {
			 e.printStackTrace();
		 }finally{
			 disConnet();
		 }
	 }
	 
	 private void disConnet(){
		 try {
			if(null != client){
				client.close();
			}
			if(null != out){
				out.close();
			}
			if(null != in){
				in.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	 }
	 
	 private void send(String message){
		 if(message != null && !message.isEmpty()){
			 //2.调用socket发送消息
              try {
            	  connect();
                  out.write(message.getBytes());  
            	  out.flush();
            	   
			} catch (IOException e) {
				e.printStackTrace();
			}  
		 }
	 }
	 
	 private void receive(){
		 
	 }
}
