package com.homeautomation.repository;

import com.homeautomation.model.Device;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

public class DeviceRepositoryTest {
    @Mock
    private DeviceRepository deviceRepository;

    private List<Device> devices = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        when(deviceRepository.save(any(Device.class))).then(answer ->
                devices.add(new Device("device"))
        );
        deviceRepository.save(new Device("device"));
    }

    @Test
    public void findByNameContainingReturnsDevice() throws Exception {
        when(deviceRepository.findByNameContaining(eq("device"), any(PageRequest.class))).thenReturn(new PageImpl<>(
                devices.stream().filter(device -> device.getName().contains("device")).collect(Collectors.toList()))
        );
        Page<Device> devicePage = deviceRepository.findByNameContaining("device", new PageRequest(1,10));

        assertEquals(devicePage.getTotalElements(), 1);
    }

}