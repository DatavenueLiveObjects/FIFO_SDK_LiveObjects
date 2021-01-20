/**
 * Copyright (c) Orange. All Rights Reserved.
 * <p>
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.orange.lo.sdk;

import com.orange.lo.sdk.externalconnector.DataManagementExtConnectorCommandCallback;
import com.orange.lo.sdk.fifomqtt.DataManagementFifoCallback;

import java.util.ArrayList;
import java.util.List;

public final class LOApiClientParameters {

    public static final String DEFAULT_HOSTNAME = "liveobjects.orange-business.com";
    public static final String DEFAULT_USERNAME = "application";
    public static final int DEFAULT_MESSAGE_QOS = 1;
    public static final int DEFAULT_KEEP_ALIVE_INTERVAL = 60;
    public static final int DEFAULT_MAX_INFLIGHT = 10;
    public static final boolean DEFAULT_CLEAN_SESSION = true;
    public static final int DEFAULT_CONNECTION_TIMEOUT = 30000;
    public static final String DEFAULT_EXT_CONNECTOR_USER = "connector";
    public static final String DEFAULT_EXT_CONNECTOR_COMMAND_REQUEST_TOPIC = "connector/v1/requests/command";
    public static final String DEFAULT_EXT_CONNECTOR_COMMAND_RESPONSE_TOPIC = "connector/v1/responses/command";
    public static final String DEFAULT_EXT_CONNECTOR_DATA_TOPIC_TEMPLATE = "connector/v1/nodes/%s/data";
    public static final String DEFAULT_EXT_CONNECTOR_STATUS_TOPIC_TEMPLATE = "connector/v1/nodes/%s/status";

    private final String apiKey;
    private final String hostname;
    private final String username;
    private final String extConnectorUsername;
    private final int messageQos;
    private final boolean automaticReconnect;
    private final boolean cleanSession;
	private final int keepAliveIntervalSeconds;
    private final int maxInflight;
    private final int connectionTimeout;
    private final List<String> topics;
    private final DataManagementFifoCallback dataManagementFifoCallback;
    private final DataManagementExtConnectorCommandCallback dataManagementExtConnectorCommandCallback;
    private final String extConnectorCommandRequestTopic;
    private final String extConnectorCommandResponseTopic;
    private final String extConnectorStatusTopicTemplate;
    private final String extConnectorDataTopicTemplate;

    private LOApiClientParameters(LOApiClientParametersBuilder builder) {
        this.apiKey = builder.apiKey;
        this.hostname = builder.hostname;
        this.username = builder.username;
        this.extConnectorUsername = builder.extConnectorUsername;
        this.messageQos = builder.messageQos;
        this.automaticReconnect = builder.automaticReconnect;
        this.cleanSession = builder.cleanSession;
        this.keepAliveIntervalSeconds = builder.keepAliveIntervalSeconds;
        this.maxInflight = builder.maxInflight;
        this.connectionTimeout = builder.connectionTimeout;
        this.topics = builder.topics;
        this.dataManagementFifoCallback = builder.dataManagementFifoCallback;
        this.dataManagementExtConnectorCommandCallback = builder.dataManagementExtConnectorCommandCallback;
        this.extConnectorCommandRequestTopic = builder.extConnectorCommandRequestTopic;
        this.extConnectorCommandResponseTopic = builder.extConnectorCommandResponseTopic;
        this.extConnectorStatusTopicTemplate = builder.extConnectorStatusTopicTemplate;
        this.extConnectorDataTopicTemplate = builder.extConnectorDataTopicTemplate;
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

    public String getExtConnectorUsername() {
        return extConnectorUsername;
    }

    public int getMessageQos() {
        return messageQos;
    }

    public boolean isAutomaticReconnect() {
        return automaticReconnect;
    }

    public boolean isCleanSession() {
		return cleanSession;
	}

    public int getKeepAliveIntervalSeconds() {
        return keepAliveIntervalSeconds;
    }
               
    public int getMaxInflight() {
    	return maxInflight;
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    public List<String> getTopics() {
        return topics;
    }

    public DataManagementFifoCallback getDataManagementFifoCallback() {
        return dataManagementFifoCallback;
    }

    public DataManagementExtConnectorCommandCallback getDataManagementExtConnectorCommandCallback() {
        return dataManagementExtConnectorCommandCallback;
    }

    public String getExtConnectorCommandResponseTopic() {
        return extConnectorCommandResponseTopic;
    }

    public String getExtConnectorStatusTopicTemplate() {
        return extConnectorStatusTopicTemplate;
    }

    public String getExtConnectorCommandRequestTopic() {
        return extConnectorCommandRequestTopic;
    }

    public String getExtConnectorDataTopicTemplate() {
        return extConnectorDataTopicTemplate;
    }

    public static LOApiClientParametersBuilder builder() {
        return new LOApiClientParametersBuilder();
    }

    public static final class LOApiClientParametersBuilder {

		private String apiKey;
        private String hostname = DEFAULT_HOSTNAME;
        private String username = DEFAULT_USERNAME;
        private String extConnectorUsername = DEFAULT_EXT_CONNECTOR_USER;
        private int messageQos = DEFAULT_MESSAGE_QOS;
        private boolean automaticReconnect;
        private boolean cleanSession = DEFAULT_CLEAN_SESSION;
        private int keepAliveIntervalSeconds = DEFAULT_KEEP_ALIVE_INTERVAL;
        private int maxInflight = DEFAULT_MAX_INFLIGHT;
        private int connectionTimeout = DEFAULT_CONNECTION_TIMEOUT;
        private List<String> topics = new ArrayList<>();
        private DataManagementFifoCallback dataManagementFifoCallback;
        private DataManagementExtConnectorCommandCallback dataManagementExtConnectorCommandCallback;
        private String extConnectorCommandRequestTopic = DEFAULT_EXT_CONNECTOR_COMMAND_REQUEST_TOPIC;
        private String extConnectorCommandResponseTopic = DEFAULT_EXT_CONNECTOR_COMMAND_RESPONSE_TOPIC;
        private String extConnectorStatusTopicTemplate = DEFAULT_EXT_CONNECTOR_STATUS_TOPIC_TEMPLATE;
        private String extConnectorDataTopicTemplate = DEFAULT_EXT_CONNECTOR_DATA_TOPIC_TEMPLATE;

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

        public LOApiClientParametersBuilder extConnectorUsername(String extUsername) {
            this.extConnectorUsername = extUsername;
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
        
        public LOApiClientParametersBuilder cleanSession(boolean cleanSession) {
            this.cleanSession = cleanSession;
            return this;
        }

        public LOApiClientParametersBuilder keepAliveIntervalSeconds(int keepAliveIntervalSeconds) {
            this.keepAliveIntervalSeconds = keepAliveIntervalSeconds;
            return this;
        }
        
        public LOApiClientParametersBuilder maxInflight(int maxInflight) {
            this.maxInflight = maxInflight;
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

        public LOApiClientParametersBuilder dataManagementMqttCallback(DataManagementFifoCallback dataManagementFifoCallback) {
            this.dataManagementFifoCallback = dataManagementFifoCallback;
            return this;
        }

        public LOApiClientParametersBuilder dataManagementExtConnectorCommandCallback(
                DataManagementExtConnectorCommandCallback dataManagementExtConnectorCommandCallback) {
            this.dataManagementExtConnectorCommandCallback = dataManagementExtConnectorCommandCallback;
            return this;
        }

        public LOApiClientParametersBuilder extConnectorCommandRequestTopic(String extConnectorCommandRequestTopic) {
            this.extConnectorCommandRequestTopic = extConnectorCommandRequestTopic;
            return this;
        }

        public LOApiClientParametersBuilder extConnectorCommandResponseTopic(String extConnectorCommandResponseTopic) {
            this.extConnectorCommandResponseTopic = extConnectorCommandResponseTopic;
            return this;
        }

        public LOApiClientParametersBuilder extConnectorStatusTopicTemplate(String extConnectorStatusTopicTemplate) {
            this.extConnectorStatusTopicTemplate = extConnectorStatusTopicTemplate;
            return this;
        }

        public LOApiClientParametersBuilder extConnectorDataTopicTemplate(String extConnectorDataTopicTemplate) {
            this.extConnectorDataTopicTemplate = extConnectorDataTopicTemplate;
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
            if ((!topics.isEmpty() && dataManagementFifoCallback == null) || (topics.isEmpty() && dataManagementFifoCallback != null)) {
                throw new IllegalArgumentException("Topics and DataManagementMqttCallback must be set simultaneously");
            }
        }
    }
}