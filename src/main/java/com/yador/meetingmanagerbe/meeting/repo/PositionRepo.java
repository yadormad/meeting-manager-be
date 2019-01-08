package com.yador.meetingmanagerbe.meeting.repo;

import com.yador.meetingmanagerbe.meeting.entity.PositionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepo extends JpaRepository<PositionEntity, Integer> {
}
