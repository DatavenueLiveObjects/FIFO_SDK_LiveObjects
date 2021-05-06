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
import com.orange.lo.sdk.rest.model.Device;
import com.orange.lo.sdk.rest.model.Group;

public class Inventory extends ResourceClient {

    private static final String DEVICE_ENDPOINT = "/v1/deviceMgt/devices/{deviceId}";
    private static final String DEVICES_ENDPOINT = "/v1/deviceMgt/devices";
    
    public static final String MQTT_DEVICES_PREFIX = "urn:lo:nsid:mqtt:";
    public static final String XCONNECTOR_DEVICES_PREFIX = "urn:lo:nsid:x-connector:";

    public Inventory(RestTemplateFactory restTemplateFactory) {
        super(restTemplateFactory);
    }

    public Device getDevice(String deviceId) {
        String uri = UriComponentsBuilder.fromUriString(DEVICE_ENDPOINT).buildAndExpand(deviceId).toUriString();
        return get(uri, Device.class);
    }

    public List<Device> getDevices() {
        return getDevices(new GetDevicesFilter());
    }
    
    public List<Device> getDevices(GetDevicesFilter getDevicesFilter) {

        String uri = UriComponentsBuilder.fromUriString(DEVICES_ENDPOINT) //
                .queryParams(FilterUtils.toMap(getDevicesFilter)) //
                .build().toUriString(); //

        ParameterizedTypeReference<List<Device>> typeRef = new ParameterizedTypeReference<List<Device>>() {};
        return getMany(uri, typeRef);
    }
    
    public void deleteDevice(String deviceId) {
    	String uri = UriComponentsBuilder.fromUriString(DEVICES_ENDPOINT).pathSegment(deviceId) //
    			.build().toUriString();
    	delete(uri);    	
    }
    
    public Device createDevice(Device device) {
    	return create(DEVICES_ENDPOINT, device, Device.class);
    }
   
    public Device createDevice(String deviceId) {
    	return createDevice(deviceId, Groups.DEFAULT_GROUP_ID);
    }
    
    public Device createDevice(String deviceId, String groupId) {
    	Group group = new Group().withId(groupId);
    	Device device = new Device().withId(deviceId).withGroup(group);
    	return createDevice(device);
    } 
    
    public void updateDevice(Device device) {
    	String uri = UriComponentsBuilder.fromUriString(DEVICES_ENDPOINT).pathSegment(device.getId()) //
    			.build().toUriString();
    	edit(uri, device);
    }
}