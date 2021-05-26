package com.orange.lo.sdk.rest.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiKey {

	@JsonProperty("active")
	private Boolean active = null;

	@JsonProperty("clientCert")
	private ClientCertificatesConfiguration clientCert = null;

	@JsonProperty("creationTs")
	private Long creationTs = null;

	@JsonProperty("debugModeEndTs")
	private Long debugModeEndTs = null;

	@JsonProperty("description")
	private String description = null;

	@JsonProperty("expired")
	private Boolean expired = null;

	@JsonProperty("from")
	private Long from = null;

	@JsonProperty("id")
	private String id = null;

	@JsonProperty("label")
	private String label = null;

	@JsonProperty("lastActivity")
	private Long lastActivity = null;

	@JsonProperty("masterKey")
	private Boolean masterKey = null;

	@JsonProperty("nonce")
	private String nonce = null;

	@JsonProperty("parentId")
	private String parentId = null;

	@JsonProperty("rateLimit")
	private RateLimit rateLimit = null;

	@JsonProperty("roles")
	private List<Role> roles = new ArrayList<>();

	@JsonProperty("scope")
	private ScopeApplication scope = null;

	@JsonProperty("sessionKey")
	private Boolean sessionKey = null;

	@JsonProperty("sessionTTL")
	private Long sessionTTL = null;

	@JsonProperty("tenantId")
	private String tenantId = null;

	@JsonProperty("to")
	private Long to = null;

	@JsonProperty("userId")
	private String userId = null;

	@JsonProperty("value")
	private String value = null;

	public ApiKey withActive(Boolean active) {
		this.active = active;
		return this;
	}

	public ApiKey withClientCert(ClientCertificatesConfiguration clientCert) {
		this.clientCert = clientCert;
		return this;
	}

	public ApiKey withCreationTs(Long creationTs) {
		this.creationTs = creationTs;
		return this;
	}

	public ApiKey withDebugModeEndTs(Long debugModeEndTs) {
		this.debugModeEndTs = debugModeEndTs;
		return this;
	}

	public Boolean isActive() {
		return active;
	}

	public ClientCertificatesConfiguration getClientCert() {
		return clientCert;
	}

	public Long getCreationTs() {
		return creationTs;
	}

	public Long getDebugModeEndTs() {
		return debugModeEndTs;
	}

	public ApiKey withDescription(String description) {
		this.description = description;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public ApiKey withExpired(Boolean expired) {
		this.expired = expired;
		return this;
	}

	public Boolean isExpired() {
		return expired;
	}

	public ApiKey withFrom(Long from) {
		this.from = from;
		return this;
	}

	public Long getFrom() {
		return from;
	}

	public ApiKey withId(String id) {
		this.id = id;
		return this;
	}

	public String getId() {
		return id;
	}

	public ApiKey withLabel(String label) {
		this.label = label;
		return this;
	}

	public String getLabel() {
		return label;
	}

	public ApiKey withLastActivity(Long lastActivity) {
		this.lastActivity = lastActivity;
		return this;
	}

	public Long getLastActivity() {
		return lastActivity;
	}

	public ApiKey withMasterKey(Boolean masterKey) {
		this.masterKey = masterKey;
		return this;
	}

	public Boolean isMasterKey() {
		return masterKey;
	}

	public ApiKey withNonce(String nonce) {
		this.nonce = nonce;
		return this;
	}

	public String getNonce() {
		return nonce;
	}

	public ApiKey withParentId(String parentId) {
		this.parentId = parentId;
		return this;
	}

	public String getParentId() {
		return parentId;
	}

	public ApiKey withRateLimit(RateLimit rateLimit) {
		this.rateLimit = rateLimit;
		return this;
	}

	public RateLimit getRateLimit() {
		return rateLimit;
	}

	public ApiKey withRoles(List<Role> roles) {
		this.roles = roles;
		return this;
	}

	public ApiKey addRolesItem(Role rolesItem) {
		this.roles.add(rolesItem);
		return this;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public ApiKey withScope(ScopeApplication scope) {
		this.scope = scope;
		return this;
	}

	public ScopeApplication getScope() {
		return scope;
	}

	public ApiKey withSessionKey(Boolean sessionKey) {
		this.sessionKey = sessionKey;
		return this;
	}

	public Boolean isSessionKey() {
		return sessionKey;
	}

	public ApiKey withSessionTTL(Long sessionTTL) {
		this.sessionTTL = sessionTTL;
		return this;
	}

	public Long getSessionTTL() {
		return sessionTTL;
	}

	public ApiKey withTenantId(String tenantId) {
		this.tenantId = tenantId;
		return this;
	}

	public String getTenantId() {
		return tenantId;
	}

	public ApiKey withTo(Long to) {
		this.to = to;
		return this;
	}

	public Long getTo() {
		return to;
	}

	public ApiKey withUserId(String userId) {
		this.userId = userId;
		return this;
	}

	public String getUserId() {
		return userId;
	}

	public ApiKey withValue(String value) {
		this.value = value;
		return this;
	}

	public String getValue() {
		return value;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ApiKey)) return false;
        ApiKey apiKey = (ApiKey) o;
        return Objects.equals(isActive(), apiKey.isActive())
                && Objects.equals(getClientCert(), apiKey.getClientCert())
                && Objects.equals(getCreationTs(), apiKey.getCreationTs())
                && Objects.equals(getDebugModeEndTs(), apiKey.getDebugModeEndTs())
                && Objects.equals(getDescription(), apiKey.getDescription())
                && Objects.equals(isExpired(), apiKey.isExpired())
                && Objects.equals(getFrom(), apiKey.getFrom())
                && Objects.equals(getId(), apiKey.getId())
                && Objects.equals(getLabel(), apiKey.getLabel())
                && Objects.equals(getLastActivity(), apiKey.getLastActivity())
                && Objects.equals(isMasterKey(), apiKey.isMasterKey())
                && Objects.equals(getNonce(), apiKey.getNonce())
                && Objects.equals(getParentId(), apiKey.getParentId())
                && Objects.equals(getRateLimit(), apiKey.getRateLimit())
                && Objects.equals(getRoles(), apiKey.getRoles())
                && Objects.equals(getScope(), apiKey.getScope())
                && Objects.equals(isSessionKey(), apiKey.isSessionKey())
                && Objects.equals(getSessionTTL(), apiKey.getSessionTTL())
                && Objects.equals(getTenantId(), apiKey.getTenantId())
                && Objects.equals(getTo(), apiKey.getTo())
                && Objects.equals(getUserId(), apiKey.getUserId())
                && Objects.equals(getValue(), apiKey.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(isActive(), getClientCert(), getCreationTs(), getDebugModeEndTs(), getDescription(),
                isExpired(), getFrom(), getId(), getLabel(), getLastActivity(), isMasterKey(), getNonce(),
                getParentId(), getRateLimit(), getRoles(), getScope(), isSessionKey(), getSessionTTL(), getTenantId(),
                getTo(), getUserId(), getValue());
    }

	@Override
	public String toString() {
		return "ApiKey [active=" + active + ", clientCert=" + clientCert + ", creationTs=" + creationTs
				+ ", debugModeEndTs=" + debugModeEndTs + ", description=" + description + ", expired=" + expired
				+ ", from=" + from + ", id=" + id + ", label=" + label + ", lastActivity=" + lastActivity
				+ ", masterKey=" + masterKey + ", nonce=" + nonce + ", parentId=" + parentId + ", rateLimit="
				+ rateLimit + ", roles=" + roles + ", scope=" + scope + ", sessionKey=" + sessionKey + ", sessionTTL="
				+ sessionTTL + ", tenantId=" + tenantId + ", to=" + to + ", userId=" + userId + ", value=" + value
				+ "]";
	}
}
