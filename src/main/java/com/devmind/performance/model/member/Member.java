package com.devmind.performance.model.member;


import static com.devmind.performance.util.StringUtils.*;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Member<T extends Member> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(max = 100)
    private String login;

    @Size(max = 250)
    private String email;

    @Size(max = 100)
    private String firstname;

    @Size(max = 100)
    private String lastname;

    @Size(max = 100)
    private String company;

    @Size(max = 300)
    private String shortDescription;

    @Lob
    private String longDescription;

    @Size(max = 512)
    private String logoUrl;

    @Size(max = 255)
    private String token;

    private String hash;

    @Version
    private int version;

    public Long getId() {
        return id;
    }

    public T withId(Long id) {
        this.id = id;
        return (T) this;
    }

    public String getLogin() {
        return login;
    }

    public T withLogin(String login) {
        this.login = login;
        return (T) this;
    }

    public String getEmail() {
        return email;
    }

    public T withEmail(String email) {
        this.email = lowercase(email);
        return (T) this;
    }

    public String getFirstname() {
        return firstname;
    }

    public T withFirstname(String firstname) {
        this.firstname = capitalize(lowercase(firstname));
        return (T) this;
    }

    public String getLastname() {
        return lastname;
    }

    public T withLastname(String lastname) {
        this.lastname = uppercase(lastname);
        return (T) this;
    }

    public String getCompany() {
        return company;
    }

    public T withCompany(String company) {
        this.company = company;
        return (T) this;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public T withShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
        return (T) this;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public T withLongDescription(String longDescription) {
        this.longDescription = longDescription;
        return (T) this;
    }

    public String getToken() {
        return token;
    }

    public T withToken(String token) {
        this.token = token;
        return (T) this;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public T withLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
        return (T) this;
    }

    public String getHash() {
        return hash;
    }

    public T withHash(String hash) {
        this.hash = hash;
        return (T) this;
    }

    public Integer getVersion() {
        return version;
    }

    public T withVersion(Integer version) {
        this.version = version;
        return (T) this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return Objects.equals(id, member.id) &&
                Objects.equals(login, member.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login);
    }

}