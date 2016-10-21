import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread {
	    private Socket s;
		
		public ClientThread(Socket s) throws IOException {
			this.s = s;
			start();
		}

		public void run() {
			try {
				BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
				PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(s.getOutputStream())), true);
				
				out.println("Welcome! Enter your name please: ");
				String username = in.readLine();
				System.out.println(username + " has connected"); 
				out.println("Welcome " + username + ". Now, you can chat...");
				
				while (true) {
					String messageReceived = in.readLine();
					System.out.println(username + " says: " + messageReceived);
				}
	        } catch (IOException e) {
	            e.printStackTrace();
			}
		}
	}