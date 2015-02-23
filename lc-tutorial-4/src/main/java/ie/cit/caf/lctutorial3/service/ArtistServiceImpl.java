package ie.cit.caf.lctutorial3.service;

import ie.cit.caf.lctutorial3.domain.Artist;
import ie.cit.caf.lctutorial3.repository.ArtistRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArtistServiceImpl implements ArtistService {

	@Autowired
	ArtistRepository artistRepository;
	
	@Override
	public void add(Artist artist) {
		artistRepository.save(artist);
	}

	@Override
	public void update(Artist artist) {
		artistRepository.save(artist);
	}

	@Override
	public Artist getById(int id) {
		return artistRepository.get(id);
	}

	@Override
	public List<Artist> getAllArtists() {
		return artistRepository.findAll();
	}

	@Override
	public void delete(Artist artist) {
		artistRepository.remove(artist);
	}

}
