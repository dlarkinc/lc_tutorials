package ie.cit.caf.lctutorial3;

import ie.cit.caf.lctutorial3.domain.Artist;
import ie.cit.caf.lctutorial3.rowmapper.ArtistRowMapper;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class JdbcTemplateTestApplication implements CommandLineRunner {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
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
	
	@Override
	public void run(String... arg0) throws Exception {	
		query01();
		query02();
		query03();
	}
	
	public static void main(String[] args) {
        SpringApplication.run(JdbcTemplateTestApplication.class, args);
    }
}
