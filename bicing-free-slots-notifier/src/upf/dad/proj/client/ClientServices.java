/** ClientServices.java
 * This class handles requests for specific Bicing client requests
 * @author Robert (Connor) Byron
 * Fall 2016
 */
package upf.dad.proj.client;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import upf.dad.proj.data.ClientNotFoundException;
import upf.dad.proj.data.ErrorMessage;
import upf.dad.proj.data.Message;
import upf.dad.proj.data.Station;
import upf.dad.proj.data.Subscription;
import upf.dad.proj.server.RestServer;

@Path("/api")
public class ClientServices {
	private static Client client = ClientBuilder.newClient();
	
	/**
	 * This method returns the subscribed clients info
	 * e.g. - HTTP GET localhost:15000/api/getClients
	 * @returns a List<Subcription> object of all subscriptions (converted to JSON)
	 */
	@GET
	@Path("/getClients")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Subscription> getClients() {
		RestServer.logMsg("Showing clients: "+RestServer.getSubscriptions());
		return RestServer.getSubscriptions();
	}
	
	/**
	 * Subscribes a client to be notified about the selected stations
	 * via Telegram to the desired client
	 * e.g. - HTTP POST localhost:15000/api/subscribe
	 *        Content-Type=application/json
	 *        Body={"phone": "6678675309", "telegramToken": "271973619", "stationIds": [5, 6, 10]}
	 * @param  subscription  Subscription object generated from the JSON in the body of the request
	 * @returns a Response object of the created subscription (converted to JSON)
	 */
	@POST
	@Path("/subscribe")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(Subscription subscription) {
		RestServer.logMsg("Adding subscription: "+subscription);
		RestServer.getSubscriptions().add(subscription);
		return Response.status(200).entity(subscription).build();
	}

	/**
	 * Triggers a notification of subscribed free slots & stations to be sent
	 * via Telegram to the desired client
	 * e.g. - HTTP POST localhost:15000/api/getStations?phone=6678675309
	 * @param  phone  This is a query param string of the subscribed phone number
	 * @returns a Stations object of all stations (converted to JSON)
	 */
	@POST
	@Path("/notify")
	@Produces(MediaType.APPLICATION_JSON)
	public String notify(@QueryParam("phone") String phone) {
		RestServer.logMsg("Notifying phone: "+phone);
		
		Subscription sub = RestServer.getSubscriptionByPhone(phone);
		if (sub == null)  // No subscription found given phone number
			throw new ClientNotFoundException(new ErrorMessage("phone number not found"));
		
		// Create the message of free slots & stations
		List<Station> stations = RestServer.getStations().getStationsById(sub.getStationIds());
		String msg = "";
		for (Station s : stations)
			msg += "Station at "+s.getStreetName()+" has "+s.getFreeSlots()+" available.\n";
		Message message = new Message(Long.parseLong(sub.getTelegramToken()), msg);
		
		// Send the message via Telegram
		WebTarget msgTarget = client.target("https://api.telegram.org").path("/bot272015078:AAHnh75jz86vCmy0-oBKzl6YZXZ-imjSdK0/sendMessage");
		return msgTarget.request().post(Entity.entity(message, MediaType.APPLICATION_JSON_TYPE), String.class);
	}

}