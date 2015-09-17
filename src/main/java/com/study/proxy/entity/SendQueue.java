package com.study.proxy.entity;

import java.util.LinkedList;


public class SendQueue {
    private LinkedList<String> queue = new LinkedList<String>();

	public void setQueue(LinkedList<String> queue) {
		this.queue = queue;
	}

	public LinkedList<String> getQueue() {
		return queue;
	}
	
	public boolean addqueue(String message){
		synchronized (queue) {
			queue.addLast(message);
		}
		return false;
	}
	
    /**
     * 队列为空时，返回null，不为空，返回第一个元素
     */
    public String getRemoveFirst()
    {
        synchronized (queue)
        {
            return queue.poll();
        }
    }
	
}
