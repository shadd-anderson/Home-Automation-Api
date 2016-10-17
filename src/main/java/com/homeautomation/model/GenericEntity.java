package com.homeautomation.model;

import javax.persistence.*;

@MappedSuperclass
public abstract class GenericEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private final Long id;
    @Version
    private Long version;

    protected GenericEntity() {
        id = null;
    }
}
