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
public class Group {

    @JsonProperty("id")
    private String id;

    @JsonProperty("pathNode")
    private String pathNode;

    @JsonProperty("path")
    private String path;

    @JsonProperty("parentId")
    private String parentId;

    @JsonProperty("description")
    private String description;

    @JsonProperty("created")
    private String created;

    @JsonProperty("updated")
    private String updated;

    public Group withId(String id) {
		this.id = id;
		return this;
	}

	public Group withPathNode(String pathNode) {
		this.pathNode = pathNode;
		return this;
	}

	public Group withPath(String path) {
		this.path = path;
		return this;
	}

	public Group withParentId(String parentId) {
		this.parentId = parentId;
		return this;
	}

	public Group withDescription(String description) {
		this.description = description;
		return this;
	}

	public Group withCreated(String created) {
		this.created = created;
		return this;
	}

	public Group withUpdated(String updated) {
		this.updated = updated;
		return this;
	}

	public String getId() {
        return id;
    }

    public String getPathNode() {
        return pathNode;
    }

    public String getPath() {
        return path;
    }

    public String getParentId() {
        return parentId;
    }

    public String getDescription() {
        return description;
    }

    public String getCreated() {
        return created;
    }

    public String getUpdated() {
        return updated;
    }

    @Override
    public String toString() {
        return "Group{" + "id='" + id + '\'' + ", pathNode='" + pathNode + '\'' + ", path='" + path + '\'' + ", parentId='" + parentId + '\'' + ", description='" + description + '\'' + ", created='" + created + '\'' + ", updated='" + updated + '\'' + '}';
    }

	@Override
	public int hashCode() {
		return Objects.hash(created, description, id, parentId, path, pathNode, updated);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Group))
			return false;
		Group other = (Group) obj;
		return Objects.equals(created, other.created) && Objects.equals(description, other.description)
				&& Objects.equals(id, other.id) && Objects.equals(parentId, other.parentId)
				&& Objects.equals(path, other.path) && Objects.equals(pathNode, other.pathNode)
				&& Objects.equals(updated, other.updated);
	}   
}