/**
 * Copyright (c) Orange. All Rights Reserved.
 * <p>
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.orange.lo.sdk.rest.devicemanagement;

import com.orange.lo.sdk.rest.FilterUtils;
import com.orange.lo.sdk.rest.RestTemplateFactory;
import com.orange.lo.sdk.rest.ResourceClient;
import com.orange.lo.sdk.rest.model.Device;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

public class Inventory extends ResourceClient {

    private static final String DEVICE_ENDPOINT = "/v1/deviceMgt/devices/{deviceId}";
    private static final String DEVICES_ENDPOINT = "/v1/deviceMgt/devices";

    public Inventory(RestTemplateFactory restTemplateFactory) {
        super(restTemplateFactory);
    }

    public Device getDevice(String deviceId) {
        String uri = UriComponentsBuilder.fromUriString(DEVICE_ENDPOINT).buildAndExpand(deviceId).toUriString();
        return get(uri, Device.class);
    }

    public List<Device> getDevices(GetDevicesFilter getDevicesFilter) {

        String uri = UriComponentsBuilder.fromUriString(DEVICES_ENDPOINT) //
                .queryParams(FilterUtils.toMap(getDevicesFilter)) //
                .build().toUriString(); //

        return getMany(uri);
    }
}