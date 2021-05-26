/**
 * Copyright (c) Orange. All Rights Reserved.
 * <p>
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.orange.lo.sdk.rest.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Activity {

    @JsonProperty("apiKeyId")
    private String apiKeyId;

    @JsonProperty("mqttVersion")
    private Integer mqttVersion;

    @JsonProperty("mqttUsername")
    private String mqttUsername;

    @JsonProperty("mqttTimeout")
    private Integer mqttTimeout;

    @JsonProperty("remoteAddress")
    private String remoteAddress;

    @JsonProperty("lastSessionStartTime")
    private String lastSessionStartTime;

    @JsonProperty("lastSessionEndTime")
    private String lastSessionEndTime;

    public Activity withApiKeyId(String apiKeyId) {
        this.apiKeyId = apiKeyId;
        return this;
    }

    public Activity withMqttVersion(Integer mqttVersion) {
        this.mqttVersion = mqttVersion;
        return this;
    }

    public Activity withMqttUsername(String mqttUsername) {
        this.mqttUsername = mqttUsername;
        return this;
    }

    public Activity withMqttTimeout(Integer mqttTimeout) {
        this.mqttTimeout = mqttTimeout;
        return this;
    }

    public Activity withRemoteAddress(String remoteAddress) {
        this.remoteAddress = remoteAddress;
        return this;
    }

    public Activity withLastSessionStartTime(String lastSessionStartTime) {
        this.lastSessionStartTime = lastSessionStartTime;
        return this;
    }

    public Activity withLastSessionEndTime(String lastSessionEndTime) {
        this.lastSessionEndTime = lastSessionEndTime;
        return this;
    }

    public String getApiKeyId() {
        return apiKeyId;
    }

    public Integer getMqttVersion() {
        return mqttVersion;
    }

    public String getMqttUsername() {
        return mqttUsername;
    }

    public Integer getMqttTimeout() {
        return mqttTimeout;
    }

    public String getRemoteAddress() {
        return remoteAddress;
    }

    public String getLastSessionStartTime() {
        return lastSessionStartTime;
    }

    public String getLastSessionEndTime() {
        return lastSessionEndTime;
    }

    @Override
    public String toString() {
        return "Activity [apiKeyId=" + apiKeyId + ", mqttVersion=" + mqttVersion + ", mqttUsername=" + mqttUsername + ", mqttTimeout=" + mqttTimeout + ", remoteAddress=" + remoteAddress + ", lastSessionStartTime=" + lastSessionStartTime + ", lastSessionEndTime=" + lastSessionEndTime + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Activity)) return false;
        Activity activity = (Activity) o;
        return Objects.equals(getApiKeyId(), activity.getApiKeyId())
                && Objects.equals(getMqttVersion(), activity.getMqttVersion())
                && Objects.equals(getMqttUsername(), activity.getMqttUsername())
                && Objects.equals(getMqttTimeout(), activity.getMqttTimeout())
                && Objects.equals(getRemoteAddress(), activity.getRemoteAddress())
                && Objects.equals(getLastSessionStartTime(), activity.getLastSessionStartTime())
                && Objects.equals(getLastSessionEndTime(), activity.getLastSessionEndTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getApiKeyId(), getMqttVersion(), getMqttUsername(), getMqttTimeout(), getRemoteAddress(),
                getLastSessionStartTime(), getLastSessionEndTime());
    }
}
