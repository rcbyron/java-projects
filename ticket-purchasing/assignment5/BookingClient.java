/* TICKET-THREADING BookingClient.java
 * EE422C Project 5 submission by
 * Robert (Connor) Byron
 * rcb2746
 * 76550
 * Slip days used: 1
 * Summer 2016
 */
package assignment5;

import java.util.ArrayList;
import java.util.List;

public class BookingClient {

	private static final int NUM_OFFICES = 6;
	
	private static final String[] names = {"Gimpy", "Lugz", "Rock-O", "Flay", "Zithro", "Torquisha", 
	                                       "Minser", "Lonnie", "Rich", "Lou Anne", "Rodrigo"};
	
	private static java.util.Random rand = new java.util.Random();
	public static int getRandomInt(int max) {
		return rand.nextInt(max);
	}
	
	public static void main(String[] args) throws InterruptedException {
		List<TicketOffice> offices = new ArrayList<TicketOffice>();
		
		// Create offices with random client lines
		int numClients = 0;
		for (int i = 0; i < NUM_OFFICES; i++) {
			TicketOffice office = new TicketOffice();
			
			// Add random clients with random names
			int lineSize = getRandomInt(400)+1000;
			for (int j = 0; j < lineSize; j++) {
				numClients++;
				String randName = names[getRandomInt(names.length-1)] + " " + i + "-" + j;
				office.addClient(new Client(randName));
			} 
			
			offices.add(office);
		}
		
		// Start all threads
		for (TicketOffice office : offices)
			office.start();
		
		// Wait for all threads to finish
		for (TicketOffice office : offices)
		    office.join();
		
		// This section of code prints the purchases sorted by seat value
		/* ArrayList<Client> purchasers = new ArrayList<Client>();
		System.out.println("Purchasers:");
		for (Client c : TicketOffice.getProcessedClients())
			if (c != null && c.seat() != -1)
				purchasers.add(c);
		Collections.sort(purchasers);
		for (Client c : purchasers)
			System.out.println(c);
				
		System.out.println("Unlucky Clients:");
		for (Client c : TicketOffice.getProcessedClients())
			if (c != null && c.seat() == -1)
				System.out.println(c);*/
		
		System.out.println("Total Capacity: "+(Theater.rows*Theater.cols));
		System.out.println("Total Clients: "+numClients);
	}
	
}
