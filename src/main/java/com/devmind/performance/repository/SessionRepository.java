package com.devmind.performance.repository;

import java.util.List;

import com.devmind.performance.config.WpCacheConfig;
import com.devmind.performance.model.session.Session;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * {@link Session}
 */
public interface SessionRepository extends CrudRepository<Session, Long> {

    //@Cacheable(WpCacheConfig.CACHE_SESSION)
    @Query(value = "SELECT DISTINCT s FROM Session s left join fetch s.speakers sp left join fetch s.votes")
    List<Session> findAllSessions();
}
