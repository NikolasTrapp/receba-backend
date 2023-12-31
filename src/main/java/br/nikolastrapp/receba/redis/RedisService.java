package br.nikolastrapp.receba.redis;

import br.nikolastrapp.receba.entities.RoleEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class RedisService {

    private final RedisTemplate<String, RoleEntity> roleEntityTemplate;

    public void put(String key, RoleEntity value, long expirationTimeInMinutes) {
        roleEntityTemplate.opsForValue().set(key, value, expirationTimeInMinutes, TimeUnit.MINUTES);
    }

    public RoleEntity get(String key) {
        return roleEntityTemplate.opsForValue().get(key);
    }

    public void clearCache() {
        try {
            Objects.requireNonNull(roleEntityTemplate.getConnectionFactory()).getConnection().flushAll();
        } catch (NullPointerException err) {
            log.error("Could not acquire redis connection: {}", err.getMessage(), err);
        } catch (Exception err) {
            log.error("Unknown error happened while flushing redis data: {}", err.getMessage(), err);
        }
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void clearCacheDaily() {
        clearCache();
    }

}
