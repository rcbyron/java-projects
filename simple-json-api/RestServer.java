package upf.dad.s3.server;

import java.net.URI;
import com.sun.net.httpserver.HttpServer;
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory ;
import org.glassfish.jersey.server.ResourceConfig;

public class RestServer {
	
	public static void main(String[] args) throws IOException {
		URI baseUri = UriBuilder.fromUri("http://localhost/").port(4848).build();
		ResourceConfig config = new ResourceConfig(Services.class);
		HttpServer server = JdkHttpServerFactory.createHttpServer(baseUri, config);
		System.out.println("Server started...");
	}
	
}