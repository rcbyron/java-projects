import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.TreeMap;

public class TicketOffice extends Thread {

	private static final int[][] theater = new int[Theater.rows][Theater.cols];
	
	private static ArrayList<Client> processedClients = new ArrayList<Client>();
	private static int nextOfficeId = 0;
	
	public static ArrayList<Client> getProcessedClients() { return processedClients; }
	
	private int officeId;
	private Queue<Client> clients;
	
	public TicketOffice() {
		officeId = ++nextOfficeId;
		clients = new LinkedList<Client>();
	}
	
	public void addClient(Client c) { clients.add(c); }
	
	@Override
	public void run() {
//	    Repeat the following for each client in line until show is sold out
//		    Seat <- find the bestAvailableSeat()
//		    If there is an available seat
//		        then markAvailableSeatTaken(Seat)
//		        printTicketSeat(Seat)
//		    Else 
//		        Output to the screen “Sorry, we are sold out.”
//	    End repeat
//		Rows and seats start from 1 (not 0)
		int seat = -1;
		do {
			if (clients.isEmpty()) {
				System.out.println("Office "+officeId+" - Finished serving clients.");
				return;
			}
			Client c = clients.remove();
			synchronized (TicketOffice.theater) {
				seat = bestAvailableSeat(Theater.rows, Theater.cols, c);
				if (seat == -1) break;
				if (!markAvailableSeatTaken(seat)) {
					c.setSeat(seat);
					//printTicketSeat(seat);
				} else
					System.out.println("Office "+officeId+" - Someone else already bought seat "+seat);
			}
			processedClients.add(c);
			try { Thread.sleep(10); }
			catch (InterruptedException e) { e.printStackTrace(); }
		} while (seat != -1);
		
		System.out.println("Office "+officeId+" - Sorry, we are sold out.");
	}
	
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
	
	// Input: seat is the place of an available seat in the theater
	// Output: The place of the seat is marked as taken. 
	private boolean markAvailableSeatTaken(int seatId) {
		int row = (seatId / Theater.cols);
		int col = (seatId % Theater.cols);
		boolean alreadyTaken = theater[row][col] == 1;
		if (!alreadyTaken)
			theater[row][col] = 1;
		return alreadyTaken;
	}
	
	// Input: seat is the location of an available seat in the theater
	// Output: A ticket for that seat is printed to the screen – leave it on the screen long enough to be read easily by the client.
	// The output format is up to you, but should contain the essential information found on a theater ticket.  
	private void printTicketSeat(int seatId) {
		int row = ((seatId / Theater.cols)+1);
		int col = ((seatId % Theater.cols)+1);
		System.out.println("Office "+officeId+" - Ticket purchased: row " + row + ", seat " + col);
	}
}
