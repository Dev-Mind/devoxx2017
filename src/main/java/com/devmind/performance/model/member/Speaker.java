package com.devmind.performance.model.member;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import com.devmind.performance.model.session.Session;

/**
 * @author Dev-Mind <guillaume@dev-mind.fr>
 * @since 29/03/16.
 */
@Entity
public class Speaker extends Member<Speaker> {
    @ManyToMany(mappedBy = "speakers")
    public Set<Session> sessions = new HashSet<>();

    public Set<Session> getSessions() {
        return sessions;
    }

    public Speaker clearSessions() {
        this.sessions.clear();
        return this;
    }

    public Speaker addSession(Session session) {
        this.sessions.add(session);
        return this;
    }

    public Speaker removeSharedLink(Session session) {
        this.sessions.remove(session);
        return this;
    }
}
