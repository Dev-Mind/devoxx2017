package com.devmind.performance.web;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import com.devmind.performance.dto.MemberDto;
import com.devmind.performance.repository.SponsorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sponsors")
public class SponsorController {

    @Autowired
    SponsorRepository sponsorRepository;

    @GetMapping("/{id}")
    public ResponseEntity<MemberDto> getMember(@PathVariable("id") Long id) {
        MemberDto sponsor = MemberDto.convert(sponsorRepository.findOne(id));
        return ResponseEntity
                .ok()
                .body(sponsor);
    }

    @GetMapping
    public ResponseEntity<List<MemberDto>> getAllSponsors() {
        List<MemberDto> sponsors = sponsorRepository.findAllSponsor().stream()
                .map(MemberDto::convert)
                .collect(Collectors.toList());

        return ResponseEntity
                .ok()
                .cacheControl(CacheControl.maxAge(2, TimeUnit.DAYS))
                .eTag(String.valueOf(sponsors.size() +
                        sponsors.stream()
                                .collect(Collectors.summingInt(MemberDto::getVersion))))
                .body(sponsors);
    }
}