package ie.cit.caf.lctutorial3.repository;

import ie.cit.caf.lctutorial3.domain.Artist;

import java.util.List;

public interface ArtistRepository {

	Artist get(int id);
	
	void save(Artist artist);

	void remove(Artist artist);
	
	List<Artist> findAll();
	
}
