package com.orange.lo.sdk;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static com.orange.lo.sdk.LOApiClientParameters.DEFAULT_EXT_CONNECTOR_COMMAND_REQUEST_TOPIC;
import static com.orange.lo.sdk.LOApiClientParameters.DEFAULT_EXT_CONNECTOR_COMMAND_RESPONSE_TOPIC;
import static com.orange.lo.sdk.LOApiClientParameters.DEFAULT_EXT_CONNECTOR_DATA_TOPIC_TEMPLATE;
import static com.orange.lo.sdk.LOApiClientParameters.DEFAULT_EXT_CONNECTOR_STATUS_TOPIC_TEMPLATE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LOApiClientParametersTest {

    private static final String API_KEY = "abcDEfgH123I";
    private static final String HOSTNAME = "hostname.liveobjects.orange-business.com";
    private static final List<String> TOPICS = Arrays.asList("topic-01", "topic-02");

    @Test
    void shouldSetApiKey() {
        LOApiClientParameters parameters = LOApiClientParameters.builder()
                .apiKey(API_KEY)
                .build();

        assertEquals(API_KEY, parameters.getApiKey());
    }

    @Test
    void shouldSetDefaultHostnameWhenHostnameWasNotSet() {
        LOApiClientParameters parameters = LOApiClientParameters.builder()
                .apiKey(API_KEY)
                .build();

        assertEquals(LOApiClientParameters.DEFAULT_HOSTNAME, parameters.getHostname());
    }

    @Test
    void shouldChangeHostnameWhenHostnameWasSet() {
        LOApiClientParameters parameters = LOApiClientParameters.builder()
                .hostname(HOSTNAME)
                .apiKey(API_KEY)
                .build();

        assertEquals(HOSTNAME, parameters.getHostname());
    }

    @Test
    void shouldSetDefaultUsernameWhenUsernameWasNotSet() {
        LOApiClientParameters parameters = LOApiClientParameters.builder()
                .apiKey(API_KEY)
                .build();

        assertEquals(LOApiClientParameters.DEFAULT_USERNAME, parameters.getUsername());
    }

    @Test
    void shouldChangeUsernameWhenUsernameWasSet() {
        String username = "newUsername";
        LOApiClientParameters parameters = LOApiClientParameters.builder()
                .apiKey(API_KEY)
                .username(username)
                .build();

        assertEquals(username, parameters.getUsername());
    }

    @Test
    void shouldSetDefaultMessageQosWhenMessageQosWasNotSet() {
        LOApiClientParameters parameters = LOApiClientParameters.builder()
                .apiKey(API_KEY)
                .build();

        assertEquals(LOApiClientParameters.DEFAULT_MESSAGE_QOS, parameters.getMessageQos());
    }

    @Test
    void shouldChangeMessageQosWhenMessageQosWasSet() {
        int messageQos = 2;
        LOApiClientParameters parameters = LOApiClientParameters.builder()
                .apiKey(API_KEY)
                .messageQos(messageQos)
                .build();

        assertEquals(messageQos, parameters.getMessageQos());
    }

    @Test
    void shouldSetAutomaticReconnectWhenAutomaticReconnectWasSet() {
        boolean automaticReconnect = true;
        LOApiClientParameters parameters = LOApiClientParameters.builder()
                .apiKey(API_KEY)
                .automaticReconnect(automaticReconnect)
                .build();

        assertEquals(automaticReconnect, parameters.isAutomaticReconnect());
    }

    @Test
    void shouldSetDefaultKeepAliveIntervalSecondsWhenKeepAliveIntervalSecondsWasNotSet() {
        LOApiClientParameters parameters = LOApiClientParameters.builder()
                .apiKey(API_KEY)
                .build();

        assertEquals(LOApiClientParameters.DEFAULT_KEEP_ALIVE_INTERVAL, parameters.getKeepAliveIntervalSeconds());
    }

    @Test
    void shouldChangeKeepAliveIntervalSecondsWhenKeepAliveIntervalSecondsWasSet() {
        int keepAliveIntervalSeconds = 10;
        LOApiClientParameters parameters = LOApiClientParameters.builder()
                .apiKey(API_KEY)
                .keepAliveIntervalSeconds(keepAliveIntervalSeconds)
                .build();

        assertEquals(keepAliveIntervalSeconds, parameters.getKeepAliveIntervalSeconds());
    }


    @Test
    void shouldSetDefaultConnectionTimeoutWhenConnectionTimeoutWasNotSet() {
        LOApiClientParameters parameters = LOApiClientParameters.builder()
                .apiKey(API_KEY)
                .build();

        assertEquals(LOApiClientParameters.DEFAULT_CONNECTION_TIMEOUT, parameters.getConnectionTimeout());
    }

    @Test
    void shouldChangeConnectionTimeoutWhenConnectionTimeoutWasSet() {
        int connectionTimeout = 10;
        LOApiClientParameters parameters = LOApiClientParameters.builder()
                .apiKey(API_KEY)
                .connectionTimeout(connectionTimeout)
                .build();

        assertEquals(connectionTimeout, parameters.getConnectionTimeout());
    }

    @Test
    void shouldSetTopics() {
        LOApiClientParameters parameters = LOApiClientParameters.builder()
                .apiKey(API_KEY)
                .topics(TOPICS)
                .dataManagementMqttCallback(System.out::println)
                .build();

        assertEquals(TOPICS, parameters.getTopics());
    }

    @Test
    void shouldSetDataManagementMqttCallback() {
        LOApiClientParameters parameters = LOApiClientParameters.builder()
                .apiKey(API_KEY)
                .topics(TOPICS)
                .dataManagementMqttCallback(System.out::println)
                .build();

        assertNotNull(parameters.getDataManagementFifoCallback());
    }

    @Test
    void shouldSetDefaultExtConnectorUsernameWhenUsernameWasNotSet() {
        LOApiClientParameters parameters = LOApiClientParameters.builder()
                .apiKey(API_KEY)
                .build();

        assertEquals(LOApiClientParameters.DEFAULT_EXT_CONNECTOR_USER, parameters.getExtConnectorUsername());
    }

    @Test
    void shouldChangeExtConnectorUsernameWhenUsernameWasSet() {
        String username = "newExtUsername";
        LOApiClientParameters parameters = LOApiClientParameters.builder()
                .apiKey(API_KEY)
                .extConnectorUsername(username)
                .build();

        assertEquals(username, parameters.getExtConnectorUsername());
    }

    @Test
    void shouldSetDataManagementExtConnectorCommandCallback() {
        LOApiClientParameters parameters = LOApiClientParameters.builder()
                .apiKey(API_KEY)
                .dataManagementExtConnectorCommandCallback((commandRequest) -> null)
                .build();

        assertNotNull(parameters.getDataManagementExtConnectorCommandCallback());
    }

    @Test
    void shouldSetDefaultCommandResponseTopicWhenCommandResponseTopicWasNotSet() {
        LOApiClientParameters parameters = LOApiClientParameters.builder()
                .apiKey(API_KEY)
                .build();

        assertEquals(DEFAULT_EXT_CONNECTOR_COMMAND_RESPONSE_TOPIC, parameters.getExtConnectorCommandResponseTopic());
    }

    @Test
    void shouldChangeDefaultExtConnectorCommandRequestTopicWhenCommandResponseTopicWasSet() {
        String commandResponseTopic = "new/command/response/topic";
        LOApiClientParameters parameters = LOApiClientParameters.builder()
                .apiKey(API_KEY)
                .extConnectorCommandResponseTopic(commandResponseTopic)
                .build();

        assertEquals(commandResponseTopic, parameters.getExtConnectorCommandResponseTopic());
    }

    @Test
    void shouldSetDefaultExtConnectorCommandRequestTopicWhenCommandRequestTopicWasNotSet() {
        LOApiClientParameters parameters = LOApiClientParameters.builder()
                .apiKey(API_KEY)
                .build();

        assertEquals(DEFAULT_EXT_CONNECTOR_COMMAND_REQUEST_TOPIC, parameters.getExtConnectorCommandRequestTopic());
    }

    @Test
    void shouldChangeDefaultExtConnectorCommandRequestTopicWhenCommandRequestTopicWasSet() {
        String commandRequestTopic = "new/command/request/topic";
        LOApiClientParameters parameters = LOApiClientParameters.builder()
                .apiKey(API_KEY)
                .extConnectorCommandRequestTopic(commandRequestTopic)
                .build();

        assertEquals(commandRequestTopic, parameters.getExtConnectorCommandRequestTopic());
    }

    @Test
    void shouldSetDefaultExtConnectorStatusTopicTemplateWhenStatusTopicTemplateWasNotSet() {
        LOApiClientParameters parameters = LOApiClientParameters.builder()
                .apiKey(API_KEY)
                .build();

        assertEquals(DEFAULT_EXT_CONNECTOR_STATUS_TOPIC_TEMPLATE, parameters.getExtConnectorStatusTopicTemplate());
    }

    @Test
    void shouldChangeDefaultExtConnectorStatusTopicTemplateWhenStatusTopicTemplateWasSet() {
        String statusTopicTemplate = "/%s/new/status/topic/template";
        LOApiClientParameters parameters = LOApiClientParameters.builder()
                .apiKey(API_KEY)
                .extConnectorStatusTopicTemplate(statusTopicTemplate)
                .build();

        assertEquals(statusTopicTemplate, parameters.getExtConnectorStatusTopicTemplate());
    }

    @Test
    void shouldSetDefaultDataTopicTemplateWhenDataTopicTemplateWasNotSet() {
        LOApiClientParameters parameters = LOApiClientParameters.builder()
                .apiKey(API_KEY)
                .build();

        assertEquals(DEFAULT_EXT_CONNECTOR_DATA_TOPIC_TEMPLATE, parameters.getExtConnectorDataTopicTemplate());
    }

    @Test
    void shouldChangeDefaultDataTopicTemplateWhenDataTopicTemplateWasSet() {
        String dataTopicTemplate = "/%s/new/data/topic/template";
        LOApiClientParameters parameters = LOApiClientParameters.builder()
                .apiKey(API_KEY)
                .extConnectorDataTopicTemplate(dataTopicTemplate)
                .build();

        assertEquals(dataTopicTemplate, parameters.getExtConnectorDataTopicTemplate());
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenTopicsAreSetAndCallbackIsNot() {
        LOApiClientParameters.LOApiClientParametersBuilder builder = LOApiClientParameters.builder()
                .apiKey(API_KEY)
                .topics(TOPICS);

        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, builder::build);
        assertEquals("Topics and DataManagementMqttCallback must be set simultaneously", illegalArgumentException.getMessage());
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenCallbackIsSetAndTopicsAreNot() {
        LOApiClientParameters.LOApiClientParametersBuilder builder = LOApiClientParameters.builder()
                .apiKey(API_KEY)
                .dataManagementMqttCallback(System.out::println);

        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, builder::build);
        assertEquals("Topics and DataManagementMqttCallback must be set simultaneously", illegalArgumentException.getMessage());
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenApiKeyIsNotSet() {
        LOApiClientParameters.LOApiClientParametersBuilder builder = LOApiClientParameters.builder();

        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, builder::build);
        assertEquals("Api key and hostname are required", illegalArgumentException.getMessage());
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenHostnameIsNotSet() {
        LOApiClientParameters.LOApiClientParametersBuilder builder = LOApiClientParameters.builder()
                .hostname(null)
                .apiKey(API_KEY);

        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, builder::build);
        assertEquals("Api key and hostname are required", illegalArgumentException.getMessage());
    }
}