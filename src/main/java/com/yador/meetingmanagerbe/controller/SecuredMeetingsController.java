package com.yador.meetingmanagerbe.controller;

import com.yador.meetingmanagerbe.meeting.FacilitatedMeeting;
import com.yador.meetingmanagerbe.meeting.entity.MeetingEntity;
import com.yador.meetingmanagerbe.meeting.entity.MeetingPriorityEntity;
import com.yador.meetingmanagerbe.meeting.repo.MeetingPriorityRepo;
import com.yador.meetingmanagerbe.meeting.repo.MeetingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/meetings")
public class SecuredMeetingsController {
    final private MeetingRepo meetings;
    final private MeetingPriorityRepo meetingPriorityRepo;

    @Autowired
    public SecuredMeetingsController(MeetingRepo meetings, MeetingPriorityRepo meetingPriorityRepo) {
        this.meetings = meetings;
        this.meetingPriorityRepo = meetingPriorityRepo;
    }

    @GetMapping("/all")
    public List<FacilitatedMeeting> getAllMeetings() {
        List<FacilitatedMeeting> facilitatedMeetings = new ArrayList<>();
        meetings.findAll().forEach(m -> facilitatedMeetings.add(new FacilitatedMeeting(m)));
        return facilitatedMeetings;
    }

    @GetMapping("/all/detailed")
    public List<MeetingEntity> getAllDetailedMeetings() {
        return meetings.findAll();
    }

    @PostMapping("/")
    public void createMeeting(@RequestBody MeetingEntity meeting) {
        meetings.save(meeting);
    }

    @PutMapping("/{id}")
    public void updateMeeting(@RequestBody MeetingEntity meeting, @PathVariable Integer id) {
        meeting.setId(id);
        meetings.save(meeting);
    }

    @DeleteMapping("/{id}")
    public void deleteMeeting(@PathVariable(name = "id") Integer id) {
        meetings.deleteById(id);
    }

    @GetMapping("/detailed/{id}")
    public MeetingEntity getMeetingById(@PathVariable(name = "id") Integer id) {
        return meetings.getOne(id);
    }

    @GetMapping("/priorities")
    public List<MeetingPriorityEntity> getAllPriorities() {
        return meetingPriorityRepo.findAll();
    }
}
