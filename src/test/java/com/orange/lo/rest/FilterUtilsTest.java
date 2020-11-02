package com.orange.lo.rest;

import com.orange.lo.rest.devicemanagement.GetDevicesFilter;
import com.orange.lo.rest.devicemanagement.GetGroupsFilter;
import org.junit.jupiter.api.Test;
import org.springframework.util.MultiValueMap;

import static org.junit.jupiter.api.Assertions.assertTrue;

class FilterUtilsTest {

    @Test
    void shouldCorrectlyMapFilterToParametersMapWhenGroupsFilterIsGiven() {
        GetGroupsFilter getGroupsFilter = new GetGroupsFilter().withLimit(20)
                .withOffset(40)
                .withParentId("parentGroupId");
        MultiValueMap<String, String> stringStringMultiValueMap = FilterUtils.toMap(getGroupsFilter);

        assertTrue(stringStringMultiValueMap.containsKey("limit"));
        assertTrue(stringStringMultiValueMap.get("limit").contains(String.valueOf(20)));
        assertTrue(stringStringMultiValueMap.containsKey("offset"));
        assertTrue(stringStringMultiValueMap.get("offset").contains(String.valueOf(40)));
        assertTrue(stringStringMultiValueMap.containsKey("parentId"));
        assertTrue(stringStringMultiValueMap.get("parentId").contains("parentGroupId"));
    }

    @Test
    void shouldCorrectlyMapFilterToParametersMapWhenDevicesFilterIsGiven() {
        GetDevicesFilter getGroupsFilter = new GetDevicesFilter().withLimit(20)
                .withOffset(40)
                .withGroupId("parentGroupId");
        MultiValueMap<String, String> stringStringMultiValueMap = FilterUtils.toMap(getGroupsFilter);

        assertTrue(stringStringMultiValueMap.containsKey("limit"));
        assertTrue(stringStringMultiValueMap.get("limit").contains(String.valueOf(20)));
        assertTrue(stringStringMultiValueMap.containsKey("offset"));
        assertTrue(stringStringMultiValueMap.get("offset").contains(String.valueOf(40)));
        assertTrue(stringStringMultiValueMap.containsKey("groupId"));
        assertTrue(stringStringMultiValueMap.get("groupId").contains("parentGroupId"));
    }
}