package com.mmalaenko.repository;

import com.mmalaenko.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional<User> getUserByLogin(String login);
    List<User> getAll();
    void save(User user);

}
