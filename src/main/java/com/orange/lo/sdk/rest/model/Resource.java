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
public class Resource {

    @JsonProperty("available")
    private Boolean available;

    public Resource withAvailable(Boolean available) {
        this.available = available;
        return this;
    }

    public Boolean getAvailable() {
        return available;
    }

    @Override
    public String toString() {
        return "Resource [available=" + available + "]";
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
		if (!(obj instanceof Resource))
			return false;
		Resource other = (Resource) obj;
		return Objects.equals(available, other.available);
	}
}