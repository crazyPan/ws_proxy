package com.study.proxy.entity;

import java.util.HashMap;
import java.util.Map;

public class CenterInfo {
	private Map<String,ServerInfo> centerMap = new HashMap<String,ServerInfo>();
	private int billingCenterNum;


	public void setBillingCenterNum(int billingCenterNum) {
		this.billingCenterNum = billingCenterNum;
	}

	public int getBillingCenterNum() {
		return billingCenterNum;
	}

	public void setCenterMap(Map<String,ServerInfo> centerMap) {
		this.centerMap = centerMap;
	}

	public Map<String,ServerInfo> getCenterMap() {
		return centerMap;
	}
	
}
