package com.devmind.performance.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.devmind.performance.model.member.Member;
import com.devmind.performance.model.member.Speaker;
import com.devmind.performance.model.session.Session;
import com.devmind.performance.model.session.Vote;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Test de {@link VoteRepository}
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class VoteRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private VoteRepository repository;

    private Member member;

    @Before
    public void init(){
        Speaker speaker = entityManager.persistAndFlush(new Speaker()
                .withLogin("guillaume")
                .withFirstname("Guillaume")
                .withLastname("EHRET")
                .withCompany("Dev-Mind")
                .withHash("monhash")
                .withEmail("guillaume@dev-mind.fr"));

        member = entityManager.persistAndFlush(new Member<>()
                .withLogin("avatar")
                .withFirstname("Avatar")
                .withLastname("AVATAR")
                .withCompany("Dev-Mind")
                .withEmail("avatar@dev-mind.fr"));

        Session session = entityManager.persistAndFlush(new Session()
                .withTitle("My session")
                .withDescription("My description")
                .addSpeaker(speaker));

        entityManager.persistAndFlush(new Vote()
                .withMember(member)
                .withSession(session)
                .withValue(Boolean.TRUE));
    }

    @Test
    public void findByMember(){
        assertThat(repository.findByMember(member))
                .hasSize(1)
                .extracting(Vote::getValue)
                .containsExactly(Boolean.TRUE);
    }
}