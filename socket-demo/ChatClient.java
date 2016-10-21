import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class ChatClient {

	private static final String ADDRESS = "localhost";
	private static final int PORT = 1234;
	
	public static void main(String[] args) throws Exception {
		Socket socket = new Socket(InetAddress.getByName(ADDRESS), PORT);
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
			
			BufferedReader myInput = new BufferedReader(new InputStreamReader(System.in));
			try {
				System.out.println(in.readLine());
				String username = myInput.readLine();
				out.println(username);
				System.out.println(in.readLine());
				
			    while(true) {
			    	String messageToSend = myInput.readLine();
			    	out.println(messageToSend);
			    }
		    } catch (IOException e) {
		    	e.printStackTrace();
		    }
		} finally {
		    socket.close();
		}
	}
	
}
