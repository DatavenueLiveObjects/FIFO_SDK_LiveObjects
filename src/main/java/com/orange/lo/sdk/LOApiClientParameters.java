/**
 * Copyright (c) Orange. All Rights Reserved.
 * <p>
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.orange.lo.sdk;

import com.orange.lo.sdk.mqtt.DataManagementMqttCallback;

import java.util.ArrayList;
import java.util.List;

public final class LOApiClientParameters {

    public static final String DEFAULT_HOSTNAME = "liveobjects.orange-business.com";
    public static final String DEFAULT_USERNAME = "application";
    public static final int DEFAULT_MESSAGE_QOS = 1;
    public static final int DEFAULT_KEEP_ALIVE_INTERVAL = 0;
    public static final int DEFAULT_CONNECTION_TIMEOUT = 30000;

    private final String apiKey;
    private final String hostname;
    private final String username;
    private final int messageQos;
    private final boolean automaticReconnect;
    private final int keepAliveIntervalSeconds;
    private final int connectionTimeout;
    private final List<String> topics;
    private final DataManagementMqttCallback dataManagementMqttCallback;

    private LOApiClientParameters(LOApiClientParametersBuilder builder) {
        this.apiKey = builder.apiKey;
        this.hostname = builder.hostname;
        this.username = builder.username;
        this.messageQos = builder.messageQos;
        this.automaticReconnect = builder.automaticReconnect;
        this.keepAliveIntervalSeconds = builder.keepAliveIntervalSeconds;
        this.connectionTimeout = builder.connectionTimeout;
        this.topics = builder.topics;
        this.dataManagementMqttCallback = builder.dataManagementMqttCallback;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getHostname() {
        return hostname;
    }

    public String getUsername() {
        return username;
    }

    public int getMessageQos() {
        return messageQos;
    }

    public boolean isAutomaticReconnect() {
        return automaticReconnect;
    }

    public int getKeepAliveIntervalSeconds() {
        return keepAliveIntervalSeconds;
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    public List<String> getTopics() {
        return topics;
    }

    public DataManagementMqttCallback getDataManagementMqttCallback() {
        return dataManagementMqttCallback;
    }

    public static LOApiClientParametersBuilder builder() {
        return new LOApiClientParametersBuilder();
    }

    public static final class LOApiClientParametersBuilder {

        private String apiKey;
        private String hostname = DEFAULT_HOSTNAME;
        private String username = DEFAULT_USERNAME;
        private int messageQos = DEFAULT_MESSAGE_QOS;
        private boolean automaticReconnect;
        private int keepAliveIntervalSeconds = DEFAULT_KEEP_ALIVE_INTERVAL;
        private int connectionTimeout = DEFAULT_CONNECTION_TIMEOUT;
        private List<String> topics = new ArrayList<>();
        private DataManagementMqttCallback dataManagementMqttCallback;

        public LOApiClientParametersBuilder apiKey(String apiKey) {
            this.apiKey = apiKey;
            return this;
        }

        public LOApiClientParametersBuilder hostname(String hostname) {
            this.hostname = hostname;
            return this;
        }

        public LOApiClientParametersBuilder username(String username) {
            this.username = username;
            return this;
        }

        public LOApiClientParametersBuilder messageQos(int messageQos) {
            this.messageQos = messageQos;
            return this;
        }

        public LOApiClientParametersBuilder automaticReconnect(boolean automaticReconnect) {
            this.automaticReconnect = automaticReconnect;
            return this;
        }

        public LOApiClientParametersBuilder keepAliveIntervalSeconds(int keepAliveIntervalSeconds) {
            this.keepAliveIntervalSeconds = keepAliveIntervalSeconds;
            return this;
        }

        public LOApiClientParametersBuilder connectionTimeout(int connectionTimeout) {
            this.connectionTimeout = connectionTimeout;
            return this;
        }

        public LOApiClientParametersBuilder topics(List<String> topics) {
            this.topics = topics;
            return this;
        }

        public LOApiClientParametersBuilder dataManagementMqttCallback(DataManagementMqttCallback dataManagementMqttCallback) {
            this.dataManagementMqttCallback = dataManagementMqttCallback;
            return this;
        }

        public LOApiClientParameters build() {
            validate();
            return new LOApiClientParameters(this);
        }

        private void validate() {
            if (this.apiKey == null || this.apiKey.trim().length() == 0 || this.hostname == null || this.hostname.trim().length() == 0) {
                throw new IllegalArgumentException("Api key and hostname are required");
            }
            if ((!topics.isEmpty() && dataManagementMqttCallback == null) || (topics.isEmpty() && dataManagementMqttCallback != null)) {
                throw new IllegalArgumentException("Topics and DataManagementMqttCallback must be set simultaneously");
            }
        }
    }
}