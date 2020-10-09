package com.mmalaenko.service.impl;

import com.mmalaenko.model.User;
import com.mmalaenko.repository.UserRepository;
import com.mmalaenko.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private final UserRepository userRepository;

    @Override
    public User getUserByLogin(String login) {
        return userRepository.getUserByLogin(login).orElseThrow(RuntimeException::new);
    }

    @Override
    public List<User> getAll() {
        return userRepository.getAll();
    }

    @Override
    public boolean isUserLoginExist(String login) {
        return userRepository.getUserByLogin(login).isPresent();
    }

    @Override
    public void save(String userName, String password) {
        userRepository.save(User.builder().login(userName).password(password).build());
    }
}
