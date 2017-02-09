package com.devmind.performance.model.session;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.devmind.performance.model.member.Member;
import com.devmind.performance.model.member.Speaker;

@Entity
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(max = 100)
    private String title;

    private Instant addedAt = Instant.now();

    private Integer maxAttendees;

    @Enumerated(EnumType.STRING)
    private Level level;

    @Enumerated(EnumType.STRING)
    private Room room;

    @Size(max = 300)
    private String summary;

    @Lob
    @NotNull
    private String description;

    @ManyToMany
    @NotNull
    private Set<Member> speakers = new HashSet<>();

    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vote> votes = new ArrayList<>();

    private LocalDateTime start;

    private LocalDateTime end;

    @Version
    private int version;

    public Long getId() {
        return id;
    }

    public Session setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Session setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getSummary() {
        return summary;
    }

    public Session setSummary(String summary) {
        this.summary = summary;
        return this;
    }

    public Instant getAddedAt() {
        return addedAt;
    }

    public Session setAddedAt(Instant addedAt) {
        this.addedAt = addedAt;
        return this;
    }

    public Integer getMaxAttendees() {
        return maxAttendees;
    }

    public Session setMaxAttendees(Integer maxAttendees) {
        this.maxAttendees = maxAttendees;
        return this;
    }

    public Level getLevel() {
        return level;
    }

    public Session setLevel(Level level) {
        this.level = level;
        return this;
    }

    public Room getRoom() {
        return room;
    }

    public Session setRoom(Room room) {
        this.room = room;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Session setDescription(String description) {
        this.description = description;
        return this;
    }

    public Set<Member> getSpeakers() {
        return speakers;
    }

    public boolean containsSpeaker(Long idSpeaker) {
        return this.speakers
                .stream()
                .filter(s -> s.getId().equals(idSpeaker))
                .findFirst()
                .isPresent();
    }

    public Session addSpeaker(Speaker speaker) {
        if(speaker!=null){
            this.speakers.add(speaker);
        }
        return this;
    }

    public Session removeSpeaker(Member speaker) {
        this.speakers.remove(speaker);
        return this;
    }

    public Session clearSpeakers() {
        this.speakers.clear();
        return this;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public int getPositiveVotes() {
        return votes.stream().distinct().collect(Collectors.summingInt(v -> Boolean.TRUE.equals(v.getValue()) ? 1 : 0));
    }

    public float getPositiveVotePercents() {
        if (votes.isEmpty()) {
            return 0;
        }
        return getPositiveVotes() / votes.size();
    }

    public Session addVote(Vote vote) {
        this.votes.add(vote);
        return this;
    }

    public Session removeVote(Vote vote) {
        this.votes.remove(vote);
        return this;
    }

    public Session clearVotes() {
        this.votes.clear();
        return this;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public Session setStart(LocalDateTime start) {
        this.start = start;
        return this;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public Session setEnd(LocalDateTime end) {
        this.end = end;
        return this;
    }

    public int getVersion() {
        return version;
    }

    public Session setVersion(int version) {
        this.version = version;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return Objects.equals(id, session.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
