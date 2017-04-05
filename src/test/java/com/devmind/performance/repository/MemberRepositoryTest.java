package com.devmind.performance.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.devmind.performance.model.member.Member;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Test de {@link MemberRepository}
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class MemberRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private MemberRepository repository;

    @Before
    public void init(){
        entityManager.persistAndFlush(new Member()
                .withLogin("guillaume")
                .withFirstname("Guillaume")
                .withLastname("EHRET")
                .withCompany("Dev-Mind")
                .withEmail("guillaume@dev-mind.fr"));
    }

    @Test
    public void findByEmail(){
        assertThat(repository.findByEmail("guillaume@dev-mind.fr"))
                .isNotNull()
                .extracting(Member::getFirstname)
                .containsExactly("Guillaume");

        assertThat(repository.findByEmail("bidon@dev-mind.fr")).isNull();
        assertThat(repository.findByEmail(null)).isNull();
    }
}