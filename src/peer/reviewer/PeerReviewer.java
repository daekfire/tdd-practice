package peer.reviewer;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.lang.Math;


public class PeerReviewer {
	List<Peer> Peers;
	int LastID;
	List<Rating> Ratings;
	int GroupMark;
	int MinMark;
	int MaxMark;
	int MarkVariance;
	
	public PeerReviewer () {
		Peers = new ArrayList<Peer>();
		LastID = 0;
		Ratings = new ArrayList<Rating>();
		GroupMark = -1;
		MinMark = -1;
		MaxMark = -1;
		MarkVariance = -1;
	}
	
	public void add(Peer p) {
		Peers.add(p);
		p.setID(LastID++);
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
	
	public void assignMarks() {
		double baseline = calculateAverages();
		for(Peer p : Peers) {
			double diff = p.getAverage() - baseline;
			double workingMark = GroupMark + (MarkVariance * diff);			
			p.setMark((int)workingMark);
		}
			
	}
	
	public void setMarkVariance(int variance) {
		this.MarkVariance = variance;
	}
	
	private int findRating(int rater, int ratee) {
		for(int i = 0; i < Ratings.size(); i++) {
			Rating temp = Ratings.get(i);
			if(temp.getRater() == rater && temp.getRatee() == ratee)
				return temp.getRating();
		}
		return -1; //failure
	}
	
	private double calculateAverages() {
		List<Double> averageList = new ArrayList<Double>();
		for(int i = 0; i < Peers.size(); i++) {
			List<Integer> tempList = new ArrayList<Integer>();
			for(int j = 0; j < Ratings.size(); j++) {
				Rating temp = Ratings.get(j);
				if(temp.getRatee() == i) 
					tempList.add(temp.getRating());				
			}
			double total = 0;
			for(Integer num : tempList) 
				total += num;
			double average = total / tempList.size();			
			Peers.get(i).setAverage(average);
			averageList.add(average);
		}
		double total = 0.0;
		for(Double d : averageList)
			total += d;
		return total / averageList.size();
	}
	
}
