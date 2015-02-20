package ie.cit.caf.lctutorial3.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ie.cit.caf.lctutorial3.domain.Artist;

public class ArtistRowMapper implements RowMapper<Artist> {

	@Override
	public Artist mapRow(ResultSet rs, int index) throws SQLException {
		Artist artist = new Artist();
		
		artist.setId(rs.getInt("id"));
		artist.setName(rs.getString("fullName"));
		artist.setGender(rs.getString("gender"));
		
		return artist;
	}
}
