package com.homeautomation.handler;

import com.homeautomation.model.Control;
import com.homeautomation.model.User;
import com.homeautomation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler(Control.class)
public class ControlHandler {
    private final UserRepository users;

    @Autowired
    public ControlHandler(UserRepository users) {
        this.users = users;
    }

    @HandleBeforeCreate
    public void setLastModifiedOnCreateAndDenyNonAdmins(Control control) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = users.findByUsername(username);
        if(!control.getDevice().getRoom().getAdministrators().contains(user)) {
            throw new AccessDeniedException("Must be a room administrator to edit controls.");
        }
        control.setLastModifiedBy(user);
    }

    @HandleBeforeSave
    public void setLastModifiedOnSaveAndDenyNonAdmins(Control control) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = users.findByUsername(username);
        if(!control.getDevice().getRoom().getAdministrators().contains(user)) {
            throw new AccessDeniedException("Must be a room administrator to edit controls.");
        }
        control.setLastModifiedBy(user);
    }
}
