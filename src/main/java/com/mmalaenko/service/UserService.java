package com.mmalaenko.service;

import com.mmalaenko.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

   User getUserByLogin(String login);

    List<User> getAll();

    boolean isUserLoginExist(final String login);

    void save(String userName, String password);
}
