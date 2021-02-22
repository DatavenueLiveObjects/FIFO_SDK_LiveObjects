/**
 * Copyright (c) Orange. All Rights Reserved.
 * <p>
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.orange.lo.sdk.rest.devicemanagement;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.util.UriComponentsBuilder;

import com.orange.lo.sdk.rest.FilterUtils;
import com.orange.lo.sdk.rest.ResourceClient;
import com.orange.lo.sdk.rest.RestTemplateFactory;
import com.orange.lo.sdk.rest.model.Group;

public class Groups extends ResourceClient {

    private static final String GROUP_ENDPOINT = "/v1/deviceMgt/groups/{id}";
    private static final String GROUPS_ENDPOINT = "/v1/deviceMgt/groups";
    public static final String DEFAULT_GROUP_ID = "root";
    
    public Groups(RestTemplateFactory restTemplateFactory) {
        super(restTemplateFactory);
    }

    public Group getGroup(String groupId) {
        String uri = UriComponentsBuilder.fromUriString(GROUP_ENDPOINT).buildAndExpand(groupId).toUriString();
        return get(uri, Group.class);
    }

    public List<Group> getGroups() {
    	return getGroups(new GetGroupsFilter());
    }

    public List<Group> getGroups(GetGroupsFilter getGroupsFilter) {

        String uri = UriComponentsBuilder.fromUriString(GROUPS_ENDPOINT) //
                .queryParams(FilterUtils.toMap(getGroupsFilter)) //
                .build().toUriString(); //

        ParameterizedTypeReference<List<Group>> typeRef = new ParameterizedTypeReference<List<Group>>() {};
        return getMany(uri, typeRef);
    }
    
    public void deleteGroup(String groupId) {
    	String uri = UriComponentsBuilder.fromUriString(GROUPS_ENDPOINT).pathSegment(groupId) //
    			.build().toUriString();
    	delete(uri);    	
    }
    
    public Group createGroup(Group group) {
    	return create(GROUPS_ENDPOINT, group, Group.class);
    }
    
    public Group createGroup(String pathNode) {
    	Group group = new Group().withPathNode(pathNode);
    	return create(GROUPS_ENDPOINT, group, Group.class);
    }
}