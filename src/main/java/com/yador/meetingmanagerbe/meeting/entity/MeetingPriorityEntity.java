package com.yador.meetingmanagerbe.meeting.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "meeting_priority", schema = "PUBLIC")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MeetingPriorityEntity {
    private Integer id;
    private String name;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    @JsonProperty("value")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
