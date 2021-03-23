package com.orange.lo.sdk.rest.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClientCertificatesConfiguration {

	@JsonProperty("caCertIds")
	private List<String> caCertIds = null;

	@JsonProperty("required")
	private Boolean required = null;

	public ClientCertificatesConfiguration withCaCertIds(List<String> caCertIds) {
		this.caCertIds = caCertIds;
		return this;
	}

	public ClientCertificatesConfiguration addCaCertIdsItem(String caCertIdsItem) {
		if (this.caCertIds == null) {
			this.caCertIds = new ArrayList<>();
		}
		this.caCertIds.add(caCertIdsItem);
		return this;
	}

	public List<String> getCaCertIds() {
		return caCertIds;
	}

	public ClientCertificatesConfiguration withRequired(Boolean required) {
		this.required = required;
		return this;
	}

	public Boolean isRequired() {
		return required;
	}

	@Override
	public int hashCode() {
		return Objects.hash(caCertIds, required);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof ClientCertificatesConfiguration)) {
			return false;
		}
		ClientCertificatesConfiguration other = (ClientCertificatesConfiguration) obj;
		return Objects.equals(caCertIds, other.caCertIds) && Objects.equals(required, other.required);
	}

	@Override
	public String toString() {
		return "ClientCertificatesConfiguration [caCertIds=" + caCertIds + ", required=" + required + "]";
	}
}