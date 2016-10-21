import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {

	private static final int PORT = 1234;
	
	public static void main(String[] args) throws IOException {
		@SuppressWarnings("resource")
		ServerSocket s = new ServerSocket(PORT);
		
		System.out.println("Server started. Waiting for connections...");
		while(true) {
	        Socket socket = s.accept();
	        new ClientThread(socket);
		}
	}
}