package ie.cit.caf.lctutorial3.domain;

import java.util.Collections;
import java.util.List;

public class Artist {

	public Artist() {
		movements = Collections.<Movement>emptyList();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	private int id;
	
	private String name;
		
	private String gender;
	
	private List<Movement> movements;
	
	public List<Movement> getMovements() {
		return movements;
	}

	public void setMovements(List<Movement> movements) {
		this.movements = movements;
	}
	
	@Override
	public String toString() {
		String out = "Artist [id=" + id + ", name=" + getName() + ", gender="
				+ gender + ", movements=[";
		for (Movement m : movements) {
			out += m.toString() + ",";
		}
		out += "]]";
		return out;
	}
}