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
public class Definition {

    @JsonProperty("clientId")
    public String clientId;

    public Definition withClientId(String clientId) {
        this.clientId = clientId;
        return this;
    }

    public String getClientId() {
        return clientId;
    }

    @Override
    public String toString() {
        return "Definition [clientId=" + clientId + "]";
    }

	@Override
	public int hashCode() {
		return Objects.hash(clientId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Definition))
			return false;
		Definition other = (Definition) obj;
		return Objects.equals(clientId, other.clientId);
	}    
}