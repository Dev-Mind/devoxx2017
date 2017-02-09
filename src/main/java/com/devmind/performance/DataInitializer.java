package com.devmind.performance;

import java.io.IOException;
import java.util.List;
import javax.annotation.PostConstruct;

import com.devmind.performance.dto.MemberDto;
import com.devmind.performance.dto.SessionDto;
import com.devmind.performance.model.session.Session;
import com.devmind.performance.repository.SessionRepository;
import com.devmind.performance.repository.SpeakerRepository;
import com.devmind.performance.repository.SponsorRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Load data from json files when database is empty
 */
@Component
@Transactional
public class DataInitializer {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ResourceLoader resourceLoader;
    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private SpeakerRepository speakerRepository;
    @Autowired
    private SponsorRepository sponsorRepository;

    protected <T> List<T> fromJson(String filename, Class<T> className) {
        Resource file = resourceLoader.getResource("classpath:raw/" + filename);

        try {
            TypeFactory typeFactory = objectMapper.getTypeFactory();
            return objectMapper.readValue(file.getFile(), typeFactory.constructCollectionType(List.class, className));
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostConstruct
    public void init() {
        if (sessionRepository.count() == 0) {
            List<MemberDto> members = fromJson("speakers.json", MemberDto.class);
            members.stream()
                    .map(MemberDto::toSpeaker)
                    .forEach(speaker -> speakerRepository.save(speaker));

            members = fromJson("sponsors.json", MemberDto.class);
            members.stream()
                    .map(MemberDto::toSponsor)
                    .forEach(sponsor -> sponsorRepository.save(sponsor));

            List<SessionDto> sessions = fromJson("sessions.json", SessionDto.class);
            sessions.forEach(s -> {
                //A session is created
                Session persistedSession = sessionRepository.save(s.toSession());
                //We add the links (many to many so after the first save)
                s.speakers.forEach(sp ->
                        persistedSession.addSpeaker(speakerRepository.findByHash(sp.getHash()))
                );
                sessionRepository.save(persistedSession);
            });
        }
    }
}

