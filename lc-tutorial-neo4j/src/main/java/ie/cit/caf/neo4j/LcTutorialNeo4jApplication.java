package ie.cit.caf.neo4j;

import java.util.ArrayList;
import java.util.List;

import ie.cit.caf.neo4j.node.Artist;
import ie.cit.caf.neo4j.node.Movement;
import ie.cit.caf.neo4j.repository.ArtistRepository;
import ie.cit.caf.neo4j.repository.MovementRepository;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.config.Neo4jConfiguration;

@SpringBootApplication
@EnableNeo4jRepositories(basePackages = "ie.cit.caf.neo4j.repository")
public class LcTutorialNeo4jApplication extends Neo4jConfiguration implements CommandLineRunner {

    public LcTutorialNeo4jApplication() {
    	setBasePackage("ie.cit.caf.neo4j");
	}

	@Bean(destroyMethod = "shutdown")
    GraphDatabaseService graphDatabaseService() {
    	return new GraphDatabaseFactory().newEmbeddedDatabase("C:\\Users\\larkin.cunningham\\Documents\\Neo4j\\caf2015.graph");
    }
    
    @Autowired
    ArtistRepository artistRepository;
	
    @Autowired
    MovementRepository movementRepository;
    
    public static void main(String[] args) {
        SpringApplication.run(LcTutorialNeo4jApplication.class, args);
    }

	@Override
	public void run(String... arg0) throws Exception {
		
		Artist a = new Artist("John Lennon");
		artistRepository.save(a);
		
		Artist b = new Artist("Paul McCartney");
		artistRepository.save(b);
		
		Movement m = new Movement("Pop");
		movementRepository.save(m);
		
		a.joined(m);
		b.joined(m);
		
		a.workedWith(b);
		
		artistRepository.save(a);
		artistRepository.save(b);
	}
}
