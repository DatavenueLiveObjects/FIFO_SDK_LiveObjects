package com.orange.lo.sdk;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

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

        assertNotNull(parameters.getDataManagementMqttCallback());
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