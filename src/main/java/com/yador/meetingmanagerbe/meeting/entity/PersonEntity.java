package com.yador.meetingmanagerbe.meeting.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.yador.meetingmanagerbe.user.entity.jpa.UserEntity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "person", schema = "PUBLIC")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PersonEntity {
    private Integer id;
    private String fullname;
    private PositionEntity position;
    private UserEntity user;
    private Set<MeetingEntity> meetings;

    @Id
    @Column(name = "user_id")
    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "FULLNAME")
    @JsonProperty("fullName")
    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "position_id", nullable = false)
    @JsonProperty("position")
    public PositionEntity getPosition() {
        return position;
    }

    public void setPosition(PositionEntity position) {
        this.position = position;
    }

    @MapsId
    @OneToOne(/*mappedBy = "person",*/ cascade = CascadeType.ALL, optional = false)
    //@JoinColumn(name = "user_id")
    @JsonIgnore
    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "meeting_person_relation",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "meeting_id")
    )
    @JsonIgnore
    public Set<MeetingEntity> getMeetings() {
        return meetings;
    }

    public void setMeetings(Set<MeetingEntity> meetings) {
        this.meetings = meetings;
    }
}
