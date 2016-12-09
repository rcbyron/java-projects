package upf.dad.s3.client;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import upf.dad.s3.data.Item;

public class RestClient {
	public static void main(String[] args) {
		System.out.println("Client started...\n");
		// Items
		Item item1 = new Item("iPhone6", 699, 10);
		Item item2 = new Item("iPhone5", 399, 500);
		Item item3 = new Item("Corona", 3, 9001);
		Item item4 = new Item("Lime", 1, 23);
		Client client = ClientBuilder.newClient();
		WebTarget targetAdd = client.target("http://localhost:15000").path("item/add");
		
		// Add items
		@SuppressWarnings("unused")
		Item response1 = targetAdd.request(MediaType.APPLICATION_JSON_TYPE)
				.post(Entity.entity(item1, MediaType.APPLICATION_JSON), Item.class);
		@SuppressWarnings("unused")
		Item response2 = targetAdd.request(MediaType.APPLICATION_JSON_TYPE)
				.post(Entity.entity(item2, MediaType.APPLICATION_JSON), Item.class);
		@SuppressWarnings("unused")
		Item response3 = targetAdd.request(MediaType.APPLICATION_JSON_TYPE)
				.post(Entity.entity(item3, MediaType.APPLICATION_JSON), Item.class);
		@SuppressWarnings("unused")
		Item response4 = targetAdd.request(MediaType.APPLICATION_JSON_TYPE)
				.post(Entity.entity(item4, MediaType.APPLICATION_JSON), Item.class);
		WebTarget targetGet1 = client.target("http://localhost:15000").path("item/get/iPhone6");
		WebTarget targetGet2 = client.target("http://localhost:15000").path("item/get/iPhone5");
		WebTarget targetGet3 = client.target("http://localhost:15000").path("item/get/Corona");
		WebTarget targetGet4 = client.target("http://localhost:15000").path("item/get/Lime");
		
		// Get item by name
		Item item11 = targetGet1.request(MediaType.APPLICATION_JSON_TYPE).get(new GenericType<Item>() {
		});
		System.out.println("Added Item: " + item11);
		Item item22 = targetGet2.request(MediaType.APPLICATION_JSON_TYPE).get(new GenericType<Item>() {
		});
		System.out.println("Added Item: " + item22);
		Item item33 = targetGet3.request(MediaType.APPLICATION_JSON_TYPE).get(new GenericType<Item>() {
		});
		System.out.println("Added Item: " + item33);
		Item item44 = targetGet4.request(MediaType.APPLICATION_JSON_TYPE).get(new GenericType<Item>() {
		});
		System.out.println("Added Item: " + item44);
		
		// List all items
		WebTarget listedTarget = client.target("http://localhost:15000").path("item/list");
		List<Item> listedItems = listedTarget.request(MediaType.APPLICATION_JSON_TYPE).get(new GenericType<List<Item>>() {
		});
		System.out.println("\nList of All Items: " + listedItems);
		
		// Check invalid item
		WebTarget notFoundTarget = client.target("http://localhost:15000").path("item/get/thisisnotanitemyet");
		Response nfResp = notFoundTarget.request(MediaType.APPLICATION_JSON_TYPE).get(new GenericType<Response>() {
		});
		System.out.println("\nTest 'Not Found' Response: " + nfResp);
		
		System.out.println("\nClient terminated.");
	}
}