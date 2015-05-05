package ie.cit.caf.redis.domain;

public class Artist {

	private int id;
	
	private String gender;

	private String fullName;
	
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

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String name) {
		this.fullName = name;
	}

	@Override
	public String toString() {
		String out = "Artiste [id=" + id + ", gender=" + gender + ", fullName="
				+ fullName 
				+"]";
		return out;
	}
}
