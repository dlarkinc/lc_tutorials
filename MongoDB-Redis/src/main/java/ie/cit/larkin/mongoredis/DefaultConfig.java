package ie.cit.larkin.mongoredis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JacksonJsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import ie.cit.larkin.mongoredis.entity.Artist;

@SpringBootApplication
public class DefaultConfig {

	@Autowired
	private JedisConnectionFactory jedisConnFactory;

	@Bean
	public StringRedisSerializer stringRedisSerializer() {
		StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
		return stringRedisSerializer;
	}

	@Bean
	public Jackson2JsonRedisSerializer<Artist> jacksonJsonRedisJsonSerializer() {
		return new Jackson2JsonRedisSerializer<>(Artist.class);
	}

	@Bean
	public RedisTemplate<String, Artist> redisTemplate() {
		RedisTemplate<String, Artist> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(jedisConnFactory);
		redisTemplate.setKeySerializer(stringRedisSerializer());
		redisTemplate.setValueSerializer(jacksonJsonRedisJsonSerializer());
		return redisTemplate;
	}
}
