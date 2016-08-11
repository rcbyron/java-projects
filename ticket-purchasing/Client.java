
public class Client implements Comparable<Client> {

	private String name;
	private int seat;
	
	public Client(String clientName) {
		name = clientName;
		seat = -1;
	}
	
	public String name() { return name; }
	public int    seat() { return seat; }
	
	public void setSeat(int seatId) { seat = seatId; }
	
	@Override
	public String toString() { return val() +":"+ seat +":" + (seat / Theater.cols) + "," + (seat % Theater.cols) + " - " + name; }

	@Override
	public int compareTo(Client arg0) {
		return val() - arg0.val();
		//return seat / Theater.cols - arg0.seat() / Theater.cols;
		//return seat - arg0.seat();
	}
	
	private int val() { return seat / Theater.cols + seat % Theater.cols; }
	
}
