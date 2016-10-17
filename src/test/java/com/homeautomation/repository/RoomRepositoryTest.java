package com.homeautomation.repository;

import com.homeautomation.Main;
import com.homeautomation.model.Room;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.validation.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Main.class)
@WebAppConfiguration
public class RoomRepositoryTest {
    @Mock
    private RoomRepository roomRepository;

    private List<Room> rooms = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        when(roomRepository.save(any(Room.class))).then(answer ->
                rooms.add(new Room("room1", 500))
        );
        roomRepository.save(new Room("room1", 500));
    }

    @Test
    public void findByNameReturnsRoom() throws Exception {
        when(roomRepository.findByNameContaining(eq("room"), any(PageRequest.class))).thenReturn(new PageImpl<>(
                rooms.stream().filter(room -> room.getName().contains("room")).collect(Collectors.toList()))
        );
        Page<Room> roomPage = roomRepository.findByNameContaining("room", new PageRequest(1,10));

        assertEquals(roomPage.getTotalElements(), 1);
    }

    @Test
    public void findBySizeLessThanReturnsRoom() throws Exception {
        when(roomRepository.findBySizeLessThan(eq(600), any(PageRequest.class))).thenReturn(new PageImpl<Room>(
                rooms.stream().filter(room -> room.getSize() < 600).collect(Collectors.toList())
        ));
        Page<Room> roomPage = roomRepository.findBySizeLessThan(600, new PageRequest(1, 10));

        assertEquals(roomPage.getTotalElements(), 1);
    }
}