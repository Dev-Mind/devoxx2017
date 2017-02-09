package com.devmind.performance.model.session;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import com.devmind.performance.model.member.Member;

@Entity
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Session session;

    @ManyToOne
    private Member member;

    @Version
    private int version;

    /**
     * true if the vote is positive
     */
    @NotNull
    public Boolean value;

    public Long getId() {
        return id;
    }

    public Vote setId(Long id) {
        this.id = id;
        return this;
    }

    public Session getSession() {
        return session;
    }

    public Vote setSession(Session session) {
        this.session = session;
        return this;
    }

    public Member getMember() {
        return member;
    }

    public Vote setMember(Member member) {
        this.member = member;
        return this;
    }

    public Boolean getValue() {
        return value;
    }

    public Vote setValue(Boolean value) {
        this.value = value;
        return this;
    }

    public int getVersion() {
        return version;
    }

    public Vote setVersion(int version) {
        this.version = version;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vote vote = (Vote) o;
        return Objects.equals(session, vote.session) &&
                Objects.equals(member, vote.member) &&
                Objects.equals(value, vote.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(session, member, value);
    }
}
