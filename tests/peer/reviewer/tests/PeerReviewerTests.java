package peer.reviewer.tests;

import org.junit.Before;
import org.junit.Test;

import peer.reviewer.Peer;
import peer.reviewer.PeerReviewer;
import peer.reviewer.Rating;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class PeerReviewerTests {
	PeerReviewer test;
	
	@Before
	public void setUpPeerReviewer() {
		test = new PeerReviewer();
	}
	
	@Test
	public void testAddPeer() {		
		test.add(new Peer());
		assertEquals("Check added peer", 1, test.getPeerCount());
		test.add(new Peer());
		assertEquals("Check added second peer", 2, test.getPeerCount());
		assertEquals("Check added peer ID", 1, test.getPeer(1).getID());
		assertEquals("Check added peer ID", 0, test.getPeer(0).getID());
		test.getPeer(1).setID(0);
		assertFalse(test.getPeer(1).getID() == 0);
	}
	
	@Test
	public void testMakeRating() {
		test.add(new Peer());
		test.add(new Peer());
		
		//Rating tests - should be moved to seperate Rating tests
		Rating rating = new Rating(0,1,5);
		assertEquals("Rating test", rating.getRating(), 5);
		assertEquals("Rating test rater", rating.getRater(), 0);
		assertEquals("Rating test ratee", rating.getRatee(), 1);
			
		test.addRating(0,1,5);
		assertEquals("First rating recorded",5,test.getRating(0,1));
		test.addRating(1,0,1);
		assertEquals("Second rating recorded",1,test.getRating(1,0)); 
		
		//Try to overwrite rating - should fail
		test.addRating(0,1,3);
		assertEquals("First rating overwrite not recorded",5,test.getRating(0,1));
		
	}
	
	@Test
	public void testGiveGroupMark() {
		test.setGroupMark(50);
		assertEquals("Test group mark setting", 50, test.getGroupMark());
		test.setGroupMark(100);
		assertEquals("Test group mark setting", 100, test.getGroupMark());
	}
	
	@Test
	public void testGiveGroupMarkBoundaries() {
		test.setGroupMarkBoundaries(0,100);
		assertEquals("Test group mark min", 0, test.getGroupMarkMin());
		assertEquals("Test group mark max", 100, test.getGroupMarkMax());
	}
	
	@Test
	public void testSimpleExampleMarks() {
		test.add(new Peer());
		test.add(new Peer());
		test.addRating(0,1,5);
		test.addRating(1,0,5);
		test.setGroupMark(50);
		test.setGroupMarkBoundaries(0,100);
		test.setMarkVariance(10);
		test.assignMarks();
		assertEquals("Test basic mark allocation", test.getPeer(0).getMark(), test.getPeer(1).getMark());
		assertEquals("Test mark is the mark given 0", 50, test.getPeer(0).getMark());
		assertEquals("Test mark is the mark given 1", 50, test.getPeer(1).getMark());
	}
	
	@Test
	public void test3WaySplits1() {
		test.add(new Peer());
		test.add(new Peer());
		test.add(new Peer());
		test.addRating(0,1,5);
		test.addRating(0,2,5);
		test.addRating(1,0,5);
		test.addRating(1,2,5);		
		test.addRating(2,0,5);
		test.addRating(2,1,5);
		test.setGroupMark(50);
		test.setGroupMarkBoundaries(0,100);
		test.setMarkVariance(10);
		test.assignMarks();
		assertTrue((test.getPeer(0).getMark() == test.getPeer(1).getMark()) 
					&& (test.getPeer(0).getMark() == test.getPeer(2).getMark())
					&& (test.getPeer(1).getMark() == test.getPeer(2).getMark()));
		assertEquals("Test mark is the mark given 0", 50, test.getPeer(0).getMark());
		assertEquals("Test mark is the mark given 1", 50, test.getPeer(1).getMark());
		assertEquals("Test mark is the mark given 2", 50, test.getPeer(2).getMark());

	}
	
	@Test
	public void testAdjustedExampleMarks() {
		test.add(new Peer());
		test.add(new Peer());
		test.addRating(0,1,3);
		test.addRating(1,0,5);
		test.setGroupMark(50);
		test.setGroupMarkBoundaries(0,100);
		test.setMarkVariance(10);
		test.assignMarks();
		assertEquals("Test average marks = group mark", 50, ((test.getPeer(0).getMark() + test.getPeer(1).getMark()) / 2));
		assertEquals("Test mark is the mark given 0", 60, test.getPeer(0).getMark());
		assertEquals("Test mark is the mark given 1", 40, test.getPeer(1).getMark());
	}
	
	@Test
	public void test3WaySplits2() {
		test.add(new Peer());
		test.add(new Peer());
		test.add(new Peer());
		test.addRating(0,1,3);		
		test.addRating(0,2,4);
		test.addRating(1,0,5);
		test.addRating(1,2,4);
		test.addRating(2,0,5);
		test.addRating(2,1,3);	
		test.setGroupMark(50);
		test.setGroupMarkBoundaries(0,100);
		test.setMarkVariance(10);
		test.assignMarks();
		assertEquals("Test average marks = group mark", 50, 
				((test.getPeer(0).getMark() + test.getPeer(1).getMark() + test.getPeer(2).getMark()) / 3));
		assertEquals("Test mark is the mark given 0", 60, test.getPeer(0).getMark());
		assertEquals("Test mark is the mark given 1", 40, test.getPeer(1).getMark());
		assertEquals("Test mark is the mark given 2", 50, test.getPeer(2).getMark());

	}
	
	@Test
	public void test3WaySplits3() {
		test.add(new Peer());
		test.add(new Peer());
		test.add(new Peer());
		test.addRating(0,1,3);		
		test.addRating(0,2,4);
		test.addRating(1,0,5);
		test.addRating(1,2,5);
		test.addRating(2,0,5);
		test.addRating(2,1,2);	
		test.setGroupMark(50);
		test.setGroupMarkBoundaries(0,100);
		test.setMarkVariance(10);
		test.assignMarks();
		assertEquals("Test average marks = group mark", 50, 
				((test.getPeer(0).getMark() + test.getPeer(1).getMark() + test.getPeer(2).getMark()) / 3));
		assertEquals("Test mark is the mark given 0", 60, test.getPeer(0).getMark());
		assertEquals("Test mark is the mark given 1", 35, test.getPeer(1).getMark());
		assertEquals("Test mark is the mark given 2", 55, test.getPeer(2).getMark());

	}
}
