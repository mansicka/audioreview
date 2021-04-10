package mansikka.AudioReview.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "reviews") 
public class Review {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String comment;
	private int score;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	
	public Review() {}
	
	public Review(String comment, int score) {
		super();
		this.comment = comment;
		this.score = score;
	
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}


	@Override
	public String toString() {
		
		return comment  ;
	}
}