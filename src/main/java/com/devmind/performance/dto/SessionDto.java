package com.devmind.performance.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.devmind.performance.model.session.Level;
import com.devmind.performance.model.session.Room;
import com.devmind.performance.model.session.Session;
import com.devmind.performance.model.session.Vote;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;

/**
 * Member DTO(ignoreUnknown = false)
 */
@JsonIgnoreProperties
public class SessionDto {

    public interface SessionList {
    }

    @JsonView(SessionList.class)
    private long idSession;
    @JsonView(SessionList.class)
    public String title;
    @JsonView(SessionList.class)
    public String summary;
    @JsonView(SessionList.class)
    public String start;
    @JsonView(SessionList.class)
    public String end;
    @JsonView(SessionList.class)
    public String room;
    @JsonView(SessionList.class)
    public List<MemberDto> speakers = new ArrayList<>();
    public int votes = 0;
    public int positiveVotes = 0;
    public long nbConsults;
    public String lang;
    public String format;
    public String description;
    public String ideaForNow;
    public String year;
    public String level;
    private int version;

    public static SessionDto convert(Session session) {
        SessionDto dto = new SessionDto()
                .withIdSession(session.getId())
                .withDescription(session.getDescription())
                .withSummary(session.getSummary())
                .withRoom(session.getRoom() == null ? Room.Amphi1.getName() : session.getRoom().getName())
                .withLevel(session.getLevel() == null ? null : session.getLevel().name())
                .withTitle(session.getTitle())
                .withEnd(session.getEnd() == null ? null : session.getEnd().format(DateTimeFormatter.ISO_DATE_TIME))
                .withStart(session.getStart() == null ? null : session.getStart().format(DateTimeFormatter.ISO_DATE_TIME))
                .withVersion(session.getVersion());

        List<Vote> votes = session.getVotes();
        if (!votes.isEmpty()) {
            dto.withVotes(((Long) session.getVotes().stream().distinct().count()).intValue());
            dto.withPositiveVotes(session.getPositiveVotes());
        }
        if (!session.getSpeakers().isEmpty()) {
            session.getSpeakers().forEach(s -> dto.addSpeaker(MemberDto.convert(s)));
        }
        return dto;
    }

    public Session toSession() {
        return new Session()
                .withId(idSession)
                .withDescription(description)
                .withSummary(summary)
                .withTitle(title)
                .withRoom(room != null ? Room.find(room) : null)
                .withLevel(level != null ? Level.valueOf(level) : null)
                .withEnd(end != null ? LocalDateTime.parse(end, DateTimeFormatter.ISO_DATE_TIME) : null)
                .withStart(start != null ? LocalDateTime.parse(start, DateTimeFormatter.ISO_DATE_TIME) : null)
                .withVersion(version);
    }

    public long getIdSession() {
        return idSession;
    }

    public SessionDto withIdSession(long idSession) {
        this.idSession = idSession;
        return this;
    }

    public String getLevel() {
        return level;
    }

    public SessionDto withLevel(String level) {
        this.level = level;
        return this;
    }

    public SessionDto withRoom(String room) {
        this.room = room;
        return this;
    }

    public String getRoom() {
        return room;
    }

    public String getStart() {
        return start;
    }

    public SessionDto withStart(String start) {
        this.start = start;
        return this;
    }

    public String getEnd() {
        return end;
    }

    public SessionDto withEnd(String end) {
        this.end = end;
        return this;
    }

    public int getVotes() {
        return votes;
    }

    public SessionDto withVotes(int votes) {
        this.votes = votes;
        return this;
    }

    public int getPositiveVotes() {
        return positiveVotes;
    }

    public SessionDto withPositiveVotes(int positiveVotes) {
        this.positiveVotes = positiveVotes;
        return this;
    }

    public long getNbConsults() {
        return nbConsults;
    }

    public SessionDto withNbConsults(long nbConsults) {
        this.nbConsults = nbConsults;
        return this;
    }

    public String getLang() {
        return lang;
    }

    public SessionDto withLang(String lang) {
        this.lang = lang;
        return this;
    }

    public String getFormat() {
        return format;
    }

    public SessionDto withFormat(String format) {
        this.format = format;
        return this;
    }

    public SessionDto withVersion(int version) {
        this.version = version;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public SessionDto withTitle(String title) {
        this.title = title;
        return this;
    }

    public String getSummary() {
        return summary;
    }

    public SessionDto withSummary(String summary) {
        this.summary = summary;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public SessionDto withDescription(String description) {
        this.description = description;
        return this;
    }

    public String getIdeaForNow() {
        return ideaForNow;
    }

    public SessionDto withIdeaForNow(String ideaForNow) {
        this.ideaForNow = ideaForNow;
        return this;
    }

    public String getYear() {
        return year;
    }

    public SessionDto withYear(String year) {
        this.year = year;
        return this;
    }

    public SessionDto addSpeaker(MemberDto memberDto) {
        speakers.add(memberDto);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SessionDto that = (SessionDto) o;
        return Objects.equals(idSession, that.idSession);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), idSession);
    }
}
