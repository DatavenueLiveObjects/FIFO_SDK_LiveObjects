package com.orange.lo.sdk.rest.devicemanagement;

import com.orange.lo.sdk.rest.model.Group;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GroupsTest {

    @Mock
    private RestTemplate restTemplate;
    @Mock
    private ResponseEntity<Group> groupResponseEntity;
    @Mock
    private ResponseEntity<List<Group>> groupsResponseEntity;
    private Group group;
    private Groups groups;

    @BeforeEach
    void setUp() {
        group = new Group();
        this.groups = new Groups(() -> restTemplate);
    }

    @Test
    void shouldCorrectlyCallRestTemplateWhenGettingGroupData() {
        String expectedEndpoint = "/v1/deviceMgt/groups/groupId";
        when(restTemplate.exchange(eq(expectedEndpoint), eq(HttpMethod.GET), any(), eq(Group.class)))
                .thenReturn(groupResponseEntity);
        when(groupResponseEntity.getBody()).thenReturn(group);

        Group group = groups.getGroup("groupId");

        assertNotNull(group);
        verify(restTemplate, times(1))
                .exchange(eq(expectedEndpoint), eq(HttpMethod.GET), any(), eq(Group.class));
    }

    @Test
    void shouldCorrectlyCallRestTemplateWhenGettingGroupsData() {
        String expectedEndpoint = "/v1/deviceMgt/groups";
        when(restTemplate.exchange(eq(expectedEndpoint),
                eq(HttpMethod.GET),
                any(),
                ArgumentMatchers.<ParameterizedTypeReference<List<Group>>>any())
        ).thenReturn(groupsResponseEntity);
        when(groupsResponseEntity.getBody()).thenReturn(new ArrayList<>());

        GetGroupsFilter getDevicesFilter = new GetGroupsFilter();
        List<Group> devices = groups.getGroups(getDevicesFilter);

        assertNotNull(devices);
        verify(restTemplate, times(1))
                .exchange(eq(expectedEndpoint),
                        eq(HttpMethod.GET), any(),
                        ArgumentMatchers.<ParameterizedTypeReference<List<Group>>>any()
                );
    }
}