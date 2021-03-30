/**
 * Copyright (c) Orange. All Rights Reserved.
 * <p>
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.orange.lo.sdk.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Command {

    @JsonProperty("deliveryStatus")
    private CommandDeliveryStatus commandDeliveryStatus = null;

    @JsonProperty("created")
    private String created = null;

    @JsonProperty("errorCode")
    private String errorCode = null;

    @JsonProperty("history")
    private List<CommandHistory> history = null;

    @JsonProperty("id")
    private String id = null;

    @JsonProperty("policy")
    private CommandPolicy policy = null;

    @JsonProperty("request")
    private CommandRequest request = null;

    @JsonProperty("response")
    private CommandResponse response = null;

    @JsonProperty("status")
    private CommandStatus status = null;

    @JsonProperty("targetDeviceId")
    private String targetDeviceId = null;

    @JsonProperty("updated")
    private String updated = null;


    public Command withDeliveryStatus(CommandDeliveryStatus commandDeliveryStatus) {
        this.commandDeliveryStatus = commandDeliveryStatus;
        return this;
    }

    public Command withCreated(String created) {
        this.created = created;
        return this;
    }

    public Command withErrorCode(String errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    public Command withHistory(List<CommandHistory> history) {
        this.history = history;
        return this;
    }

    public Command withId(String id) {
        this.id = id;
        return this;
    }

    public Command withPolicy(CommandPolicy policy) {
        this.policy = policy;
        return this;
    }

    public Command withRequest(CommandRequest request) {
        this.request = request;
        return this;
    }

    public Command withResponse(CommandResponse response) {
        this.response = response;
        return this;
    }

    public Command withStatus(CommandStatus status) {
        this.status = status;
        return this;
    }

    public Command withTargetDeviceId(String targetDeviceId) {
        this.targetDeviceId = targetDeviceId;
        return this;
    }

    public Command withUpdated(String updated) {
        this.updated = updated;
        return this;
    }

    public CommandDeliveryStatus getDeliveryStatus() {
        return commandDeliveryStatus;
    }

    public String getCreated() {
        return created;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public List<CommandHistory> getHistory() {
        return history;
    }

    public String getId() {
        return id;
    }

    public CommandPolicy getPolicy() {
        return policy;
    }

    public CommandRequest getRequest() {
        return request;
    }

    public CommandResponse getResponse() {
        return response;
    }

    public CommandStatus getStatus() {
        return status;
    }

    public String getTargetDeviceId() {
        return targetDeviceId;
    }

    public String getUpdated() {
        return updated;
    }

    @Override
    public String toString() {
        return "Command{" +
                "deliveryStatus=" + commandDeliveryStatus +
                ", created='" + created + '\'' +
                ", errorCode='" + errorCode + '\'' +
                ", history=" + history +
                ", id='" + id + '\'' +
                ", policy=" + policy +
                ", request=" + request +
                ", response=" + response +
                ", status=" + status +
                ", targetDeviceId='" + targetDeviceId + '\'' +
                ", updated='" + updated + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Command that = (Command) o;
        return commandDeliveryStatus == that.commandDeliveryStatus
                && Objects.equals(created, that.created)
                && Objects.equals(errorCode, that.errorCode)
                && Objects.equals(history, that.history)
                && Objects.equals(id, that.id) && Objects.equals(policy, that.policy)
                && Objects.equals(request, that.request)
                && Objects.equals(response, that.response)
                && status == that.status
                && Objects.equals(targetDeviceId, that.targetDeviceId)
                && Objects.equals(updated, that.updated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commandDeliveryStatus, created, errorCode, history, id, policy, request, response, status, targetDeviceId, updated);
    }
}
