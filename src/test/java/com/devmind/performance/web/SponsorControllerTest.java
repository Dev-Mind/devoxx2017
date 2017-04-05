package com.devmind.performance.web;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import com.devmind.performance.model.member.Sponsor;
import com.devmind.performance.repository.SponsorRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Test {@link SponsorController}
 */
@RunWith(SpringRunner.class)
@WebMvcTest(SponsorController.class)
public class SponsorControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private SponsorRepository sponsorRepository;

    @Test
    public void findAll() throws Exception {

        given(this.sponsorRepository.findAllSponsor()).willReturn(Arrays.asList(
                new Sponsor().withLastname("Dev-Mind"),
                new Sponsor().withLastname("MiXiT")
        ));

        this.mvc.perform(
                get("/api/sponsors"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..lastname", containsInAnyOrder("DEV-MIND", "MIXIT")));
    }

    @Test
    public void findOne() throws Exception {

        given(this.sponsorRepository.findOne(10L)).willReturn(new Sponsor().withCompany("Dev-Mind"));

        this.mvc.perform(
                get("/api/sponsors/10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.company", is("Dev-Mind")));
    }

}