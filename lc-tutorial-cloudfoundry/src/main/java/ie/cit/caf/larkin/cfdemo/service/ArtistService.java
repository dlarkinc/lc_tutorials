package ie.cit.caf.larkin.cfdemo.service;

import ie.cit.caf.larkin.cfdemo.entity.Artist;

import java.util.List;

public interface ArtistService {

	List<Artist> findByFullNameContainsIgnoreCase(String pattern);
	
	Iterable<Artist> findAll();
}
