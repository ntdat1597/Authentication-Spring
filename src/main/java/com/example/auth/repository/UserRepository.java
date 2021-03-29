package com.example.auth.repository;

import com.example.auth.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User finByUsername (String username);
}
