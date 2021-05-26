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
public class CommandPolicy {

    @JsonProperty("ackMode")
    private AckMode ackMode = null;

    @JsonProperty("ackTimeoutInSeconds")
    private Long ackTimeoutInSeconds = null;

    @JsonProperty("attempts")
    private Integer attempts = null;

    @JsonProperty("expirationInSeconds")
    private Long expirationInSeconds = null;

    public CommandPolicy withAckMode(AckMode ackMode) {
        this.ackMode = ackMode;
        return this;
    }

    public CommandPolicy withAckTimeoutInSeconds(Long ackTimeoutInSeconds) {
        this.ackTimeoutInSeconds = ackTimeoutInSeconds;
        return this;
    }

    public CommandPolicy withAttempts(Integer attempts) {
        this.attempts = attempts;
        return this;
    }

    public CommandPolicy withExpirationInSeconds(Long expirationInSeconds) {
        this.expirationInSeconds = expirationInSeconds;
        return this;
    }

    public AckMode getAckMode() {
        return ackMode;
    }

    public Long getAckTimeoutInSeconds() {
        return ackTimeoutInSeconds;
    }

    public Integer getAttempts() {
        return attempts;
    }

    public Long getExpirationInSeconds() {
        return expirationInSeconds;
    }

    @Override
    public String toString() {
        return "CommandPolicy{" +
                "ackMode=" + ackMode +
                ", ackTimeoutInSeconds=" + ackTimeoutInSeconds +
                ", attempts=" + attempts +
                ", expirationInSeconds=" + expirationInSeconds +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommandPolicy)) return false;
        CommandPolicy that = (CommandPolicy) o;
        return getAckMode() == that.getAckMode()
                && Objects.equals(getAckTimeoutInSeconds(), that.getAckTimeoutInSeconds())
                && Objects.equals(getAttempts(), that.getAttempts())
                && Objects.equals(getExpirationInSeconds(), that.getExpirationInSeconds());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAckMode(), getAckTimeoutInSeconds(), getAttempts(), getExpirationInSeconds());
    }
}
