/**
 * Copyright (c) Orange. All Rights Reserved.
 * <p>
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.orange.lo.sdk.rest.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InterfaceCapability {

    @JsonProperty("available")
    private Boolean available;

    @JsonProperty("version")
    private Integer version;

    public InterfaceCapability withAvailable(Boolean available) {
        this.available = available;
        return this;
    }

    public InterfaceCapability withVersion(Integer version) {
        this.version = version;
        return this;
    }

    public Boolean getAvailable() {
        return available;
    }

    public Integer getVersion() {
        return version;
    }

    @Override
    public String toString() {
        return "Command [available=" + available + ", version=" + version + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InterfaceCapability)) return false;
        InterfaceCapability that = (InterfaceCapability) o;
        return Objects.equals(getAvailable(), that.getAvailable()) && Objects.equals(getVersion(), that.getVersion());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAvailable(), getVersion());
    }
}
