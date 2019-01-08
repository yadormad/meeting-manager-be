package com.yador.meetingmanagerbe.meeting.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "position", schema = "PUBLIC")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PositionEntity {
    private int id;
    private String name;
    private List<PersonEntity> personEntityList;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NAME")
    @JsonProperty("value")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "position")
    @JsonIgnore
    public List<PersonEntity> getPersonEntityList() {
        return personEntityList;
    }

    public void setPersonEntityList(List<PersonEntity> personEntityList) {
        this.personEntityList = personEntityList;
    }
}
