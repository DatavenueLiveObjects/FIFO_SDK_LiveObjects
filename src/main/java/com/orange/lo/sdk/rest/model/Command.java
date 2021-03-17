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
public class Command {

    @JsonProperty("available")
    private Boolean available;

    @JsonProperty("version")
    private Integer version;

    public Command withAvailable(Boolean available) {
        this.available = available;
        return this;
    }

    public Command withVersion(Integer version) {
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
	public int hashCode() {
		return Objects.hash(available, version);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Command))
			return false;
		Command other = (Command) obj;
		return Objects.equals(available, other.available) && Objects.equals(version, other.version);
	}
}