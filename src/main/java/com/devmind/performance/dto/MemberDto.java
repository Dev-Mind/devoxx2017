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

    //@JsonView(MemberList.class)
    private Long idMember;
    //@JsonView({MemberList.class, SessionDto.SessionList.class})
    private String firstname;
    //@JsonView({MemberList.class, SessionDto.SessionList.class})
    private String lastname;
    //@JsonView(MemberList.class)
    private String hash;
    //@JsonView(MemberList.class)
    private String shortDescription;
    private String logo;
    private String login;
    private String company;
    private String longDescription;
    private int version;


    public static <T extends Member<T>> MemberDto convert(T member) {
        return new MemberDto()
                .withIdMember(member.getId())
                .withLogin(member.getLogin())
                .withFirstname(member.getFirstname())
                .withLastname(member.getLastname())
                .withCompany(member.getCompany())
                .withHash(member.getHash())
                .withLogo(member.getLogoUrl())
                .withShortDescription(member.getShortDescription())
                .withLongDescription(member.getLongDescription())
                .withVersion(member.getVersion());
    }

    public Speaker toSpeaker() {
        return new Speaker()
                .withId(idMember)
                .withHash(hash)
                .withLogin(login)
                .withFirstname(firstname)
                .withLastname(lastname)
                .withToken(firstname)
                .withCompany(company)
                .withLogoUrl(logo)
                .withShortDescription(shortDescription)
                .withLongDescription(longDescription)
                .withVersion(version);
    }

    public Sponsor toSponsor() {
        return new Sponsor()
                .withId(idMember)
                .withHash(hash)
                .withLogin(login)
                .withFirstname(firstname)
                .withLastname(lastname)
                .withToken(firstname)
                .withCompany(company)
                .withLogoUrl(logo)
                .withShortDescription(shortDescription)
                .withLongDescription(longDescription)
                .withVersion(version);
    }

    public Long getIdMember() {
        return idMember;
    }

    public MemberDto withIdMember(Long idMember) {
        this.idMember = idMember;
        return this;
    }

    public String getLogin() {
        return login;
    }

    public MemberDto withLogin(String login) {
        this.login = login;
        return this;
    }

    public String getLogo() {
        return logo;
    }

    public MemberDto withLogo(String logo) {
        this.logo = logo;
        return this;
    }

    public String getFirstname() {
        return firstname;
    }

    public MemberDto withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public String getLastname() {
        return lastname;
    }

    public MemberDto withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public String getCompany() {
        return company;
    }

    public MemberDto withCompany(String company) {
        this.company = company;
        return this;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public MemberDto withShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
        return this;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public MemberDto withLongDescription(String longDescription) {
        this.longDescription = longDescription;
        return this;
    }

    public String getHash() {
        return hash;
    }

    public MemberDto withHash(String hash) {
        this.hash = hash;
        return this;
    }

    public int getVersion() {
        return version;
    }

    public MemberDto withVersion(int version) {
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
