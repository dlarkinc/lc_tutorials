package ie.cit.caf.tutorial3.repository;

import static org.junit.Assert.*;

import java.util.List;

import ie.cit.caf.lctutorial3.DefaultConfig;
import ie.cit.caf.lctutorial3.JdbcTemplateTestApplication;
import ie.cit.caf.lctutorial3.domain.Artist;
import ie.cit.caf.lctutorial3.repository.JdbcArtistRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DefaultConfig.class)
@ActiveProfiles("default")
@TransactionConfiguration(defaultRollback=true)
public class JdbcArtistRepositoryTestsWithRollback {

	@Autowired
	JdbcArtistRepository repo;
	
	@Test
	public void get() {
		Artist a = repo.get(1);
		assertEquals("Chadwick, Lynn", a.getName());
	}

	@Test
	public void findAll() {
		List<Artist> artists = repo.findAll();
		assertEquals(5, artists.size());
	}
	
	@Test
	@Transactional
	public void remove() {
		Artist a = repo.get(1);
		repo.remove(a);
		List<Artist> artists = repo.findAll();
		assertEquals(4, artists.size());
	}

}
