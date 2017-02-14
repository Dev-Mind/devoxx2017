package com.devmind.performance.web;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.Arrays;

import com.devmind.performance.model.session.Session;
import com.devmind.performance.repository.SessionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Test {@link SessionController}
 */
@RunWith(SpringRunner.class)
@WebMvcTest(SessionController.class)
public class SessionControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private SessionRepository sessionRepository;

    @Test
    public void findAll() throws Exception {

        given(this.sessionRepository.findAllSessions()).willReturn(Arrays.asList(
                new Session().withTitle("Performance web").withStart(LocalDateTime.now()).withId(1L),
                new Session().withTitle("Test in Javascript").withStart(LocalDateTime.now()).withId(2L)
        ));

        this.mvc.perform(
                get("/api/sessions"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..title", containsInAnyOrder("Performance web", "Test in Javascript")));
    }

    @Test
    public void findOne() throws Exception {

        given(this.sessionRepository.findOne(10L))
                .willReturn(new Session().withTitle("Performance web").withStart(LocalDateTime.now()).withId(1L));

        this.mvc.perform(
                get("/api/sessions/10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("Performance web")));
    }

}