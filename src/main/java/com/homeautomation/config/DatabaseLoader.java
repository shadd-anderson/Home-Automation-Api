package com.homeautomation.config;

import com.homeautomation.model.Control;
import com.homeautomation.model.Device;
import com.homeautomation.model.Room;
import com.homeautomation.model.User;
import com.homeautomation.repository.ControlRepository;
import com.homeautomation.repository.DeviceRepository;
import com.homeautomation.repository.RoomRepository;
import com.homeautomation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.stream.IntStream;

@Component
public class DatabaseLoader implements ApplicationRunner{
    private UserRepository users;
    private DeviceRepository devices;
    private RoomRepository rooms;
    private ControlRepository controls;

    @Autowired
    public DatabaseLoader(UserRepository users, DeviceRepository devices, RoomRepository rooms, ControlRepository controls) {
        this.users = users;
        this.devices = devices;
        this.rooms = rooms;
        this.controls = controls;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        User admin = new User("admin", "password", new String[] {"ROLE_ADMIN", "ROLE_USER"});
        User nonAdmin = new User("non-admin", "password", new String[] {"ROLE_USER"});
        users.save(admin);
        users.save(nonAdmin);
        String[] roomNames = {
                "kitchen",
                "bedroom",
                "bathroom"
        };
        String[] deviceNames = {
                "thermostat",
                "light switch",
                "TV",
                "computer monitor"
        };
        IntStream.range(1,15)
                .forEach(i -> {
                    Room room = new Room(String.format(roomNames[i % 3] + " %d", i/3), i * 60);
                    room.addAdministrator(admin);
                    Control control = new Control(String.format("Control %d", i), i * 5);
                    controls.save(control);
                    Device device = new Device(deviceNames[i % 4]);
                    device.addControl(control);
                    devices.save(device);
                    controls.save(control);
                    room.addDevice(device);
                    rooms.save(room);
                });
    }
}
