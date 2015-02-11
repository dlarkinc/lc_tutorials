package ie.cit.caf.lctutorial3;

import java.util.List;

import ie.cit.caf.lctutorial3.entity.Artist;
import ie.cit.caf.lctutorial3.repository.ArtisteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

@EnableAutoConfiguration
public class JpaRepositoryTestApplication implements CommandLineRunner {

	@Autowired
	ArtisteRepository artistRepository;
	
	public void query01() {
		System.out.println("\nQuery 1 (Print artist with id 1)\n------------------");
		
		// using inherited findOne method from CrudRepository
		Artist artist = artistRepository.findOne(1);

		System.out.println(artist.toString());
	}
	
	public void query02() {
		System.out.println("\nQuery 2 (Print male artists)\n------------------");
		
		List<Artist> artists = artistRepository.findByGender("male");
		for (Artist artist : artists) {
			System.out.println(artist.getFullName());
		}
	}
	
	public void query02a() {
		System.out.println("\nQuery 2 (Print male artists called Ackling, Roger)\n------------------");
		
		List<Artist> artists = artistRepository.findByFullNameAndGender("Ackling, Roger", "male");
		for (Artist artist : artists) {
			System.out.println(artist.getFullName());
		}
		
	}

	public void query03() {
		System.out.println("\nQuery 3 (Print artists with e in their name, ignoring case)\n------------------");
		
		List<Artist> artists = artistRepository.findByFullNameContains("e"); // will just get all artists with an e in their name
		
		for (Artist artist : artists) {
			// will have used eager loading to populate the movements
			System.out.println(artist.toString());
		}
	}
	
	public void query04() {
		System.out.println("\nQuery 4 (Change artist gender)\n------------------");
		
		Artist artist = artistRepository.findOne(1);
		System.out.println("Before: " + artist.toString());
		
		artist.setGender(changeGender(artist.getGender()));
		// using inherited save method from CrudRepository
		artistRepository.save(artist);

		artist = artistRepository.findOne(1);
		System.out.println("Before: " + artist.toString());
	}
	
	private String changeGender(String gender) {
		if (gender.equals("male")) {
			return "female";
		} else {
			return "male";
		}
	}
	
	public void query05() {
		System.out.println("\nQuery 5 (Get artists in movement 2 using join/subquery)\n------------------");
		
		List<Artist> artists = artistRepository.findByMovementsId(3);
		
		for (Artist artist : artists) {
			System.out.println(artist.toString());
		}
	}
	
	public void query06() {
		System.out.println("\nQuery 6 (Get artists in Conceptual Art movement using custom query with joins)\n------------------");
		
		List<Artist> artists = artistRepository.findByMovementsName("Conceptual Art");
		
		for (Artist artist : artists) {
			System.out.println(artist.toString());
		}
	}
	
	@Override
	public void run(String... arg0) throws Exception {
		query01();  // Get artist using JPA repo
		query02();  // Get artists by single value
		query02a(); // Get artists by multiple columns
		query03();  // Get artists by pattern matching on name
		query04();  // Get artist, make a change and use JPA repo to save it
		query05();  // Get artists by automatic subquery/join (in movement 2)
		query06();  // Get artists by custom query with join to movement "Conceptual Art"
	}
	
    public static void main(String[] args) {
        SpringApplication.run(JpaRepositoryTestApplication.class, args);
    }

}
