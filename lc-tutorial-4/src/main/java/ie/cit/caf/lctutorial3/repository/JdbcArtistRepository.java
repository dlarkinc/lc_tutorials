package ie.cit.caf.lctutorial3.repository;

import java.util.List;

import ie.cit.caf.lctutorial3.domain.Artist; 
import ie.cit.caf.lctutorial3.rowmapper.ArtistRowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcArtistRepository implements ArtistRepository {

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JdbcArtistRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public Artist get(int id) {
		String sql = "SELECT * FROM artists WHERE id = ?";
		Artist artist = jdbcTemplate.queryForObject(sql, new Object[] { id }, new ArtistRowMapper());
		return artist;
	}
	
	public void save(Artist artist) {
		if (artist.getId() != 0) {
			update(artist);
		} else {
			add(artist);
		}
	}
	
	private void add(Artist artist) {
		String sql = "INSERT INTO artists (fullName, gender) VALUES (?, ?)";
		jdbcTemplate.update(sql, new Object[] { artist.getName(), artist.getGender() } );
	}
	
	private void update(Artist artist) {
		String sql = "UPDATE artists SET fullName = ?, gender = ? WHERE id = ?";
		jdbcTemplate.update(sql, new Object[] { artist.getName(), artist.getGender(), artist.getId() } );
	}
	
	public List<Artist> findAll() {
		String sql = "SELECT * FROM artists";
		return jdbcTemplate.query(sql, new ArtistRowMapper());
	}

	@Override
	public void remove(Artist artist) {
		String sql = "DELETE FROM artists WHERE id = ?";
		jdbcTemplate.update(sql, new Object[] { artist.getId() } );
	}
}
