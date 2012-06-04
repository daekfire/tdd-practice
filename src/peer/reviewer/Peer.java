package peer.reviewer;

public class Peer {
	int ID;
	
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
}
