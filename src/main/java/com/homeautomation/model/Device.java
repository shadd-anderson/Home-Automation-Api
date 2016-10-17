package com.homeautomation.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Device extends GenericEntity {
    private String name;
    @ManyToOne(cascade = CascadeType.MERGE)
    private Room room;
    @OneToMany(mappedBy = "device", cascade = CascadeType.MERGE)
    private List<Control> controls;

    protected Device() {
        super();
        controls = new ArrayList<>();
    }

    public Device(String name) {
        this();
        this.name = name;
    }

    public void addControl(Control control) {
        control.setDevice(this);
        controls.add(control);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public List<Control> getControls() {
        return controls;
    }

    public void setControls(List<Control> controls) {
        this.controls = controls;
    }
}
