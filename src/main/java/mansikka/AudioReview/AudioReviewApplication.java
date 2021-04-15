package mansikka.AudioReview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import mansikka.AudioReview.model.User;
import mansikka.AudioReview.model.Release;
import mansikka.AudioReview.model.Review;
import mansikka.AudioReview.domain.ReleaseRepository;
import mansikka.AudioReview.domain.ReviewRepository;
import mansikka.AudioReview.domain.UserRepository;

@SpringBootApplication
public class AudioReviewApplication {
	private static final Logger log = LoggerFactory.getLogger(AudioReviewApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AudioReviewApplication.class, args);
	}
	
	
	@Bean
	public CommandLineRunner AudioReviewDemo(ReleaseRepository rrepository, UserRepository urepository, ReviewRepository rwrepository) {
		return (args) -> {
			log.info("reset users");
			urepository.deleteAll();
			// Create users: admin/admin user/user
			log.info("create users: admin/admin & user/user");
			User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
			User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);
			
			log.info("reset releases");
			rrepository.deleteAll();
			Release rel1 = new Release("Sleepnet", "First Light", "https://open.spotify.com/album/6Qv02WJFMwHymSpJoXuuqt?si=BUn4AnC2TOe-02eNO7U2Qw");
			Release rel2 = new Release("Revocation", "The Outer Ones", "https://open.spotify.com/album/1Ela7sSi5MIp9HmEuLbCdY?si=Gza2bmeCSKCC6OKtZtjyew");
			Release rel3 = new Release("Burial", "Untrue", "https://open.spotify.com/album/1C30LhZB9I48LdpVCRRYvq?si=2J7cC8IKQ3Cqwr5SHovj2w");
			Release rel4 = new Release("Rivers of Nihil", "Where Owls Know My Name", "https://open.spotify.com/album/6Ue1iqByWrHTVFUkTpnm7D?si=AJ-VUpyyR3GERU0SqAJFag");
			
			
			rrepository.save(rel1);
			rrepository.save(rel2);
			rrepository.save(rel3);
			rrepository.save(rel4);
			
			log.info("reviews");
			Review rev1 = new Review("Good album!", 5, rel1);
			Review rev2 = new Review("Good album!", 10, rel1);
			Review rev3 = new Review("Good album!", 8, rel1);
			Review rev4 = new Review("Good album!", 7, rel1);
			List<Review> reviews = new ArrayList<Review>();
			reviews.add(rev1);
			reviews.add(rev2);
			reviews.add(rev3);
			reviews.add(rev4);
			
			rwrepository.save(rev1);
			rwrepository.save(rev2);
			rwrepository.save(rev3);
			rwrepository.save(rev4);
			
			log.info("fetch releases");
			for (Release release : rrepository.findAll()) {
				log.info(release.toString());
				
			}
		};
	}
}

