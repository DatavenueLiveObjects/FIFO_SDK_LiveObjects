/**
 * Copyright (c) Orange. All Rights Reserved.
 * <p>
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.orange.lo.sdk.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CommandHistory {

    @JsonProperty("deliveryStatus")
    private CommandDeliveryStatus commandDeliveryStatus = null;

    @JsonProperty("errorCode")
    private String errorCode = null;

    @JsonProperty("nodeId")
    private String nodeId = null;

    @JsonProperty("status")
    private CommandStatus status = null;

    @JsonProperty("timestamp")
    private String timestamp = null;

    public CommandHistory withDeliveryStatus(CommandDeliveryStatus commandDeliveryStatus) {
        this.commandDeliveryStatus = commandDeliveryStatus;
        return this;
    }

    public CommandHistory withErrorCode(String errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    public CommandHistory withNodeId(String nodeId) {
        this.nodeId = nodeId;
        return this;
    }

    public CommandHistory withStatus(CommandStatus status) {
        this.status = status;
        return this;
    }

    public CommandHistory withTimestamp(String timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public CommandDeliveryStatus getCommandDeliveryStatus() {
        return commandDeliveryStatus;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getNodeId() {
        return nodeId;
    }

    public CommandStatus getStatus() {
        return status;
    }

    public String getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "CommandHistory{" +
                "deliveryStatus=" + commandDeliveryStatus +
                ", errorCode='" + errorCode + '\'' +
                ", nodeId='" + nodeId + '\'' +
                ", status=" + status +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommandHistory)) return false;
        CommandHistory that = (CommandHistory) o;
        return getCommandDeliveryStatus() == that.getCommandDeliveryStatus()
                && Objects.equals(getErrorCode(), that.getErrorCode())
                && Objects.equals(getNodeId(), that.getNodeId())
                && getStatus() == that.getStatus()
                && Objects.equals(getTimestamp(), that.getTimestamp());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCommandDeliveryStatus(), getErrorCode(), getNodeId(), getStatus(), getTimestamp());
    }
}
