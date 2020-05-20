package com.github.surpassm.im.server.pojo;

import java.io.Serializable;

/**
 * Retain标志消息存储
 */
public class RetainMessageStore implements Serializable {

	private String topic;

	private byte[] messageBytes;


	public String getTopic() {
		return topic;
	}

	public RetainMessageStore setTopic(String topic) {
		this.topic = topic;
		return this;
	}

	public byte[] getMessageBytes() {
		return messageBytes;
	}

	public RetainMessageStore setMessageBytes(byte[] messageBytes) {
		this.messageBytes = messageBytes;
		return this;
	}
}
