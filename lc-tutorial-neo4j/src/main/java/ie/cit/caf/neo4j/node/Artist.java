package ie.cit.caf.neo4j.node;

import java.util.HashSet;
import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

@NodeEntity
public class Artist {
	
	public Artist() {}
    public Artist(String name) { this.name = name; }
	
    @GraphId private Long id;
    
    @Indexed(unique = false)
	private String name;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
    
	@RelatedTo(type = "WORKED_WITH", direction=Direction.BOTH)
	private @Fetch Set<Artist> artists;
	
	public Set<Artist> getArtists() {
		return artists;
	}

	public void workedWith(Artist artist) {
		if (artists == null) {
			artists = new HashSet<Artist>();
		}
		artists.add(artist);
	}
	
	@RelatedTo(type = "PART_OF", direction=Direction.OUTGOING)
	private @Fetch Set<Movement> movements;
	
	public Set<Movement> getMovements() {
		return movements;
	}
	
	public void joined(Movement movement) {
		if (movements == null) {
			movements = new HashSet<Movement>();
		}
		movements.add(movement);
	}
	
	@Override
	public int hashCode() {
	    return id.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
	    if (obj instanceof Artist) {
	        Artist that = (Artist) obj;
	        return this.id.equals(that.id);
	    }
	    return false;
	}
}
