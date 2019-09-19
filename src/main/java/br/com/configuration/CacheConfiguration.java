package br.com.configuration;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.cache.CacheManager;
import org.springframework.cache.guava.GuavaCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static java.util.Collections.singletonList;
import static java.util.concurrent.TimeUnit.DAYS;

@Configuration
public class CacheConfiguration {

    public static final String CACHE_SECTORS = "CACHE_SECTORS";

    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        Cache<Object, Object> builderCachePlanets = CacheBuilder.newBuilder().expireAfterWrite(1, DAYS).maximumSize(100).build();
        GuavaCache cachePlanets = new GuavaCache(CACHE_SECTORS, builderCachePlanets);
        cacheManager.setCaches(singletonList(cachePlanets));
        return cacheManager;
    }
}
