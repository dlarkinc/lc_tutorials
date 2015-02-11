package ie.cit.caf.domain;

import java.util.List;
import java.util.Set;

public class Artist extends Person {

	private int id;
	
	private int birthYear;
	
	private String gender;
	
	private List<Place> activePlaces;
	
	private List<Movement> movements;
	
	private String role;

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public List<Place> getActivePlaces() {
		return activePlaces;
	}

	public void setActivePlaces(List<Place> activePlaces) {
		this.activePlaces = activePlaces;
	}

	private Birth birth;
	
	public Birth getBirth() {
		return birth;
	}

	public void setBirth(Birth birth) {
		this.birth = birth;
	}

	public List<Movement> getMovements() {
		return movements;
	}

	public void setMovements(List<Movement> movements) {
		this.movements = movements;
	}

	public int getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}

	@Override
	public String toString() {
		String out = "Artist [id=" + id + ", name=" + getName() + ", birthYear=" + birthYear + ", gender="
				+ gender + ", activePlaces=" + activePlaces + ", movements=[";
		for (Movement m : movements) {
			out += m.toString() + ",";
		}
		out += "], " + movements + ", role=" + role + ", birth=" + birth + "]";
		return out;
	}


}
