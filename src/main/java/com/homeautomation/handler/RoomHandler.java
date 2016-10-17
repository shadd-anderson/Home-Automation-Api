package com.homeautomation.handler;

import com.homeautomation.model.Room;
import com.homeautomation.model.User;
import com.homeautomation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler(Room.class)
public class RoomHandler {
    private final UserRepository users;

    @Autowired
    public RoomHandler(UserRepository users) {
        this.users=users;
    }

    @HandleBeforeCreate
    public void checkForAdminBeforeCreation(Room room) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = users.findByUsername(username);
        if(user.hasRole("ROLE_ADMIN")) {
            room.addAdministrator(user);
        } else {
            throw new AccessDeniedException("You do not have authorization to create a room.");
        }
    }
}
