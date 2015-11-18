package ie.cit.larkin.mongoredis.entity;

import java.util.List;

import org.springframework.data.annotation.Id;

public class Artist extends Person {
	
	@Id
	private String id;
	
	private Integer birthYear;
	
	private String gender;

	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}

	public Integer getBirthYear() {
		return birthYear;
	}


	public void setBirthYear(Integer birthYear) {
		this.birthYear = birthYear;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}

	public List<Movement> getMovements() {
		return movements;
	}


	public void setMovements(List<Movement> movements) {
		this.movements = movements;
	}
	private List<Movement> movements;
	

	public String toString() {
		String artist = getName() + " (" + getBirthYear() + ")";
		for (Movement m : getMovements()) {
			artist += "-- " + m.getName(); //+ " (" + m.getEra().getName() + ")";
		}
//		artist += "Born: " + getBirth().getTime().getStartYear();
//		if (getBirth().getPlace() != null) {
//			artist += " (" + getBirth().getPlace().getName() + ")";
//		}
		return artist;
	}
}
