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
public class CommandRequest {

    @JsonProperty("connector")
    private String connector = null;

    @JsonProperty("value")
    private CommandRequestValue value = null;

    public CommandRequest withConnector(String connector) {
        this.connector = connector;
        return this;
    }

    public CommandRequest withValue(CommandRequestValue value) {
        this.value = value;
        return this;
    }

    public String getConnector() {
        return connector;
    }

    public void setConnector(String connector) {
        this.connector = connector;
    }

    public CommandRequestValue getValue() {
        return value;
    }

    public void setValue(CommandRequestValue value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "CommandRequest{" +
                "connector='" + connector + '\'' +
                ", value=" + value +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommandRequest that = (CommandRequest) o;
        return Objects.equals(connector, that.connector) && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(connector, value);
    }
}
