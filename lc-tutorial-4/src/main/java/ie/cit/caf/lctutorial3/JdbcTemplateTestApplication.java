package ie.cit.caf.lctutorial3;

import ie.cit.caf.lctutorial3.domain.Artist;
import ie.cit.caf.lctutorial3.repository.ArtistRepository;
import ie.cit.caf.lctutorial3.service.ArtistService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

@Import(DefaultConfig.class)
public class JdbcTemplateTestApplication implements CommandLineRunner {

	@Autowired
	ArtistRepository artistRepository;
	
	@Autowired
	ArtistService artistService;
	
	@Autowired
	JdbcTemplate jdbcTemplate;   // only here for the SimpleJdbcInsert example below
	
	private String toggleGender(String gender) {
		if (gender.equals("male")) {
			return "female";
		} else {
			return "male";
		}
	}
	
	public void repositoryExample() {
		Artist artist = artistRepository.get(1);
		System.out.println(artist.toString());
		
		artist.setGender(toggleGender(artist.getGender()));
		artistRepository.save(artist);
		
		Artist newArtist = new Artist();
		newArtist.setName("Picasso, Pablo");
		newArtist.setGender("male");
		artistRepository.save(newArtist);
		
		System.out.println("All artists:");
		List<Artist> artists = artistRepository.findAll();
		for (Artist a : artists) {
			System.out.println(a.toString());
		}
		
		// Note getting the auto-generated id can be messy, particularly if you
		// want to keep your code database-agnostic. Here is one approach (note
		// you would add this into your Repository class

		Artist newArtist2 = new Artist();
		newArtist2.setName("Da Vinci, Leonardo");
		newArtist2.setGender("male");
		
		SimpleJdbcInsert insertArtist = new SimpleJdbcInsert(jdbcTemplate).withTableName("artists").usingGeneratedKeyColumns("id");
		Map<String, Object> parameters = new HashMap<String, Object>(2);
        parameters.put("fullName", newArtist2.getName());
        parameters.put("gender", newArtist2.getGender());
        Number newId = insertArtist.executeAndReturnKey(parameters);
        newArtist2.setId(newId.intValue());
        
        System.out.println("Generated id: " + newArtist2.getId());

		System.out.println("All artists (before delete):");
		List<Artist> artists2 = artistRepository.findAll();
		for (Artist a : artists2) {
			System.out.println(a.toString());
		}

		artistRepository.remove(newArtist2);
		
		System.out.println("All artists (after delete):");
		List<Artist> artists3 = artistRepository.findAll();
		for (Artist a : artists3) {
			System.out.println(a.toString());
		}

	}
	
	void serviceExampleWithAop() {
		Artist a1 = artistService.get(3);
		a1.setGender(toggleGender(a1.getGender()));
		artistService.save(a1);
		
		System.out.println("\nUpdated via service:\n" + a1.toString());
	}
	
	@Override
	public void run(String... arg0) throws Exception {	

		repositoryExample();

		serviceExampleWithAop();
	}
	
	public static void main(String[] args) {
        SpringApplication.run(JdbcTemplateTestApplication.class, args);
    }
}
