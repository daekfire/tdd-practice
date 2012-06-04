package peer.reviewer;

public class Rating {
	private int rater;
	private int ratee;
	private int rating;
	
	public Rating(int rater, int ratee, int rating) {
		this.rater = rater;
		this.ratee = ratee;
		this.rating = rating;
	}

	public int getRater() {
		return rater;
	}

	private void setRater(int rater) {
		this.rater = rater;
	}

	public int getRatee() {
		return ratee;
	}

	private void setRatee(int ratee) {
		this.ratee = ratee;
	}

	public int getRating() {
		return rating;
	}

	private void setRating(int rating) {
		this.rating = rating;
	}
}
