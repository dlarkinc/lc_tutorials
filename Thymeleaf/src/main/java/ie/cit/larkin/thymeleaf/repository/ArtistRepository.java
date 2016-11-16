package ie.cit.larkin.thymeleaf.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import ie.cit.larkin.thymeleaf.entity.Artist;

public interface ArtistRepository extends CrudRepository<Artist, Integer>{
	
	public List<Artist> findByGender(String gender); // SELECT * FROM artists WHERE gender = 'xxxxx'
	public List<Artist> findByFullNameAndGender(String string, String string2);

	public List<Artist> findByFullName(String fullName); // SELECT * FROM artists WHERE fullName LIKE '%xxxxx%'
	public List<Artist> findByFullNameContains(String fullName); // SELECT * FROM artists WHERE fullName LIKE '%xxxxx%'
	public List<Artist> findByFullNameContainsIgnoreCase(String fullName); // SELECT * FROM artists WHERE LOWER(fullName) LIKE LOWER('%xxxxx%')

	public List<Artist> findByMovementsId(int id);
	
	@Query("SELECT a FROM Artist a JOIN a.movements m WHERE m.name = :name")
	public List<Artist> findByMovementsName(@Param("name") String name);
	
	public int countByGender(String gender);
	
	@Query(value="Select * FROM artists WHERE id = :id", nativeQuery = true)
	public Artist findByIdNative(@Param("id") int id);
}
