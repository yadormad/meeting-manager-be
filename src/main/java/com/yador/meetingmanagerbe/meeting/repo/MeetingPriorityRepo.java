package com.yador.meetingmanagerbe.meeting.repo;

import com.yador.meetingmanagerbe.meeting.entity.MeetingPriorityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetingPriorityRepo extends JpaRepository<MeetingPriorityEntity, Integer> {
}
