package com.study.proxy.entity;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class VacRequest {

	public final static String lineBreak = "\0";

	/**
	 * 
	 */
	private String eventId;

	/**
	 * 
	 */
	private String eventTimestamp;

	/**
	 * 
	 */
	private String subscriptionId;

	/**
	 * 
	 */
	private String requestedAction;

	/**
	 * 
	 */
	private String refundsessionid;

	/**
	 * 
	 */
	private String chargingType;

	/**
	 * 
	 */
	private String chargePartyType;

	/**
	 * 
	 */
	private String serviceID;

	/**
	 * 
	 */
	private String sPId;

	/**
	 * 
	 */
	private String serviceType;

	/**
	 * 
	 */
	private String fee;

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getEventTimestamp() {
		return eventTimestamp;
	}

	public void setEventTimestamp(String eventTimestamp) {
		this.eventTimestamp = eventTimestamp;
	}

	public String getSubscriptionId() {
		return subscriptionId;
	}

	public void setSubscriptionId(String subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	public String getRequestedAction() {
		return requestedAction;
	}

	public void setRequestedAction(String requestedAction) {
		this.requestedAction = requestedAction;
	}

	public String getRefundsessionid() {
		return refundsessionid;
	}

	public void setRefundsessionid(String refundsessionid) {
		this.refundsessionid = refundsessionid;
	}

	public String getChargingType() {
		return chargingType;
	}

	public void setChargingType(String chargingType) {
		this.chargingType = chargingType;
	}

	public String getChargePartyType() {
		return chargePartyType;
	}

	public void setChargePartyType(String chargePartyType) {
		this.chargePartyType = chargePartyType;
	}

	public String getServiceID() {
		return serviceID;
	}

	public void setServiceID(String serviceID) {
		this.serviceID = serviceID;
	}

	public String getsPId() {
		return sPId;
	}

	public void setsPId(String sPId) {
		this.sPId = sPId;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getFee() {
		return fee;
	}

	public void setFee(String fee) {
		this.fee = fee;
	}

	public String toString() {
		return new StringBuffer().append(eventId == null ? "" : eventId)
				.append(lineBreak)
				.append(eventTimestamp == null ? "" : eventTimestamp)
				.append(lineBreak)
				.append(subscriptionId == null ? "" : subscriptionId)
				.append(lineBreak)
				.append(requestedAction == null ? "" : requestedAction)
				.append(lineBreak)
				.append(refundsessionid == null ? "" : refundsessionid)
				.append(lineBreak)
				.append(chargingType == null ? "" : chargingType)
				.append(lineBreak)
				.append(chargePartyType == null ? "" : chargePartyType)
				.append(lineBreak)
				.append(serviceID == null ? "" : serviceID)
				.append(lineBreak)
				.append(sPId == null ? "" : sPId)
				.append(lineBreak)
				.append(serviceType == null ? "" : serviceType)
				.append(lineBreak)
				.append(fee == null ? "" : fee)
				.append(lineBreak).toString();
	}

	public void print() {
		System.out.println("eventId:" + this.getEventId() );
		System.out.println("EventTimestamp:" + this.getEventTimestamp() );
		System.out.println("subscriptionId:" + this.getSubscriptionId() );
		System.out.println("requestedAction:" + this.getRequestedAction() );
		System.out.println("refundsessionid:" + this.getRefundsessionid() );
		System.out.println("chargingType:" + this.getChargingType() );
		System.out.println("chargePartyType:" + this.getChargePartyType());
		System.out.println("serviceID:" + this.getServiceID() );
		System.out.println("sPId:" + this.getsPId());
		System.out.println("serviceType:" + this.getServiceType());
		System.out.println("fee:" + this.getFee() );

	}
	
	
	public static void main(String[] args) {
		VacRequest vac = new VacRequest();
		vac.setEventId("6789");
		vac.setEventTimestamp("20150512");
		vac.setServiceID("");
		vac.setFee("4567");

		try {
			System.out.println(URLEncoder.encode(vac.toString(), "UTF-8"));
			System.out.println(vac.toString());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
