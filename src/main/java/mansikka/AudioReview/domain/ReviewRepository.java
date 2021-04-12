package mansikka.AudioReview.domain;


import org.springframework.data.repository.CrudRepository;

import mansikka.AudioReview.model.Review;
public interface ReviewRepository extends CrudRepository<Review, Long> {
	Review save(Review review);
}
