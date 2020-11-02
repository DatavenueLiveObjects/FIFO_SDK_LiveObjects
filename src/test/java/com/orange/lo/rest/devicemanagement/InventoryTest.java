package com.orange.lo.rest.devicemanagement;

import com.orange.lo.rest.model.Device;
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
class InventoryTest {

    @Mock
    private RestTemplate restTemplate;
    @Mock
    private ResponseEntity<Device> deviceResponseEntity;
    @Mock
    private ResponseEntity<List<Device>> devicesResponseEntity;
    private Device device;
    private Inventory inventory;

    @BeforeEach
    void setUp() {
        device = new Device();
        this.inventory = new Inventory(() -> restTemplate);
    }

    @Test
    void shouldCorrectlyCallRestTemplateWhenGettingDeviceData() {
        String expectedEndpoint = "/v1/deviceMgt/devices/deviceId";
        when(restTemplate.exchange(eq(expectedEndpoint), eq(HttpMethod.GET), any(), eq(Device.class)))
                .thenReturn(deviceResponseEntity);
        when(deviceResponseEntity.getBody()).thenReturn(device);

        Device device = inventory.getDevice("deviceId");

        assertNotNull(device);
        verify(restTemplate, times(1))
                .exchange(eq(expectedEndpoint), eq(HttpMethod.GET), any(), eq(Device.class));
    }

    @Test
    void shouldCorrectlyCallRestTemplateWhenGettingDevicesData() {
        String expectedEndpoint = "/v1/deviceMgt/devices";
        when(restTemplate.exchange(eq(expectedEndpoint),
                eq(HttpMethod.GET),
                any(),
                ArgumentMatchers.<ParameterizedTypeReference<List<Device>>>any())
        ).thenReturn(devicesResponseEntity);
        when(devicesResponseEntity.getBody()).thenReturn(new ArrayList<>());

        GetDevicesFilter getDevicesFilter = new GetDevicesFilter();
        List<Device> devices = inventory.getDevices(getDevicesFilter);

        assertNotNull(devices);
        verify(restTemplate, times(1))
                .exchange(eq(expectedEndpoint),
                        eq(HttpMethod.GET), any(),
                        ArgumentMatchers.<ParameterizedTypeReference<List<Device>>>any()
                );
    }
}