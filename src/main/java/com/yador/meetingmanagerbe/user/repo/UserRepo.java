package com.yador.meetingmanagerbe.user.repo;

import com.yador.meetingmanagerbe.user.entity.jpa.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserEntity, Integer> {
}
