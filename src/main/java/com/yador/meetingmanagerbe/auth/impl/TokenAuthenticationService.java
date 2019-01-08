package com.yador.meetingmanagerbe.auth.impl;

import com.yador.meetingmanagerbe.auth.UserAuthenticationService;
import com.yador.meetingmanagerbe.token.TokenService;
import com.yador.meetingmanagerbe.user.entity.User;
import com.yador.meetingmanagerbe.user.service.UserCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
public class TokenAuthenticationService implements UserAuthenticationService {
    private final TokenService tokens;
    private final UserCrudService users;

    @Autowired
    public TokenAuthenticationService(TokenService tokens, UserCrudService users) {
        this.tokens = tokens;
        this.users = users;
    }

    @Override
    public Optional<String> login(final String username, final String password) {
        return users
                .findByUsername(username)
                .filter(user -> Objects.equals(password, user.getPassword()))
                .map(user -> tokens.expiring(Map.of("username", username)));
    }

    @Override
    public Optional<User> findByToken(final String token) {
        return Optional
                .of(tokens.verify(token))
                .map(map -> map.get("username"))
                .flatMap(users::findByUsername);
    }

    @Override
    public void logout(final User user) {
        // Nothing to doy
    }
}
