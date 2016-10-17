package com.homeautomation.repository;

import com.homeautomation.model.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

public interface RoomRepository extends PagingAndSortingRepository<Room, Long> {
    @RestResource(rel = "name-contains", path = "containsName")
    Page<Room> findByNameContaining(@Param("name") String name, Pageable page);

    @RestResource(rel = "less-than-size", path = "sizeLessThan")
    Page<Room> findBySizeLessThan(@Param("size") int size, Pageable page);
}
