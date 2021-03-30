/**
 * Copyright (c) Orange. All Rights Reserved.
 * <p>
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.orange.lo.sdk.rest.devicemanagement;

import com.orange.lo.sdk.rest.FilterUtils;
import com.orange.lo.sdk.rest.ResourceClient;
import com.orange.lo.sdk.rest.RestTemplateFactory;
import com.orange.lo.sdk.rest.model.Command;
import com.orange.lo.sdk.rest.model.CommandAddRequest;
import com.orange.lo.sdk.rest.model.CommandResponse;
import com.orange.lo.sdk.rest.model.CommandStatus;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;

public class Commands extends ResourceClient {

    private static final String COMMAND_ENDPOINT = "/v1/deviceMgt/commands/{commandId}";
    private static final String STATUS_ENDPOINT = "/v1/deviceMgt/commands/{commandId}/status";
    private static final String COMMANDS_ENDPOINT = "/v1/deviceMgt/devices/{deviceId}/commands";

    public Commands(RestTemplateFactory restTemplateFactory) {
        super(restTemplateFactory);
    }

    public Command getCommand(String commandId) {
        String uri = UriComponentsBuilder.fromUriString(COMMAND_ENDPOINT)
                .buildAndExpand(commandId)
                .toUriString();
        return get(uri, Command.class);
    }

    public void deleteCommand(String commandId) {
        String uri = UriComponentsBuilder.fromUriString(COMMAND_ENDPOINT)
                .buildAndExpand(commandId)
                .toUriString();
        delete(uri);
    }

    public CommandStatus getCommandStatus(String commandId) {
        String uri = UriComponentsBuilder.fromUriString(STATUS_ENDPOINT)
                .buildAndExpand(commandId)
                .toUriString();
        ParameterizedTypeReference<Map<String, String>> typeRef = new ParameterizedTypeReference<Map<String, String>>() {
        };
        Map<String, String> s = get(uri, typeRef);
        return CommandStatus.valueOf(s.get("content"));
    }

    public void setCommandStatus(String commandId, CommandStatus status) {
        String uri = UriComponentsBuilder.fromUriString(STATUS_ENDPOINT)
                .buildAndExpand(commandId)
                .toUriString();
        update(uri, status);
    }

    public List<Command> getCommands(String deviceId, GetCommandsFilter getCommandsFilter) {
        String uri = UriComponentsBuilder.fromUriString(COMMANDS_ENDPOINT)
                .queryParams(FilterUtils.toMap(getCommandsFilter))
                .buildAndExpand(deviceId)
                .toUriString();

        ParameterizedTypeReference<List<Command>> typeRef = new ParameterizedTypeReference<List<Command>>() {
        };
        return getMany(uri, typeRef);
    }

    public CommandResponse addCommand(String deviceId, CommandAddRequest body) {
        return this.addCommand(deviceId, body, true);
    }

    public CommandResponse addCommand(String deviceId, CommandAddRequest body, Boolean validate) {
        String uri = UriComponentsBuilder.fromUriString(COMMANDS_ENDPOINT)
                .queryParam("validate", validate)
                .buildAndExpand(deviceId)
                .toUriString();
        return create(uri, body, CommandResponse.class);
    }
}
