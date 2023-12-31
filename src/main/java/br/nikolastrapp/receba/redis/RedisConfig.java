package br.nikolastrapp.receba.redis;

import br.nikolastrapp.receba.entities.RoleEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfig {

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory();
    }

    @Bean
    public RedisTemplate<String, RoleEntity> roleEntityRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, RoleEntity> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setKeySerializer(new CustomRedisSerializer<RoleEntity>());
        return template;
    }

}
