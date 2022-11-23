package com.orange.lo.sdk.rest.devicemanagement;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orange.lo.sdk.rest.model.Command;
import com.orange.lo.sdk.rest.model.CommandAddRequest;
import com.orange.lo.sdk.rest.model.CommandRequest;
import com.orange.lo.sdk.rest.model.CommandResponse;
import com.orange.lo.sdk.rest.model.CommandStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CommandsTest {

    @Mock
    private RestTemplate restTemplate;
    @Mock
    private ResponseEntity<Command> commandResponseEntity;
    @Mock
    private ResponseEntity<Map<String, String>> commandStatusResponseEntity;
    @Mock
    private ResponseEntity<List<Command>> commandsResponseEntity;
    @Mock
    private ResponseEntity<CommandResponse> commandsResponseResponseEntity;
    private Command command;
    private Commands commands;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        command = new Command();
        commands = new Commands(() -> restTemplate);
    }

    @Test
    void shouldCorrectlyCallRestTemplateWhenGettingCommandData() {
        String expectedEndpoint = "/v1/deviceMgt/commands/commandId";
        when(restTemplate.exchange(eq(expectedEndpoint), eq(HttpMethod.GET), any(), eq(Command.class)))
                .thenReturn(commandResponseEntity);
        when(commandResponseEntity.getBody()).thenReturn(command);

        Command command = commands.getCommand("commandId");

        assertNotNull(command);
        verify(restTemplate, times(1))
                .exchange(eq(expectedEndpoint), eq(HttpMethod.GET), any(), eq(Command.class));
    }

    @Test
    void shouldCorrectlyCallRestTemplateWhenDeletingCommand() {
        String expectedEndpoint = "/v1/deviceMgt/commands/commandId";

        commands.deleteCommand("commandId");

        verify(restTemplate, times(1))
                .exchange(eq(expectedEndpoint), eq(HttpMethod.DELETE), any(), eq(Void.class));
    }

    @Test
    void shouldCorrectlyCallRestTemplateWhenGettingCommandStatus() {
        String expectedEndpoint = "/v1/deviceMgt/commands/commandId/status";
        Map<String, String> response = Collections.singletonMap("content", "PROCESSED");
        when(restTemplate.exchange(eq(expectedEndpoint),
                eq(HttpMethod.GET),
                any(),
                ArgumentMatchers.<ParameterizedTypeReference<Map<String, String>>>any())
        ).thenReturn(commandStatusResponseEntity);
        when(commandStatusResponseEntity.getBody()).thenReturn(response);

        CommandStatus commandStatus = commands.getCommandStatus("commandId");

        assertEquals(CommandStatus.PROCESSED, commandStatus);
        verify(restTemplate, times(1))
                .exchange(eq(expectedEndpoint),
                        eq(HttpMethod.GET),
                        any(),
                        ArgumentMatchers.<ParameterizedTypeReference<Map<String, String>>>any()
                );
    }

    @Test
    void shouldCorrectlyCallRestTemplateWhenUpdatesCommandStatus() {
        String expectedEndpoint = "/v1/deviceMgt/commands/commandId/status";

        commands.setCommandStatus("commandId", CommandStatus.CANCELED);

        verify(restTemplate, times(1))
                .exchange(eq(expectedEndpoint), eq(HttpMethod.PUT), any(), eq(Void.class));
    }

    @Test
    void shouldCorrectlyCallRestTemplateWhenGettingCommands() {
        String expectedEndpoint = "/v1/deviceMgt/devices/deviceId/commands";
        when(restTemplate.exchange(eq(expectedEndpoint),
                eq(HttpMethod.GET),
                any(),
                ArgumentMatchers.<ParameterizedTypeReference<List<Command>>>any())
        ).thenReturn(commandsResponseEntity);
        when(commandsResponseEntity.getBody()).thenReturn(new ArrayList<>());

        List<Command> commandList = commands.getCommands("deviceId", new GetCommandsFilter());

        assertNotNull(commandList);
        verify(restTemplate, times(1))
                .exchange(eq(expectedEndpoint),
                        eq(HttpMethod.GET),
                        any(),
                        ArgumentMatchers.<ParameterizedTypeReference<List<Command>>>any()
                );
    }

    @Test
    void shouldCorrectlyCallRestTemplateWhenCreatingCommand() {
        String expectedEndpoint = "/v1/deviceMgt/devices/deviceId/commands?validate=true";
        when(restTemplate.exchange(eq(expectedEndpoint),
                eq(HttpMethod.POST),
                any(),
                eq(CommandResponse.class))
        ).thenReturn(commandsResponseResponseEntity);
        when(commandsResponseResponseEntity.getBody()).thenReturn(new CommandResponse());

        CommandResponse commandResponse = commands.addCommand("deviceId", new CommandAddRequest(), true);

        assertNotNull(commandResponse);
        verify(restTemplate, times(1))
                .exchange(eq(expectedEndpoint),
                        eq(HttpMethod.POST),
                        any(),
                        eq(CommandResponse.class)
                );
    }

    @Test
    void shouldCorrectlyMapMqttCommandJsonToCommandRequest() throws JsonProcessingException {

        String commandJson = "{\n" +
                "    \"request\": {\n" +
                "        \"connector\": \"mqtt\",\n" +
                "        \"value\": {\n" +
                "            \"req\": \"reboot\",\n" +
                "            \"arg\": {\n" +
                "                \"delay\": 1000\n" +
                "            }\n" +
                "        }\n" +
                "    },\n" +
                "    \"policy\": {\n" +
                "        \"expirationInSeconds\": 120,\n" +
                "        \"ackMode\": \"APPLICATIVE\"\n" +
                "    }\n" +
                "}";
        CommandAddRequest commandAddRequest = objectMapper.readValue(commandJson, CommandAddRequest.class);
        CommandRequest request = commandAddRequest.getRequest();
        Map<String, Object> value = request.getValue();

        Assertions.assertAll(
                () -> assertEquals("mqtt", request.getConnector()),
                () -> assertNotNull(value),
                () -> assertEquals(2, value.size()),
                () -> assertTrue(value.containsKey("req")),
                () -> assertTrue(value.containsKey("arg")),
                () -> assertTrue(value.get("arg") instanceof Map),
                () -> {
                    Map<String, Object> args = (Map<String, Object>) value.get("arg");
                    assertTrue(args.containsKey("delay"));
                }
        );
    }

    @Test
    void shouldCorrectlyMapLoraCommandJsonToCommandRequest() throws JsonProcessingException {
        String commandJson = "{\n" +
                "    \"request\": {\n" +
                "            \"connector\":\"lora\",\n" +
                "            \"value\":{\n" +
                "                    \"data\": \"A1FF20\",\n" +
                "                    \"port\": 1\n" +
                "            }\n" +
                "    },\n" +
                "    \"policy\": {\n" +
                "            \"expirationInSeconds\" : 200,\n" +
                "            \"ackMode\": \"NONE\"\n" +
                "    }\n" +
                "}";
        CommandAddRequest commandAddRequest = objectMapper.readValue(commandJson, CommandAddRequest.class);
        CommandRequest request = commandAddRequest.getRequest();
        Map<String, Object> value = request.getValue();

        Assertions.assertAll(
                () -> assertEquals("lora", request.getConnector()),
                () -> assertNotNull(value),
                () -> assertEquals(2, value.size()),
                () -> assertTrue(value.containsKey("data")),
                () -> assertTrue(value.containsKey("port"))
        );
    }

    @Test
    void shouldCorrectlyMapSmsTextCommandJsonToCommandRequest() throws JsonProcessingException {
        String commandJson = "{\n" +
                "    \"request\": {\n" +
                "            \"connector\": \"sms\",\n" +
                "        \"value\": {\n" +
                "            \"payload\": \"Hello Live Objects!\",\n" +
                "            \"type\": \"TEXT\",\n" +
                "            \"serverPhoneNumber\": \"20406\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"policy\": {\n" +
                "            \"expirationInSeconds\" : 30,\n" +
                "            \"ackMode\": \"NONE\"\n" +
                "    }\n" +
                "}";
        CommandAddRequest commandAddRequest = objectMapper.readValue(commandJson, CommandAddRequest.class);
        CommandRequest request = commandAddRequest.getRequest();
        Map<String, Object> value = request.getValue();

        Assertions.assertAll(
                () -> assertEquals("sms", request.getConnector()),
                () -> assertNotNull(value),
                () -> assertEquals(3, value.size()),
                () -> assertTrue(value.containsKey("payload")),
                () -> assertTrue(value.containsKey("type")),
                () -> assertTrue(value.containsKey("serverPhoneNumber"))
        );
    }

    @Test
    void shouldCorrectlyMapSmsBinaryCommandJsonToCommandRequest() throws JsonProcessingException {
        String commandJson = "{\n" +
                "    \"request\": {\n" +
                "      \"connector\": \"sms\",\n" +
                "      \"value\": {\n" +
                "        \"payload\": \"756e6c6f636b\",\n" +
                "        \"type\": \"BINARY\"\n" +
                "      }\n" +
                "    },\n" +
                "    \"policy\": {\n" +
                "        \"expirationInSeconds\": 60,\n" +
                "        \"ackMode\": \"NONE\"\n" +
                "    }\n" +
                " }";
        CommandAddRequest commandAddRequest = objectMapper.readValue(commandJson, CommandAddRequest.class);
        CommandRequest request = commandAddRequest.getRequest();
        Map<String, Object> value = request.getValue();

        Assertions.assertAll(
                () -> assertEquals("sms", request.getConnector()),
                () -> assertNotNull(value),
                () -> assertEquals(2, value.size()),
                () -> assertTrue(value.containsKey("payload")),
                () -> assertTrue(value.containsKey("type"))
        );
    }

    @Test
    void shouldCorrectlyMapExternalConnectorCommandJsonToCommandRequest() throws JsonProcessingException {
        String commandJson = "{\n" +
                "    \"request\": {\n" +
                "      \"connector\": \"x-connector\",\n" +
                "      \"value\": {\n" +
                "        \"myCommand\": \"turn on\",\n" +
                "        \"myParams\": {\n" +
                "          \"device\": \"6\"\n" +
                "        }\n" +
                "      }\n" +
                "    },\n" +
                "    \"policy\": {\n" +
                "      \"expirationInSeconds\": 60,\n" +
                "      \"ackMode\": \"APPLICATIVE\"\n" +
                "    }\n" +
                "  }";
        CommandAddRequest commandAddRequest = objectMapper.readValue(commandJson, CommandAddRequest.class);
        CommandRequest request = commandAddRequest.getRequest();
        Map<String, Object> value = request.getValue();

        Assertions.assertAll(
                () -> assertEquals("x-connector", request.getConnector()),
                () -> assertNotNull(value),
                () -> assertEquals(2, value.size()),
                () -> assertTrue(value.containsKey("myCommand")),
                () -> assertTrue(value.containsKey("myParams")),
                () -> assertTrue(value.get("myParams") instanceof Map),
                () -> {
                    Map<String, Object> args = (Map<String, Object>) value.get("myParams");
                    assertTrue(args.containsKey("device"));
                }
        );
    }
}