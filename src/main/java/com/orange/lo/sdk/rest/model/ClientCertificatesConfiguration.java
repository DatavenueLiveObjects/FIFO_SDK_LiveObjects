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
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof ClientCertificatesConfiguration)) return false;
		ClientCertificatesConfiguration that = (ClientCertificatesConfiguration) o;
		return Objects.equals(getCaCertIds(), that.getCaCertIds()) && Objects.equals(isRequired(), that.isRequired());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getCaCertIds(), isRequired());
	}

	@Override
	public String toString() {
		return "ClientCertificatesConfiguration [caCertIds=" + caCertIds + ", required=" + required + "]";
	}
}
