package mansikka.AudioReview.domain;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import mansikka.AudioReview.model.Release;
public interface ReleaseRepository extends CrudRepository<Release, Long> {
	
}
