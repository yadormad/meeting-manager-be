package com.yador.meetingmanagerbe.auth.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthModel {
    private Integer userId;
    private String token;

    public AuthModel(Integer userId, String token) {
        this.userId = userId;
        this.token = token;
    }

    @JsonProperty("userId")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @JsonProperty("token")
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
