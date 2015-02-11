package ie.cit.caf.rowmapper;

import ie.cit.caf.domain.Artist;
import ie.cit.caf.domain.Movement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;

import org.springframework.jdbc.core.RowMapper;

public class ArtistRowMapper implements RowMapper<Artist> {

	@Override
	public Artist mapRow(ResultSet rs, int index) throws SQLException {
		Artist artist = new Artist();
		
		artist.setId(rs.getInt("id"));
		artist.setName(rs.getString("fullName"));
		artist.setGender(rs.getString("gender"));
		artist.setMovements(Collections.<Movement> emptyList());
		
		return artist;
	}
	
}
