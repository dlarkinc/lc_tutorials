package ie.cit.caf.rowmapper;

import ie.cit.caf.domain.Movement;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class MovementRowMapper implements RowMapper<Movement> {

	@Override
	public Movement mapRow(ResultSet rs, int index) throws SQLException {
		Movement movement = new Movement();
		
		movement.setId(rs.getInt("id"));
		movement.setName(rs.getString("name"));
		
		return movement;
	}
	
}
