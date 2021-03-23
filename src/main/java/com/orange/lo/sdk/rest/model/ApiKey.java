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
	public int hashCode() {
		return Objects.hash(active, clientCert, creationTs, debugModeEndTs, description, expired, from, id, label,
				lastActivity, masterKey, nonce, parentId, rateLimit, roles, scope, sessionKey, sessionTTL, tenantId, to,
				userId, value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof ApiKey)) {
			return false;
		}
		ApiKey other = (ApiKey) obj;
		return Objects.equals(active, other.active) && Objects.equals(clientCert, other.clientCert)
				&& Objects.equals(creationTs, other.creationTs) && Objects.equals(debugModeEndTs, other.debugModeEndTs)
				&& Objects.equals(description, other.description) && Objects.equals(expired, other.expired)
				&& Objects.equals(from, other.from) && Objects.equals(id, other.id)
				&& Objects.equals(label, other.label) && Objects.equals(lastActivity, other.lastActivity)
				&& Objects.equals(masterKey, other.masterKey) && Objects.equals(nonce, other.nonce)
				&& Objects.equals(parentId, other.parentId) && Objects.equals(rateLimit, other.rateLimit)
				&& Objects.equals(roles, other.roles) && Objects.equals(scope, other.scope)
				&& Objects.equals(sessionKey, other.sessionKey) && Objects.equals(sessionTTL, other.sessionTTL)
				&& Objects.equals(tenantId, other.tenantId) && Objects.equals(to, other.to)
				&& Objects.equals(userId, other.userId) && Objects.equals(value, other.value);
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