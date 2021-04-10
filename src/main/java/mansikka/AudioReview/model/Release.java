package mansikka.AudioReview.model;
import java.util.ArrayList;
import java.util.Date;
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
	
	@Column(name="artist")
	private String artist;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "url")
	private String url;
	
	@OneToMany(mappedBy = "release")
	private List<Review> reviews;

	public Release() {}
	
	public Release(String artist, String title, String url) {
		super();
		this.artist = artist;
		this.title = title;
		this.url = url;
	
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

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
	@Override
	public String toString() {
		
		return "Release: id: " + id + ": " + title + " by " + artist + ", url: " + url  ;
	}

	
}
