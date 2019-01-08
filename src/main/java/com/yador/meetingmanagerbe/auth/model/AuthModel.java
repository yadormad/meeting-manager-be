package com.yador.meetingmanagerbe.auth.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yador.meetingmanagerbe.meeting.entity.PersonEntity;

public class AuthModel {
    private PersonEntity personEntity;
    private String token;

    public AuthModel(PersonEntity personEntity, String token) {
        this.personEntity = personEntity;
        this.token = token;
    }

    @JsonProperty("person")
    public PersonEntity getPersonEntity() {
        return personEntity;
    }

    public void setPersonEntity(PersonEntity personEntity) {
        this.personEntity = personEntity;
    }

    @JsonProperty("token")
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
