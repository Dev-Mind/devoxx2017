package com.devmind.performance.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.devmind.performance.model.member.Member;
import com.devmind.performance.model.member.Sponsor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Test de {@link SponsorRepository}
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class SponsorRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private SponsorRepository repository;

    @Before
    public void init(){
        entityManager.persistAndFlush(new Sponsor()
                .withLogin("guillaume")
                .withCompany("Dev-Mind")
                .withHash("monhash")
                .withFirstname("Guillaume")
                .withEmail("guillaume@dev-mind.fr"));
    }

    @Test
    public void findAllSponsor(){
        assertThat(repository.findAllSponsor())
                .hasSize(1)
                .extracting(Member::getFirstname)
                .containsExactly("Guillaume");
    }
}