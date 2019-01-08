package com.yador.meetingmanagerbe.controller;

import com.yador.meetingmanagerbe.auth.UserAuthenticationService;
import com.yador.meetingmanagerbe.meeting.entity.PersonEntity;
import com.yador.meetingmanagerbe.meeting.entity.PositionEntity;
import com.yador.meetingmanagerbe.meeting.repo.PersonRepo;
import com.yador.meetingmanagerbe.meeting.repo.PositionRepo;
import com.yador.meetingmanagerbe.user.entity.User;
import com.yador.meetingmanagerbe.user.entity.jpa.UserEntity;
import com.yador.meetingmanagerbe.user.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class SecuredUsersController {
    final private UserAuthenticationService authentication;
    final private UserRepo userRepo;
    final private PositionRepo positionRepo;
    final private PersonRepo personRepo;

    @Autowired
    public SecuredUsersController(UserAuthenticationService authentication, UserRepo userRepo, PositionRepo positionRepo, PersonRepo personRepo) {
        this.authentication = authentication;
        this.userRepo = userRepo;
        this.positionRepo = positionRepo;
        this.personRepo = personRepo;
    }

    @GetMapping("/current")
    public User getCurrent(@AuthenticationPrincipal final User user) {
        return user;
    }

    @GetMapping("/logout")
    public boolean logout(@AuthenticationPrincipal final User user) {
        authentication.logout(user);
        return true;
    }

    @GetMapping("/positions")
    public List<PositionEntity> getAllPositions() {
        return positionRepo.findAll();
    }

    @PostMapping("/person/create")
    public Integer createPerson(@AuthenticationPrincipal final User user, @RequestBody PersonEntity personEntity) {
        personEntity.setId(user.getId());
        Integer id = user.getId();
        UserEntity userEntity = userRepo.findById(id).get();
        personEntity.setUser(userEntity);
        userEntity.setPerson(personEntity);
        personRepo.saveAndFlush(personEntity);
        return personEntity.getId();
    }
}
