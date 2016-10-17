package com.homeautomation.repository;

import com.homeautomation.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
