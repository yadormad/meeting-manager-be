package com.yador.meetingmanagerbe.user.entity.jpa;

import com.yador.meetingmanagerbe.meeting.entity.PersonEntity;

import javax.persistence.*;

//todo merge with User class

@Entity
@Table(name = "user", schema = "PUBLIC")
public class UserEntity {
    private Integer id;
    private String username, password;
    private PersonEntity person;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPerson(PersonEntity person) {
        this.person = person;
    }

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public UserEntity setIdAndReturn(Integer id) {
        this.id = id;
        return this;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public UserEntity setUsernameAndReturn(String username) {
        this.username = username;
        return this;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public UserEntity setPasswordAndReturn(String password) {
        this.password = password;
        return this;
    }

    @OneToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    //@PrimaryKeyJoinColumn
    public PersonEntity getPerson() {
        return person;
    }

    public UserEntity setPersonAndReturn(PersonEntity person) {
        this.person = person;
        return this;
    }
}
