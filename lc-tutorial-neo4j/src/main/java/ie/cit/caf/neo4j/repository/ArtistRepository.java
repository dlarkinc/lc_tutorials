package ie.cit.caf.neo4j.repository;

import java.util.List;

import ie.cit.caf.neo4j.node.Artist;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository  extends CrudRepository<Artist, Long> {
	List<Artist> findByName(String name);
	Artist findById(Long id);
}
