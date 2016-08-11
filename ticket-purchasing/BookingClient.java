import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BookingClient {

	private static final String[] names = {"Gimpy", "Lugz", "Rock-O", "Flay", "Zithro", "Torquisha", 
	                                       "Minser", "Lonnie", "Rich", "Lou Anne", "Rodrigo"};
	
	private static java.util.Random rand = new java.util.Random();
	public static int getRandomInt(int max) {
		return rand.nextInt(max);
	}
	
	public static void main(String[] args) throws InterruptedException {
		List<TicketOffice> offices = new ArrayList<TicketOffice>();
		
		int numClients = 0;
		for (int i = 0; i < 6; i++) {
			TicketOffice office = new TicketOffice();
			int lineSize = getRandomInt(400)+1000;
			System.out.println(lineSize);
			for (int j = 0; j < lineSize; j++) {
				numClients++;
				String randName = names[getRandomInt(names.length-1)] + " " + i + "-" + j;
				office.addClient(new Client(randName));
			}
			
			offices.add(office);
		}
		

		for (TicketOffice office : offices)
			office.start();
		
		// Wait for all threads to finish
		for (TicketOffice office : offices)
		    office.join();
		
		
//		ArrayList<Client> purchasers = new ArrayList<Client>();
//		System.out.println("Purchasers:");
//		for (Client c : TicketOffice.getProcessedClients())
//			if (c != null && c.seat() != -1)
//				purchasers.add(c);
//		Collections.sort(purchasers);
//		for (Client c : purchasers)
//			System.out.println(c);
//				
//		System.out.println("Unlucky Clients:");
//		for (Client c : TicketOffice.getProcessedClients())
//			if (c != null && c.seat() == -1)
//				System.out.println(c);
//		
//		System.out.println(numClients);
	}
	
}
