package ie.cit.caf.larkin.cfdemo.service;

import java.util.List;

import ie.cit.caf.larkin.cfdemo.entity.Artist;
import ie.cit.caf.larkin.cfdemo.repository.ArtistRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArtistServiceImpl implements ArtistService {

	@Autowired
	ArtistRepository artistRepository;

	@Override
	public List<Artist> findByFullNameContainsIgnoreCase(String pattern) {
		return artistRepository.findByFullName(pattern);
	}

	@Override
	public Iterable<Artist> findAll() {
		return artistRepository.findAll();
	}
	
	
}
