package ie.cit.caf.lctutorial3.service;

import java.util.List;

import ie.cit.caf.lctutorial3.domain.Artist;

public interface ArtistService {

	void add(Artist artist);
	
	void update(Artist artist);
	
	Artist getById(int id);
	
	void delete(Artist artist);
	
	List<Artist> getAllArtists();
}
