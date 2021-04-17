package mansikka.AudioReview.model;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name= "releases")
public class Release {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String artist;
	private String title;
	private String url;
	private String catno;
	private String description;
	
	@OneToMany(mappedBy = "release", fetch = FetchType.EAGER)
	private List<Review> reviews;

	public Release() {}
	
	public Release(String artist, String title, String url, String catno, String description) {
		super();
		this.artist = artist;
		this.title = title;
		this.url = url;
		this.catno = catno;
		this.description = description;
	
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	public String getCatno() {
		return catno;
	}

	public void setCatno(String catno) {
		this.catno = catno;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String descrption) {
		this.description = descrption;
	}


	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
	@Override
	public String toString() {
		for(int i=0; i < reviews.size(); i++) {
			System.out.println(reviews.get(i));
		}
		return "";
	}

	
}
