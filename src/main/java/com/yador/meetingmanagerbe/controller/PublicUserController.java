package com.yador.meetingmanagerbe.controller;

import com.yador.meetingmanagerbe.auth.UserAuthenticationService;
import com.yador.meetingmanagerbe.auth.model.AuthModel;
import com.yador.meetingmanagerbe.meeting.entity.PersonEntity;
import com.yador.meetingmanagerbe.meeting.repo.PersonRepo;
import com.yador.meetingmanagerbe.user.entity.User;
import com.yador.meetingmanagerbe.user.service.UserCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/public")
public class PublicUserController {
    private final UserAuthenticationService authentication;
    private final UserCrudService users;
    private final PersonRepo personRepo;

    @Autowired
    public PublicUserController(UserAuthenticationService authentication, UserCrudService users, PersonRepo personRepo) {
        this.authentication = authentication;
        this.users = users;
        this.personRepo = personRepo;
    }

    @PostMapping("/register")
    public AuthModel register(
            @RequestParam("username") String username,
            @RequestParam("password") String password) {
        users.save(
                new User()
                        .setUsernameAndReturn(username)
                        .setPasswordAndReturn(password)
                );
        return login(username, password);
    }

    @PostMapping("/login")
    public AuthModel login(
            @RequestParam("username") String username,
            @RequestParam("password") String password) {
        Integer userId = users.findByUsername(username).get().getId();
        Optional<PersonEntity> personEntityOptional = personRepo.findById(userId);
        String token = authentication
                .login(username, password)
                .orElseThrow(() -> new RuntimeException("invalid login and/or password"));

        return personEntityOptional.map(personEntity -> new AuthModel(personEntity, token)).orElseGet(() -> new AuthModel(null, token));
    }
}
