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
public class Configuration {

    @JsonProperty("available")
    public Boolean available;

    public Configuration withAvailable(Boolean available) {
        this.available = available;
        return this;
    }

    public Boolean getAvailable() {
        return available;
    }

    @Override
    public String toString() {
        return "Configuration [available=" + available + "]";
    }

	@Override
	public int hashCode() {
		return Objects.hash(available);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Configuration))
			return false;
		Configuration other = (Configuration) obj;
		return Objects.equals(available, other.available);
	}
}