package ie.cit.caf.neo4j.repository;

import java.util.List;

import ie.cit.caf.neo4j.node.Movement;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovementRepository  extends CrudRepository<Movement, Long> {
	List<Movement> findByName(String name);
	Movement findById(Long id);
}
