package mansikka.AudioReview.domain;

import org.springframework.data.repository.CrudRepository;

import mansikka.AudioReview.model.User;
public interface UserRepository extends CrudRepository<User, Long> {
	User findByUsername(String username);
}
