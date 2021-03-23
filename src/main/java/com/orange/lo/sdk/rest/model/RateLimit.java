package com.orange.lo.sdk.rest.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RateLimit {

	@JsonProperty("httpMaxCalls")
	private Long httpMaxCalls = null;

	@JsonProperty("httpWindowSize")
	private Long httpWindowSize = null;

	@JsonProperty("mqttBridgeMaxMessages")
	private Long mqttBridgeMaxMessages = null;

	@JsonProperty("mqttBridgeWindowSize")
	private Long mqttBridgeWindowSize = null;

	@JsonProperty("mqttDeviceMaxMessages")
	private Long mqttDeviceMaxMessages = null;

	@JsonProperty("mqttDeviceWindowSize")
	private Long mqttDeviceWindowSize = null;

	public RateLimit withHttpMaxCalls(Long httpMaxCalls) {
		this.httpMaxCalls = httpMaxCalls;
		return this;
	}

	public Long getHttpMaxCalls() {
		return httpMaxCalls;
	}

	public RateLimit withHttpWindowSize(Long httpWindowSize) {
		this.httpWindowSize = httpWindowSize;
		return this;
	}

	public Long getHttpWindowSize() {
		return httpWindowSize;
	}

	public RateLimit withMqttBridgeMaxMessages(Long mqttBridgeMaxMessages) {
		this.mqttBridgeMaxMessages = mqttBridgeMaxMessages;
		return this;
	}

	public Long getMqttBridgeMaxMessages() {
		return mqttBridgeMaxMessages;
	}

	public RateLimit withRqttBridgeWindowSize(Long mqttBridgeWindowSize) {
		this.mqttBridgeWindowSize = mqttBridgeWindowSize;
		return this;
	}

	public Long getMqttBridgeWindowSize() {
		return mqttBridgeWindowSize;
	}

	public RateLimit withMqttDeviceMaxMessages(Long mqttDeviceMaxMessages) {
		this.mqttDeviceMaxMessages = mqttDeviceMaxMessages;
		return this;
	}

	public Long getMqttDeviceMaxMessages() {
		return mqttDeviceMaxMessages;
	}

	public RateLimit mqttDeviceWindowSize(Long mqttDeviceWindowSize) {
		this.mqttDeviceWindowSize = mqttDeviceWindowSize;
		return this;
	}

	public Long getMqttDeviceWindowSize() {
		return mqttDeviceWindowSize;
	}

	@Override
	public int hashCode() {
		return Objects.hash(httpMaxCalls, httpWindowSize, mqttBridgeMaxMessages, mqttBridgeWindowSize,
				mqttDeviceMaxMessages, mqttDeviceWindowSize);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof RateLimit)) {
			return false;
		}
		RateLimit other = (RateLimit) obj;
		return Objects.equals(httpMaxCalls, other.httpMaxCalls) && Objects.equals(httpWindowSize, other.httpWindowSize)
				&& Objects.equals(mqttBridgeMaxMessages, other.mqttBridgeMaxMessages)
				&& Objects.equals(mqttBridgeWindowSize, other.mqttBridgeWindowSize)
				&& Objects.equals(mqttDeviceMaxMessages, other.mqttDeviceMaxMessages)
				&& Objects.equals(mqttDeviceWindowSize, other.mqttDeviceWindowSize);
	}

	@Override
	public String toString() {
		return "RateLimit [httpMaxCalls=" + httpMaxCalls + ", httpWindowSize=" + httpWindowSize
				+ ", mqttBridgeMaxMessages=" + mqttBridgeMaxMessages + ", mqttBridgeWindowSize=" + mqttBridgeWindowSize
				+ ", mqttDeviceMaxMessages=" + mqttDeviceMaxMessages + ", mqttDeviceWindowSize=" + mqttDeviceWindowSize
				+ "]";
	}
}