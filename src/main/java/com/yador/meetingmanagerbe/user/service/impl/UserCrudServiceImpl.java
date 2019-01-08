package com.yador.meetingmanagerbe.user.service.impl;

import com.yador.meetingmanagerbe.user.entity.User;
import com.yador.meetingmanagerbe.user.entity.UserConverter;
import com.yador.meetingmanagerbe.user.entity.jpa.UserEntity;
import com.yador.meetingmanagerbe.user.repo.UserRepo;
import com.yador.meetingmanagerbe.user.service.UserCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

@Service
public class UserCrudServiceImpl implements UserCrudService {

    private UserConverter userConverter;
    private final UserRepo userRepo;

    @Autowired
    public UserCrudServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
        userConverter = new UserConverter(userRepo);
    }

    @Override
    public User save(User user) {
        return userConverter.toUserDetails(
                userRepo.saveAndFlush(
                        userConverter.toJpa(user)
                )
        );
    }

    @Override
    public Optional<User> find(Integer id) {
        return Optional.ofNullable(userConverter.toUserDetails(userRepo.getOne(id)));
    }

    @Override
    public Optional<User> findByUsername(String username) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsernameAndReturn(username);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("username", exact());

        Example<UserEntity> example = Example.of(userEntity, matcher);
        return Optional.ofNullable(userConverter.toUserDetails(userRepo.findOne(example).get()));
    }
}
