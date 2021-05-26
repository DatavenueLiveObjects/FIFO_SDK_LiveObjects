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
public class CommandAddRequest {

    @JsonProperty("policy")
    private CommandPolicy policy = null;

    @JsonProperty("request")
    private CommandRequest request = null;

    public CommandAddRequest withPolicy(CommandPolicy policy) {
        this.policy = policy;
        return this;
    }

    public CommandAddRequest withRequest(CommandRequest request) {
        this.request = request;
        return this;
    }

    public CommandPolicy getPolicy() {
        return policy;
    }

    public CommandRequest getRequest() {
        return request;
    }

    @Override
    public String toString() {
        return "CommandAddRequest{" +
                "policy=" + policy +
                ", request=" + request +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommandAddRequest)) return false;
        CommandAddRequest that = (CommandAddRequest) o;
        return Objects.equals(getPolicy(), that.getPolicy()) && Objects.equals(getRequest(), that.getRequest());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPolicy(), getRequest());
    }
}
