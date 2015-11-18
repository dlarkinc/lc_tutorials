package ie.cit.larkin.mongoredis.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import ie.cit.larkin.mongoredis.entity.Artist;

@Repository
public interface ArtistRepository extends MongoRepository<Artist, String> {
	public Artist findByName(String name);
}
