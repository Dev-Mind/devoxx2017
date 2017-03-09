package com.devmind.performance.repository;

import java.util.List;

import com.devmind.performance.config.WpCacheConfig;
import com.devmind.performance.model.member.Sponsor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * {@link Sponsor}
 */
public interface SponsorRepository extends CrudRepository<Sponsor, Long> {

    //@Cacheable(WpCacheConfig.CACHE_SPONSOR)
    @Query(value = "SELECT DISTINCT m FROM Sponsor m")
    List<Sponsor> findAllSponsor();
}
