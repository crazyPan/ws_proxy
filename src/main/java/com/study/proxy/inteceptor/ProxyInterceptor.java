package com.study.proxy.inteceptor;

import java.io.IOException;
import java.io.InputStream;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;

import com.study.proxy.entity.SendQueue;
import com.study.proxy.entity.SendQueueList;
import com.study.proxy.entity.ServerAddress;
import com.study.proxy.entity.ServerInfo;
import com.study.proxy.util.ConfUtil;
/**
 * 提供对webservice消息的路由功能，因为只是实现高效转发，不需要提供具体服务，
 * 所以关键点就是 如何不解析soap消息，而只找到其路由的关键key，然后对其进行转发即可
 * 所以采用cxf的拦截器实现
 * 
 * @author crazyPan
 *
 */
public class ProxyInterceptor extends AbstractPhaseInterceptor<Message> {
	/**
	 * RECEIVE 放在消息接受到，但解析前拦截器
	 */
	public ProxyInterceptor() {
		super(Phase.RECEIVE);
	}

	
	public void handleMessage(Message message) throws Fault {
		 InputStream is = message.getContent(InputStream.class); 
		 try {
			 byte[] soapByte = null;
			 is.read(soapByte);
			 //1.把关键字段读出来
			 String soap = new String(soapByte);
			 //2.根据key，把请求消息扔给对应的处理队列
			 if(soap.contains(ConfUtil.getProxyKey())){
				 int start = soap.indexOf("");
				 int end = soap.indexOf("");
				 String proxyKey = soap.substring(start,end);
				 ServerInfo server = ConfUtil.getCenterInfo().getCenterMap().get(proxyKey);
				 //3.负载均衡 
				 ServerAddress address = balance(server,soap);
				 //4.放到对应的消息队列里，就会有对应的线程来扫描了
				 SendQueue sendqueue = SendQueueList.getSendQueue().get(address);
				 sendqueue.addqueue(soap);
			 } 
		 } catch (IOException e) {
			e.printStackTrace();
		 }
	}
	/**
	 * 根据请求负载均衡，定位到物理中心中特定server
	 * @param server 物理中心
	 * @param message 请求消息
	 * @return 
	 */
	private ServerAddress balance(ServerInfo server, String message) {
		//TODO:负载均衡
		
		return null;
	}
	
	
}
