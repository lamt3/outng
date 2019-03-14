package com.dapp.web.outng.chat.models;

import java.util.Date;

public class ChatMessage {
	
	private String message;
	private Long userId;
	private Long sendToUserId; 
	private String messageType;
	private Date date;
	
	
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getSendToUserId() {
		return sendToUserId;
	}
	public void setSendToUserId(Long sendToUserId) {
		this.sendToUserId = sendToUserId;
	}
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	} 
	
	
}
