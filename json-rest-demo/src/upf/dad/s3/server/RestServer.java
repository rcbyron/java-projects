package upf.dad.s3.server;

import java.io.IOException;
import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import com.sun.net.httpserver.HttpServer;
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory ;
import org.glassfish.jersey.server.ResourceConfig;

@SuppressWarnings("restriction")
public class RestServer {
	
	public static void main(String[] args) throws IOException {
		URI baseUri = UriBuilder.fromUri("http://localhost/").port(15000).build();
		ResourceConfig config = new ResourceConfig(Services.class);
		@SuppressWarnings({ "unused" })
		HttpServer server = JdkHttpServerFactory.createHttpServer(baseUri, config);
		System.out.println("Server started...");
	}
	
}