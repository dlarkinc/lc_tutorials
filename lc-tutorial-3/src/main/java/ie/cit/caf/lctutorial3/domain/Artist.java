package ie.cit.caf.lctutorial3.domain;

public class Artist {

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

	@Override
	public String toString() {
		 return "Artist [id=" + id + ", name=" + name +
			 ", gender="	+ gender + "]";
	}
}