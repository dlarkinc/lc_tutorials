package ie.cit.larkin.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ie.cit.larkin.form.ApplicantForm;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		String[] myStringArray = {"apple","banana","cauliflower"};
		
		model.addAttribute("serverTime", formattedDate );
		model.addAttribute("stringArray", myStringArray);
		
		return "home";
	}
	
	@RequestMapping(value = "/applicant", method = RequestMethod.GET)
	public String addApplicant(Model model) {
		model.addAttribute("applicantForm", new ApplicantForm());
		return "form";
	}

	@RequestMapping(value = "/applicant", method = RequestMethod.POST)
	public String newApplicant(@ModelAttribute("applicant") @Valid ApplicantForm applicantForm, BindingResult result, Model model) {
		
		if (result.hasErrors()) {
			return "form";
		}
		
		model.addAttribute("applicant", applicantForm);
		return "view";
	}
}
