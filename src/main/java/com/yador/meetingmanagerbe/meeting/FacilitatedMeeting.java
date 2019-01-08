package com.yador.meetingmanagerbe.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yador.meetingmanagerbe.meeting.entity.MeetingEntity;

import java.util.Date;

public class FacilitatedMeeting {

    private Integer id;
    private String name;
    private Date startDate;
    private String meetingPriority;
    private String description;

    public FacilitatedMeeting(MeetingEntity meetingEntity) {
        id = meetingEntity.getId();
        name = meetingEntity.getName();
        startDate = meetingEntity.getStartDate();
        meetingPriority = meetingEntity.getMeetingPriority().getName();
        description = meetingEntity.getDescription();
    }

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("startDate")
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @JsonProperty("meetingPriority")
    public String getMeetingPriority() {
        return meetingPriority;
    }

    public void setMeetingPriority(String meetingPriority) {
        this.meetingPriority = meetingPriority;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
