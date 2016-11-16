package ie.cit.larkin.thymeleaf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ie.cit.larkin.thymeleaf.entity.Artist;
import ie.cit.larkin.thymeleaf.repository.ArtistRepository;


/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/artist")
public class ArtistController {

	@Autowired
	ArtistRepository artistRepository;

	@RequestMapping("/")
	public String list(Model model) {
		
		Iterable<Artist> a= artistRepository.findAll();
		
		model.addAttribute("artists", a);
		
		return "artist/list";
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String view(Model model, @PathVariable("id") int id) {
		
		Artist a= artistRepository.findOne(id);
		
		model.addAttribute("artist", a);
		
		return "artist/view";
	}
	
}
