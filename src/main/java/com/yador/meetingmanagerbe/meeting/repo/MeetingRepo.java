package com.yador.meetingmanagerbe.meeting.repo;

import com.yador.meetingmanagerbe.meeting.entity.MeetingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetingRepo extends JpaRepository<MeetingEntity, Integer> {
}
