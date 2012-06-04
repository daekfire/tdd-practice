package peer.reviewer;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;


public class PeerReviewer {
	List<Peer> Peers;
	int lastID;
	List<Rating> Ratings;
	int GroupMark;
	int MinMark;
	int MaxMark;
	
	public PeerReviewer () {
		Peers = new ArrayList<Peer>();
		lastID = 0;
		Ratings = new ArrayList<Rating>();
		GroupMark = -1;
		MinMark = -1;
		MaxMark = -1;
	}
	
	public void add(Peer p) {
		Peers.add(p);
		p.setID(lastID++);
	}
	
	public int getPeerCount() {		
		return Peers.size();
	}
	
	public Peer getPeer(int id) {
		return Peers.get(id);
	}
	
	public void addRating(int rater, int ratee, int rating) {
		if(findRating(rater,ratee) == -1)
			Ratings.add(new Rating(rater,ratee,rating));
	}
	
	public int getRating(int rater, int ratee) {
		return findRating(rater,ratee);
	}
	
	public void setGroupMark(int mark) {
		GroupMark = mark;
	}
	
	public int getGroupMark() {
		return GroupMark;
		
	}
	
	public void setGroupMarkBoundaries(int min, int max) {
		this.MinMark = min;
		this.MaxMark = max;
	}
	
	public int getGroupMarkMin() {
		return this.MinMark;
	}
	
	public int getGroupMarkMax() {
		return this.MaxMark;
	}
	
	private int findRating(int rater, int ratee) {
		for(int i = 0; i < Ratings.size(); i++) {
			Rating temp = Ratings.get(i);
			if(temp.getRater() == rater && temp.getRatee() == ratee)
				return temp.getRating();
		}
		return -1; //failure
	}
}
