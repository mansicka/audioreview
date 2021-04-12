package mansikka.AudioReview.web;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import mansikka.AudioReview.domain.ReleaseRepository;
import mansikka.AudioReview.domain.ReviewRepository;
import mansikka.AudioReview.model.Release;
import mansikka.AudioReview.model.Review;

@Controller
public class ReleaseController {
	@Autowired
	private ReleaseRepository rrepository; 

	@Autowired
	private ReviewRepository rwrepository;

	@RequestMapping(value = "/releases", method = RequestMethod.GET)
	public String getAllReleases(Model model) {
		model.addAttribute("releases", rrepository.findAll());
		return "releases"; // releases.html 
	}
	@RequestMapping(value = "/addrelease")
	public String addRelease(Model model) {
		model.addAttribute("release", new Release());
		return "add" ;// add.html
	}

	@RequestMapping(value = "/saverls", method = RequestMethod.POST)
	public String saveRelease (Release release) {
		rrepository.save(release);
		return "redirect:/releases";
	}
	
	@RequestMapping(value = "/deleterls/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')")
	public String deleteRelease(@PathVariable("id") Long releaseId, Model model) {
		rrepository.deleteById(releaseId);
		return "redirect:/releases";                    
	}
	@RequestMapping(value = "/editrls/{id}", method = RequestMethod.GET)
	public String editRelease(@PathVariable("id") Long releaseId, Model model) {
			model.addAttribute("release", rrepository.findById(releaseId));
			return "redirect:/releases" ;// releases.html
		}
	@RequestMapping(value = "/savereview", method = RequestMethod.POST)
	public String saveReview(Review review) {
		rwrepository.save(review);
		return "release";
	}
	
	@RequestMapping(value= "/release/{id}", method = RequestMethod.GET.POST)
	
	public String viewRelease(@PathVariable("id") Long releaseId, Optional<Release> release, Model model) {
		try {
			if (releaseId != 0) {
				release = rrepository.findById(releaseId);

				if (release.isPresent()) {
					//get spotify embbed code from url
					String uri = "https://open.spotify.com/embed/album/" 
					+ release.get().getUrl().substring(
					(release.get().getUrl().lastIndexOf("/")+1),
					(release.get().getUrl().indexOf("?")));
					//pass attributes to model
					model.addAttribute("uri", uri);
					model.addAttribute("id", release.get().getId());
					model.addAttribute("artist", release.get().getArtist());
					model.addAttribute("title", release.get().getTitle());
					model.addAttribute("url", release.get().getUrl());
					
					return "release"; //release.html
				}
				return "redirect:/releases";
			}
			return "redirect:/releases";
		} catch (Exception e) {
			return "redirect:/releases";
		}
	
	}
		//miten lisätään arvostelu? 
	public String newReview(Model model) {
		model.addAttribute("review", new Review());
		return "release"; 
	}	
	
	
	@RequestMapping(value="/login")
	public String login() {	
	    return "login"; //login.html
	}	

	}

