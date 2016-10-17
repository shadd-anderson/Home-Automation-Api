package com.homeautomation.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
public class Room extends GenericEntity {
    private String name;
    @Max(value = 1000, message = "Room size must be less than 1,000")
    private int size;
    @OneToMany(cascade = CascadeType.MERGE)
    private List<Device> devices;
    @ManyToMany(cascade = CascadeType.MERGE)
    private List<User> administrators;

    protected Room() {
        super();
        devices = new ArrayList<>();
        administrators = new ArrayList<>();
    }

    public Room(String name, int size) {
        this();
        this.name = name;
        this.size = size;
    }

    public void addDevice(Device device) {
        device.setRoom(this);
        devices.add(device);
    }

    public void addAdministrator(User user) {
        List<String> roles = Arrays.asList(user.getRoles());
        if(!roles.contains("ROLE_ADMIN")) {
            roles.add("ROLE_ADMIN");
        }
        user.setRoles(roles.toArray(new String[roles.size()]));
        administrators.add(user);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

    public List<User> getAdministrators() {
        return administrators;
    }

    public void setAdministrators(List<User> administrators) {
        this.administrators = administrators;
    }
}
