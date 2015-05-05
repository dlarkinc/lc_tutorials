package ie.cit.caf.redis;

import ie.cit.caf.redis.repository.ILookupRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootApplication
public class LcTutorialRedisApplication implements CommandLineRunner {

	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	@Autowired
	private ILookupRepository lookupRepository;
	
    public static void main(String[] args) {
        SpringApplication.run(LcTutorialRedisApplication.class, args);
    }

	@Override
	public void run(String... arg0) throws Exception {
		
		String key1 = "person.larkin.name";
		String value1 = "Larkin Cunningham";
		String key2 = "person.larkin.age";
		Integer value2 = 21;  // :)

		// add the key-value pairs
		redisTemplate.boundValueOps(key1).append(value1);
		redisTemplate.boundValueOps(key2).append(value2.toString());
		
		// query by key
		System.out.println("Name: " + redisTemplate.opsForValue().get(key1));
		System.out.println("Age:  " + redisTemplate.opsForValue().get(key2));

		useRepo();
	}
	
	private void useRepo() {
		lookupRepository.add("person.lorna.name", "Lorna Doone");
		lookupRepository.add("person.lorna.age", Integer.toString(146));

		System.out.println("Name: " + redisTemplate.opsForValue().get("person.lorna.name"));
		System.out.println("Age:  " + redisTemplate.opsForValue().get("person.lorna.age"));
	}
}
