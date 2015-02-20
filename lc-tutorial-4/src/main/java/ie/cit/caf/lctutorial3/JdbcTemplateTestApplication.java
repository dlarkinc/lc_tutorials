package ie.cit.caf.lctutorial3;

import ie.cit.caf.lctutorial3.domain.Artist;
import ie.cit.caf.lctutorial3.domain.Movement;
import ie.cit.caf.lctutorial3.repository.ArtistRepository;
import ie.cit.caf.lctutorial3.rowmapper.ArtistRowMapper;
import ie.cit.caf.lctutorial3.rowmapper.MovementRowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

@SpringBootApplication
public class JdbcTemplateTestApplication implements CommandLineRunner {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	ArtistRepository artistRepository;
	
	public void query01() {
		// Query for a list of maps with key-value pairs
		// The hard way!!!
		
		System.out.println("\nQuery 1 (List all artists using resultset Map)\n----------------");
		
		String sql = "SELECT * FROM artists";
		List<Map<String, Object>> resultSet = jdbcTemplate.queryForList(sql);
		
		for (Map<String, Object> row : resultSet) {
			System.out.println("Name: " + row.get("fullName"));
			System.out.println("ID: " + row.get("id"));
			System.out.println("Gender: " + row.get("gender") + "\n");
		}
	}
	
	public void query02() {
		// Query for a list of objects - automatic mapping from row to object using RowMapper class
		// Using parameterised "prepared statements" reduces the risk of a SQL inject attack
		
		System.out.println("\nQuery 2 (List male artists using RowMapper)\n-----------------");
		
		String sql = "SELECT * FROM artists WHERE gender = ?";
		List<Artist> artists = jdbcTemplate.query(sql, new Object[] { "male" }, new ArtistRowMapper());
		
		for (Artist artist : artists) {
			System.out.println(artist.toString());
		}
	}

	public void query03() {
		// Query for a specific object - automatic mapping from row to object using RowMapper class
		
		System.out.println("\nQuery 3 (Print artist with id 1 - uses RowMapper)\n------------------");
		
		String sql = "SELECT * FROM artists WHERE id = ?";
		Artist artist = jdbcTemplate.queryForObject(sql, new Object[] { 1 }, new ArtistRowMapper());
		
		System.out.println(artist.toString());
	}
	
	public void query04() {
		// Query for specific column values
		
		System.out.println("\nQuery 4 (Specfic columns)\n------------------");
		
		// Old version (now deprecated)
		String sql = "SELECT count(*) FROM artists";
		int artistCount = jdbcTemplate.queryForInt(sql);
		System.out.printf("Number of artists: %d\n", artistCount);
		
		// New way
		sql = "SELECT count(*) FROM movements";
		int movementCount = jdbcTemplate.queryForObject(sql, Integer.class);
		System.out.printf("Number of movements: %d\n", movementCount);
		
		// Getting a map of values
		sql = "SELECT fullName, gender FROM artists WHERE id = 1";
		Map<String, Object> map = jdbcTemplate.queryForMap(sql);
		System.out.printf("Name: %s, Gender: %s\n", map.get("fullName"), map.get("gender"));
	}
	
	public void query05() {
		// Specific artist and the artist's movements (many-to-many)
		// This is an inefficient version with 2 separate queries

		System.out.println("\nQuery 5 (Print artist and movements - 2 queries)\n------------------");

		String sql = "SELECT * FROM artists WHERE id = ?";
		Artist artist = jdbcTemplate.queryForObject(sql, new Object[] { 1 }, new ArtistRowMapper());

		sql = "SELECT m.* FROM movements m, artist_movements am WHERE m.id = am.movement_id AND am.artist_id = ?";
		List<Movement> movements = jdbcTemplate.query(sql, new Object[] { 1 }, new MovementRowMapper());
		
		artist.setMovements(movements);
		
		System.out.println(artist.toString());
	}
	
	public void query06() {
		// Specific artist and the artist's movements (many-to-many)
		// More efficient version with a single join query and
		// a ResultSetExtractor.... but it can get messy the more
		// complicated the join queries! The solution? ORM.

		System.out.println("\nQuery 6 (Print artist and movements - 1 join query)\n------------------");

		String sql = "SELECT a.id as artistid, a.fullName, a.gender, m.id as movementid, m.name " + 
				"FROM artists a, movements m, artist_movements am " + 
				"WHERE m.id = am.movement_id AND a.id = am.artist_id AND am.artist_id = ?";
		Artist artist = jdbcTemplate.query(sql, new Object[] { 1 }, 
				new ResultSetExtractor<Artist>() {

					@Override
					public Artist extractData(ResultSet rs)
							throws SQLException, DataAccessException {
						;
						Artist artist = null;
						List<Movement> movements = new ArrayList<>();
						
						while (rs.next()) {
							if (artist == null) {
								artist = new Artist();
								artist.setId(rs.getInt("artistid"));
								artist.setName(rs.getString("fullName"));
								artist.setGender(rs.getString("gender"));
							}
							Movement movement = new Movement();
							movement.setId(rs.getInt("movementid"));
							movement.setName(rs.getString("name"));
							movements.add(movement);
						}
						
						artist.setMovements(movements);
						return artist;
					}
				}
		);
		
		System.out.println(artist.toString());
	}
	
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
	
	@Override
	public void run(String... arg0) throws Exception {	

		repositoryExample();
		
//		query01();
//		query02();
//		query03();
//		query04();
//		query05();
//		query06();
	}
	
	public static void main(String[] args) {
        SpringApplication.run(JdbcTemplateTestApplication.class, args);
    }
}
