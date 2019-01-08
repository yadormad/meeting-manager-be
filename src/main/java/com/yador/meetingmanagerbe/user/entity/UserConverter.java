package com.yador.meetingmanagerbe.user.entity;

import com.yador.meetingmanagerbe.user.entity.jpa.UserEntity;
import com.yador.meetingmanagerbe.user.repo.UserRepo;

public class UserConverter {

    private UserRepo userRepo;

    public UserConverter(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public UserEntity toJpa(User user) {
        UserEntity userEntity = user.getId() != null ? userRepo.getOne(user.getId()) : new UserEntity();
        return userEntity
                .setUsernameAndReturn(user.getUsername())
                .setPasswordAndReturn(user.getPassword());
    }

    public User toUserDetails(UserEntity userEntity) {
        return new User()
                .setIdAndReturn(userEntity.getId())
                .setUsernameAndReturn(userEntity.getUsername())
                .setPasswordAndReturn(userEntity.getPassword());
    }
}
