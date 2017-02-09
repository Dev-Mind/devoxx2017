package com.devmind.performance.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WpCacheConfig {

    public static final String CACHE_SPONSOR = "sponsor";
    public static final String CACHE_SESSION = "session";

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager(
                CACHE_SESSION,
                CACHE_SPONSOR);
    }
}
