package ie.cit.caf.lctutorial3.entity;

import ie.cit.caf.lctutorial3.entity.Movement;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="artists")
public class Artist {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String gender;

	@Column(name="fullName")

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="artist_movements",
			joinColumns={@JoinColumn(name="artist_id", referencedColumnName="id")},
			inverseJoinColumns={@JoinColumn(name="movement_id", referencedColumnName="id")})
	public List<Movement> movements;
	
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

	public List<Movement> getMovements() {
		return movements;
	}

	public void setMovements(List<Movement> movements) {
		this.movements = movements;
	}

	@Override
	public String toString() {
		String out = "Artiste [id=" + id + ", gender=" + gender + ", fullName="
				+ fullName + ", movements="; 
		for (Movement m : movements) {
			out += m.toString() + ",";
		}
		out += "]]";
		return out;
	}
}
