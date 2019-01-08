package com.yador.meetingmanagerbe.meeting.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "meeting", schema = "PUBLIC")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MeetingEntity {
    private Integer id;
    private String name, description;
    private Date startDate, endDate;
    private MeetingPriorityEntity meetingPriority;
    private Set<PersonEntity> participants;

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "priority_id", nullable = false)
    @JsonProperty("priority")
    public MeetingPriorityEntity getMeetingPriority() {
        return meetingPriority;
    }

    public void setMeetingPriority(MeetingPriorityEntity meetingPriority) {
        this.meetingPriority = meetingPriority;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "meeting_person_relation",
            joinColumns = @JoinColumn(name = "meeting_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id")
    )
    @JsonProperty("participants")
    public Set<PersonEntity> getParticipants() {
        return participants;
    }

    public void setParticipants(Set<PersonEntity> participants) {
        this.participants = participants;
    }

    @Basic
    @Column(name = "name")
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "description")
    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name="startdate", nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty("startDate")
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Column(name="enddate", nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty("endDate")
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
