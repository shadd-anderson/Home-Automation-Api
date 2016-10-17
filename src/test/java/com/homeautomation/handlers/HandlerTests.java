package com.homeautomation.handlers;

import com.homeautomation.model.Control;
import com.homeautomation.model.Device;
import com.homeautomation.model.Room;
import com.homeautomation.model.User;
import com.homeautomation.repository.ControlRepository;
import com.homeautomation.repository.DeviceRepository;
import com.homeautomation.repository.RoomRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.access.AccessDeniedException;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;

public class HandlerTests {
    private User user = new User("non-admin", "password", new String[]{"ROLE_USER"});

    @Mock
    private RoomRepository roomRepository;

    @Mock
    private DeviceRepository deviceRepository;

    @Mock
    private ControlRepository controlRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = AccessDeniedException.class)
    public void nonAdminCannotCreateDevice() throws Exception {
        doAnswer(answer -> {
            if(!user.hasRole("ROLE_ADMIN")) {
                throw new AccessDeniedException("Access denied");
            }
            return null;
        }).when(deviceRepository).save(any(Device.class));
        deviceRepository.save(new Device("device"));
    }

    @Test(expected = AccessDeniedException.class)
    public void nonAdminCannotCreateControl() throws Exception {
        doAnswer(answer -> {
            if(!user.hasRole("ROLE_ADMIN")) {
                throw new AccessDeniedException("Access denied");
            }
            return null;
        }).when(controlRepository).save(any(Control.class));
        controlRepository.save(new Control("control", 50));
    }

    @Test(expected = AccessDeniedException.class)
    public void nonAdminCannotCreateRoom() throws Exception {
        doAnswer(answer -> {
            if(!user.hasRole("ROLE_ADMIN")) {
                throw new AccessDeniedException("Access denied");
            }
            return null;
        }).when(roomRepository).save(any(Room.class));
        roomRepository.save(new Room("room1", 500));
    }
}