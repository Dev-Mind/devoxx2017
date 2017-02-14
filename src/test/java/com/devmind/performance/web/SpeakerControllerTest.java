package com.devmind.performance.web;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import com.devmind.performance.model.member.Speaker;
import com.devmind.performance.repository.SpeakerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Test {@link SpeakerController}
 */
@RunWith(SpringRunner.class)
@WebMvcTest(SpeakerController.class)
public class SpeakerControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private SpeakerRepository speakerRepository;

    @Test
    public void findAll() throws Exception {

        given(this.speakerRepository.findAllSpeakers()).willReturn(Arrays.asList(
                new Speaker().withFirstname("Dan").withLastname("North"),
                new Speaker().withFirstname("Joel").withLastname("Spolsky")
        ));

        this.mvc.perform(
                get("/api/speakers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..firstname", containsInAnyOrder("Dan", "Joel")));
    }

    @Test
    public void findOne() throws Exception {

        given(this.speakerRepository.findOne(10L))
                .willReturn(new Speaker().withFirstname("Dan").withLastname("North"));

        this.mvc.perform(
                get("/api/speakers/10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname", is("Dan")));
    }

}