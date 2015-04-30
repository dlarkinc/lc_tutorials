package ie.cit.caf.neo4j.node;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;

@NodeEntity
public class Movement {
	public Movement() {
		super();
	}
	
	public Movement(String name) {
		this.name = name;
	}

	@GraphId
	private Long id;
	
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

	@Override
	public int hashCode() {
	    return id.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
	    if (obj instanceof Movement) {
	        Movement that = (Movement) obj;
	        return this.id.equals(that.id);
	    }
	    return false;
	}
}
