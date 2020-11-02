/**
 * Copyright (c) Orange. All Rights Reserved.
 * <p>
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.orange.lo.rest.model;

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
}
