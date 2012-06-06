package peer.reviewer;

public class Peer {
	int ID;
	int Mark;
	double AverageRating;
	
	public Peer() {
		ID = -1;
	}
	
	public int getID() {		
		return ID;
	}
	
	public void setID(int ID) {
		if(this.ID == -1)
			this.ID = ID;
	}
	
	public int getMark() {
		return this.Mark;
	}
	
	public void setMark(int mark) {
		this.Mark = mark;
	}
	
	public void setAverage(double average) {
		AverageRating = average;
	}
	
	public double getAverage() {
		return this.AverageRating;
	}
}
