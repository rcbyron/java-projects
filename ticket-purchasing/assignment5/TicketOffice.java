/* TICKET-THREADING TicketOffice.java
 * EE422C Project 5 submission by
 * Robert (Connor) Byron
 * rcb2746
 * 76550
 * Slip days used: 1
 * Summer 2016
 */

package assignment5;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class TicketOffice extends Thread {

	private static final int THREAD_INTERVAL = 5; // in milliseconds

	private static ArrayList<Client> processedClients = new ArrayList<Client>();
	private static int[][] theater = new int[Theater.rows][Theater.cols];
	private static int nextOfficeId = 0;
	
	public static ArrayList<Client> getProcessedClients() { return processedClients; }
	
	private int officeId;
	private Queue<Client> clients;
	
	public TicketOffice() {
		// Give the office a new unique ID
		officeId = ++nextOfficeId;
		
		// Create an empty queue of clients
		clients = new LinkedList<Client>();
	}
	
	/* Add a client to the line (queue) */
	public void addClient(Client c) { clients.add(c); }
	
    /* Rows and seats start from 1 (not 0)
     * 
     * Algorithm:
     *     Repeat the following for each client in line until show is sold out
	 *          Seat <- find the bestAvailableSeat()
	 *          If there is an available seat
	 *              then markAvailableSeatTaken(Seat)
	 *              printTicketSeat(Seat)
	 *          Else 
	 *              Output to the screen “Sorry, we are sold out.”
	 *     End repeat
	 */
	@Override public void run() {
		// Run while seat is available (seat != -1) and has clients
		int seat = -1;
		do {
			if (clients.isEmpty()) {
				System.out.println("Office "+officeId+" - Finished serving clients.");
				return;
			}
			
			// Get next client and attempt to serve
			Client c = clients.remove();
			synchronized (TicketOffice.theater) {
				seat = bestAvailableSeat(Theater.rows, Theater.cols, c);
				
				// No seats available
				if (seat == -1) break;
				
				// Attempt to fill seat and print ticket
				if (!markAvailableSeatTaken(seat)) {
					c.setSeat(seat);
					printTicketSeat(seat);
				} else {
					// Due to synchronization, this should rarely (if ever) print
					System.out.println("Office "+officeId+" - Someone else already bought seat "+seat);
				}
			}
			processedClients.add(c);
			try { Thread.sleep(THREAD_INTERVAL); }
			catch (InterruptedException e) { e.printStackTrace(); }
		} while (seat != -1);
		
		System.out.println("Office "+officeId+" - Sorry, we are sold out.");
	}
	
	/* Returns the best available seat based on lowest row + seat value
	 * -1 if no seats are available
	 */
	private int bestAvailableSeat(int rows, int cols, Client client) {
		int bestSeatId = -1;
		int bestSeatVal = Integer.MAX_VALUE;
		int seatVal = 0;
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				seatVal = row + col;
				if (theater[row][col] == 0 && seatVal < bestSeatVal) {
					bestSeatId = (row * cols) + col;
					bestSeatVal = seatVal;
				}
			}
		}
		return bestSeatId;
	}
	
	/* Input: seat is the place of an available seat in the theater
	 * Output: true if seat is already taken, false otherwise */
	private boolean markAvailableSeatTaken(int seatId) {
		int row = (seatId / Theater.cols);
		int col = (seatId % Theater.cols);
		boolean alreadyTaken = theater[row][col] == 1;
		if (!alreadyTaken)
			theater[row][col] = 1;
		return alreadyTaken;
	}
	
	/* Input: seat is the location of an available seat in the theater
	 * Output: A ticket for that seat is printed to the screen. */
	private void printTicketSeat(int seatId) {
		int row = ((seatId / Theater.cols)+1);
		int col = ((seatId % Theater.cols)+1);
		System.out.println("Office "+officeId+" - Ticket purchased: row " + row + ", seat " + col);
	}
}
