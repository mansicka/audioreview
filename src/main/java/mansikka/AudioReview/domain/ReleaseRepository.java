package mansikka.AudioReview.domain;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import mansikka.AudioReview.model.Release;
import mansikka.AudioReview.model.Review;
public interface ReleaseRepository extends CrudRepository<Release, Long> {
	Optional<Release> findById(Long releaseid);
	
	
}
