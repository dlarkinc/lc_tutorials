package ie.cit.larkin.mongoredis;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JacksonJsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import ie.cit.larkin.mongoredis.entity.Artist;
import ie.cit.larkin.mongoredis.entity.Movement;
import ie.cit.larkin.mongoredis.repository.ArtistRepository;
import ie.cit.larkin.mongoredis.repository.ILookupRepository;

@Import(DefaultConfig.class)
public class MongoDbRedisApplication implements CommandLineRunner {

	@Autowired
	ArtistRepository artistRepository;
	
	@Autowired
	ILookupRepository lookupRepository;
	
	@Autowired
	RedisTemplate<String, Artist> redisTemplate;
	
    public static void main(String[] args) {
        SpringApplication.run(MongoDbRedisApplication.class, args);
    }

	@Override
	public void run(String... arg0) throws Exception {
		// add an artist
		Artist a = new Artist();
		a.setName("Gary Numan");
		a.setBirthYear(1999);
		a.setGender("male");
		
		Movement m = new Movement();
		m.setName("Surrealism");
		
		List<Movement> movements = new ArrayList();
		movements.add(m);
		
		a.setMovements(movements);
		
		artistRepository.save(a);
		
		System.out.println("Artist document saved");
		
		// retrieve an artist document
		
		Artist b = artistRepository.findByName("Gary Numan");
		System.out.println(b.toString());
		
		// store in Redis
		redisTemplate.opsForValue().set("artist:123", b);
		
		// retrieve / deserialize from Redis
		Artist c = redisTemplate.opsForValue().get("artist:123");
		System.out.println(c.toString());
	}
}
