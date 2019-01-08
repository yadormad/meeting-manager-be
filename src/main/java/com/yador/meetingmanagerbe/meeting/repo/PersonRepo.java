package com.yador.meetingmanagerbe.meeting.repo;

import com.yador.meetingmanagerbe.meeting.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepo extends JpaRepository<PersonEntity, Integer> {
}
