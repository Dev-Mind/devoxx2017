package com.devmind.performance.dto;

import java.util.Objects;

import com.devmind.performance.model.member.Member;
import com.devmind.performance.model.member.Speaker;
import com.devmind.performance.model.member.Sponsor;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;

/**
 * Member DTO
 */
@JsonIgnoreProperties(ignoreUnknown = false)
public class MemberDto {

    public interface MemberList {
    }

    @JsonView(MemberList.class)
    private Long idMember;
    @JsonView({MemberList.class, SessionDto.SessionList.class})
    private String firstname;
    @JsonView({MemberList.class, SessionDto.SessionList.class})
    private String lastname;
    @JsonView(MemberList.class)
    private String hash;
    @JsonView(MemberList.class)
    private String shortDescription;
    private String logo;
    private String login;
    private String company;
    private String longDescription;
    private int version;


    public static <T extends Member<T>> MemberDto convert(T member) {
        return new MemberDto()
                .setIdMember(member.getId())
                .setLogin(member.getLogin())
                .setFirstname(member.getFirstname())
                .setLastname(member.getLastname())
                .setCompany(member.getCompany())
                .setHash(member.getHash())
                .setLogo(member.getLogoUrl())
                .setShortDescription(member.getShortDescription())
                .setLongDescription(member.getLongDescription())
                .setVersion(member.getVersion());
    }

    public Speaker toSpeaker() {
        return new Speaker()
                .setId(idMember)
                .setHash(hash)
                .setLogin(login)
                .setFirstname(firstname)
                .setLastname(lastname)
                .setToken(firstname)
                .setCompany(company)
                .setLogoUrl(logo)
                .setShortDescription(shortDescription)
                .setLongDescription(longDescription)
                .setVersion(version);
    }

    public Sponsor toSponsor() {
        return new Sponsor()
                .setId(idMember)
                .setHash(hash)
                .setLogin(login)
                .setFirstname(firstname)
                .setLastname(lastname)
                .setToken(firstname)
                .setCompany(company)
                .setLogoUrl(logo)
                .setShortDescription(shortDescription)
                .setLongDescription(longDescription)
                .setVersion(version);
    }

    public Long getIdMember() {
        return idMember;
    }

    public MemberDto setIdMember(Long idMember) {
        this.idMember = idMember;
        return this;
    }

    public String getLogin() {
        return login;
    }

    public MemberDto setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getLogo() {
        return logo;
    }

    public MemberDto setLogo(String logo) {
        this.logo = logo;
        return this;
    }

    public String getFirstname() {
        return firstname;
    }

    public MemberDto setFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public String getLastname() {
        return lastname;
    }

    public MemberDto setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public String getCompany() {
        return company;
    }

    public MemberDto setCompany(String company) {
        this.company = company;
        return this;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public MemberDto setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
        return this;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public MemberDto setLongDescription(String longDescription) {
        this.longDescription = longDescription;
        return this;
    }

    public String getHash() {
        return hash;
    }

    public MemberDto setHash(String hash) {
        this.hash = hash;
        return this;
    }

    public int getVersion() {
        return version;
    }

    public MemberDto setVersion(int version) {
        this.version = version;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MemberDto that = (MemberDto) o;
        return Objects.equals(idMember, that.idMember) &&
                Objects.equals(login, that.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), idMember, login);
    }
}
