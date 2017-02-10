package com.devmind.performance.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import com.devmind.performance.model.member.Member;
import com.devmind.performance.model.member.Speaker;
import com.devmind.performance.model.session.Session;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.jpa.repository.Query;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Test de {@link SpeakerRepository}
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class SpeakerRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private SpeakerRepository repository;

    @Before
    public void init(){
        Speaker speaker = entityManager.persistAndFlush(new Speaker()
                .withLogin("guillaume")
                .withFirstname("Guillaume")
                .withLastname("EHRET")
                .withCompany("Dev-Mind")
                .withHash("monhash")
                .withEmail("guillaume@dev-mind.fr"));

        entityManager.persistAndFlush(new Member<>()
                .withLogin("avatar")
                .withFirstname("Avatar")
                .withLastname("AVATAR")
                .withCompany("Dev-Mind")
                .withEmail("avatar@dev-mind.fr"));

        entityManager.persistAndFlush(new Session()
                .withTitle("My session")
                .withDescription("My description")
                .addSpeaker(speaker));
    }

    @Test
    public void findByLogin(){
        assertThat(repository.findByLogin("guillaume"))
                .isNotNull()
                .extracting(Member::getFirstname)
                .containsExactly("Guillaume");

        //avatar is not a speaker
        assertThat(repository.findByLogin("avatar")).isNull();
        assertThat(repository.findByLogin("null")).isNull();
    }


    @Test
    public void findByHash(){
        assertThat(repository.findByHash("monhash"))
                .isNotNull()
                .extracting(Member::getFirstname)
                .containsExactly("Guillaume");

        assertThat(repository.findByHash("truc")).isNull();
        assertThat(repository.findByHash("null")).isNull();
    }

    @Test
    public void findAllSpeakers(){
        assertThat(repository.findAllSpeakers())
                .hasSize(1)
                .extracting(Member::getFirstname)
                .containsExactly("Guillaume");
    }
}