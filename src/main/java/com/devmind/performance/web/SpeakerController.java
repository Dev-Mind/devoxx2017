package com.devmind.performance.web;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.devmind.performance.dto.MemberDto;
import com.devmind.performance.model.member.Speaker;
import com.devmind.performance.repository.SpeakerRepository;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/speakers")
public class SpeakerController {

    @Autowired
    SpeakerRepository speakerRepository;

    @GetMapping("/{id}")
    public ResponseEntity<MemberDto> getMember(@PathVariable("id") Long id) {
        return ResponseEntity
                .ok()
                .body(MemberDto.convert(speakerRepository.findOne(id)));
    }

    @GetMapping
    @JsonView(MemberDto.MemberList.class)
    public List<MemberDto> getAllMembers() {
        return speakerRepository
                .findAllSpeakers()
                .stream()
                .sorted(Comparator.comparing(Speaker::getFirstname))
                .map(MemberDto::convert)
                .collect(Collectors.toList());
    }
}