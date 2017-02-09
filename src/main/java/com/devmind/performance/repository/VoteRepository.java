package com.devmind.performance.repository;

import java.util.List;

import com.devmind.performance.model.member.Member;
import com.devmind.performance.model.session.Vote;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * {@link Vote}
 */
public interface VoteRepository extends CrudRepository<Vote, Long> {

    @Query(value = "SELECT m FROM Vote m where m.session.id=:idSession and m.member.id=:idMember")
    Vote findBySessionAndMember(@Param("idSession") Long idSession, @Param("idMember") Long idMember);

    List<Vote> findByMember(Member member);
}
