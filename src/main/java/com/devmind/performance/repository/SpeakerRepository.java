package com.devmind.performance.repository;

import java.util.List;

import com.devmind.performance.model.member.Speaker;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * {@link Speaker}
 */
public interface SpeakerRepository extends CrudRepository<Speaker, Long> {

    @Query(value = "SELECT DISTINCT m FROM Speaker m inner join m.sessions s")
    List<Speaker> findAllSpeakers();

    Speaker findByLogin(String login);

    Speaker findByHash(String hash);
}
