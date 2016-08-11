/* TICKET-THREADING TicketOffice.java
 * EE422C Project 5 submission by
 * Robert (Connor) Byron
 * rcb2746
 * 76550
 * Slip days used: 1
 * Summer 2016
 */

package assignment5;

public class Client implements Comparable<Client> {

	private String name;
	private int seat;
	
	public Client(String clientName) {
		name = clientName;
		seat = -1;
	}
	
	public String name() { return name; }
	public int    seat() { return seat; }
	public int    val()  { return seat / Theater.cols + seat % Theater.cols; }
	
	public void setSeat(int seatId) { seat = seatId; }
	
	@Override
	public String toString() {
		int row = ((seat / Theater.cols)+1);
		int col = ((seat % Theater.cols)+1);
		return val() +":"+ seat +":" + row + "," + col + " - " + name;
	}

	@Override
	public int compareTo(Client arg0) {
		// Sort based on seat value
		return val() - arg0.val();
		
		// Sort based on seat id
		//return seat - arg0.seat();
	}
	
}
