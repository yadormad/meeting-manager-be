package com.yador.meetingmanagerbe.user.service;

import com.yador.meetingmanagerbe.user.entity.User;

import java.util.Optional;

public interface UserCrudService {
    User save(User user);

    Optional<User> find(Integer id);

    Optional<User> findByUsername(String username);
}
