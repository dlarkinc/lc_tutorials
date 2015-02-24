package ie.cit.caf.tutorial3.service;

import java.util.ArrayList;
import java.util.List;

import ie.cit.caf.lctutorial3.domain.Artist;
import ie.cit.caf.lctutorial3.repository.ArtistRepository;
import ie.cit.caf.lctutorial3.service.ArtistService;
import ie.cit.caf.lctutorial3.service.ArtistServiceImpl;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class ArtistRepositoryImplTests {

	private ArtistService artistService;
	private ArtistRepository repoMock;
	
	@Before
	public void setup() {
		repoMock = mock(ArtistRepository.class);
		
		Artist a = new Artist();
		a.setName("John");
		a.setId(1);
		a.setGender("male");
		
		Artist b = new Artist();
		b.setName("Mary");
		b.setId(2);
		b.setGender("female");
		
		List<Artist> artists = new ArrayList<>();
		
		artists.add(a);
		artists.add(b);
		
		when(repoMock.get(1)).thenReturn(a);
		when(repoMock.findAll()).thenReturn(artists);
		
		artistService = new ArtistServiceImpl(repoMock);
	}
	
	@Test
	public void get() {
		assertTrue(artistService.get(1).getName().equals("John"));
	}
	
	@Test
	public void findAll() {
		assertTrue(artistService.findAll().size() == 2);
	}
	
}
