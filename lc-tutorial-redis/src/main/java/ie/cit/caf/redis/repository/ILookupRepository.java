package ie.cit.caf.redis.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface ILookupRepository {

	void add(String key, String value);
	
	String get(String key);
}
