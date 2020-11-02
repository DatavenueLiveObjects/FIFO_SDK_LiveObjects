/**
 * Copyright (c) Orange. All Rights Reserved.
 * <p>
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.orange.lo.rest.devicemanagement;

import com.orange.lo.rest.FilterUtils;
import com.orange.lo.rest.RestTemplateFactory;
import com.orange.lo.rest.ResourceClient;
import com.orange.lo.rest.model.Group;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

public class Groups extends ResourceClient {

    private static final String GROUP_ENDPOINT = "/v1/deviceMgt/groups/{id}";
    private static final String GROUPS_ENDPOINT = "/v1/deviceMgt/groups";

    public Groups(RestTemplateFactory restTemplateFactory) {
        super(restTemplateFactory);
    }

    public Group getGroup(String groupId) {
        String uri = UriComponentsBuilder.fromUriString(GROUP_ENDPOINT).buildAndExpand(groupId).toUriString();
        return get(uri, Group.class);
    }

    public List<Group> getGroups(GetGroupsFilter getGroupsFilter) {

        String uri = UriComponentsBuilder.fromUriString(GROUPS_ENDPOINT) //
                .queryParams(FilterUtils.toMap(getGroupsFilter)) //
                .build().toUriString(); //

        return getMany(uri);
    }
}