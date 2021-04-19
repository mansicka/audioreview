package mansikka.AudioReview.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
	private ReleaseRepository releaserepository;

	@Autowired
	private ReviewRepository reviewrepository;

	@RequestMapping(value = "/releases", method = RequestMethod.GET)
	public String getAllReleases(Model model) {
		model.addAttribute("releases", releaserepository.findAll());
		return "releases"; // releases.html
	}

	@RequestMapping(value = "/addrelease")
	public String addRelease(Model model) {
		model.addAttribute("release", new Release());
		return "newrelease";// newrelease.html
	}

	@RequestMapping(value = "/saverls", method = RequestMethod.POST)
	public String saveRelease(Release release) {
		releaserepository.save(release);
		return "redirect:/releases";
	}

	@RequestMapping(value = "/deleterls/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')")
	public String deleteRelease(@PathVariable("id") Long releaseId, Model model) {
		releaserepository.deleteById(releaseId);
		return "redirect:/releases";
	}

	@RequestMapping(value = "/editrls/{id}", method = RequestMethod.GET)
	public String editRelease(@PathVariable("id") Long releaseId, Model model) {
		model.addAttribute("release", releaserepository.findById(releaseId));
		return "editrelease";// editrelease.html
	}

	 @RequestMapping(value = "/savereview", method = RequestMethod.POST)
	    public String saveReview(Review review, Release release) {
	        Long releaseId = release.getId();
	        review.setRelease(release);
	        reviewrepository.save(review);
	        System.out.println("save review:" + review.getComment());
	        return "redirect:release/" + releaseId;
	    }

	@RequestMapping(value = "/release/{id}", method = RequestMethod.GET)
	public String viewRelease(@PathVariable("id") Long releaseId, Optional<Release> release, Model model) {
		System.out.println(releaserepository.findAll());
		Optional<Release> singleRelease = releaserepository.findById(releaseId);
		System.out.println(singleRelease);
		List<Review> reviewList = singleRelease.get().getReviews();
		System.out.println(reviewList);
		if (releaseId != 0) {
			release = releaserepository.findById(releaseId);

			if (release.isPresent()) {
				// get spotify embbed code from url
				String uri = "https://open.spotify.com/embed/album/" + release.get().getUrl().substring(
						(release.get().getUrl().lastIndexOf("/") + 1), (release.get().getUrl().indexOf("?")));
				// pass attributes to model
				model.addAttribute("uri", uri);
				model.addAttribute("id", release.get().getId());
				model.addAttribute("artist", release.get().getArtist());
				model.addAttribute("title", release.get().getTitle());
				model.addAttribute("url", release.get().getUrl());
				model.addAttribute("reviews", reviewList);
				model.addAttribute("release", release.get());
				model.addAttribute("catno", release.get().getCatno());
				model.addAttribute("description",  release.get().getDescription());
				model.addAttribute("review", new Review());
				System.out.println("GET RELEASE: " + release.get());
				return "release"; // release.html
			}
			return "redirect:/releases";
		}
		return "redirect:/releases";
	}

	@RequestMapping(value = "/release/{id}", method = RequestMethod.POST)
	public String submitReview(@PathVariable("id") Long releaseId, Optional<Review> review, Model model) {
		System.out.println("aaaaa");
		return "redirect:/releases";
	}

	
	// miten lisätään arvostelu?
	public String newReview(Model model) {
		model.addAttribute("review", new Review());
		return "release";
	}

	@RequestMapping(value = "/login")
	public String login() {
		return "login"; // login.html
	}

}
