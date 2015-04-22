package ie.cit.caf.larkin.cfdemo.controller;

import java.util.List;

import ie.cit.caf.larkin.cfdemo.entity.Artist;
import ie.cit.caf.larkin.cfdemo.service.ArtistService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/artist")
public class ArtistController {

	@Autowired
	ArtistService artistService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String index(Model model) {
		
		Iterable<Artist> artists = artistService.findAll();
		
		model.addAttribute("artists", artists);
		
		return "artist/index";
	}
	
	@RequestMapping(value="/name/like/{pattern}", method=RequestMethod.GET)
	public String artistByNameLike(@PathVariable String pattern, Model model) {
		
		List<Artist> artists = artistService.findByFullNameContainsIgnoreCase(pattern);
		
		model.addAttribute("artists", artists);
		
		return "artist/index";
	}
}
