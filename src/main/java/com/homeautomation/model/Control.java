package com.homeautomation.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Control extends GenericEntity {
    private String name;
    @ManyToOne(cascade = CascadeType.MERGE)
    private Device device;
    private int value;
    @ManyToOne(cascade = CascadeType.MERGE)
    private User lastModifiedBy;

    protected Control() {
        super();
    }

    public Control(String name, int value) {
        this();
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public User getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(User lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }
}
