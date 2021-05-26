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
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof RateLimit)) return false;
		RateLimit rateLimit = (RateLimit) o;
		return Objects.equals(getHttpMaxCalls(), rateLimit.getHttpMaxCalls())
				&& Objects.equals(getHttpWindowSize(), rateLimit.getHttpWindowSize())
				&& Objects.equals(getMqttBridgeMaxMessages(), rateLimit.getMqttBridgeMaxMessages())
				&& Objects.equals(getMqttBridgeWindowSize(), rateLimit.getMqttBridgeWindowSize())
				&& Objects.equals(getMqttDeviceMaxMessages(), rateLimit.getMqttDeviceMaxMessages())
				&& Objects.equals(getMqttDeviceWindowSize(), rateLimit.getMqttDeviceWindowSize());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getHttpMaxCalls(), getHttpWindowSize(), getMqttBridgeMaxMessages(),
				getMqttBridgeWindowSize(), getMqttDeviceMaxMessages(), getMqttDeviceWindowSize());
	}

	@Override
	public String toString() {
		return "RateLimit [httpMaxCalls=" + httpMaxCalls + ", httpWindowSize=" + httpWindowSize
				+ ", mqttBridgeMaxMessages=" + mqttBridgeMaxMessages + ", mqttBridgeWindowSize=" + mqttBridgeWindowSize
				+ ", mqttDeviceMaxMessages=" + mqttDeviceMaxMessages + ", mqttDeviceWindowSize=" + mqttDeviceWindowSize
				+ "]";
	}
}
