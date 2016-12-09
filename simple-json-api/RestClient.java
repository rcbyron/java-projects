public class RestClient {
	public static void main(String[] args) {
		// Items
		Item item1 = new Item("iPhone6", 699, 10);
		Item item2 = new Item("iPhone5", 399, 500);
		Client client = ClientBuilder.newClient();
		WebTarget targetAdd = client.target("http://localhost:15000").path("item/add");
		// Add items
		Item response1 = targetAdd.request(MediaType.APPLICATION_JSON_TYPE)
				.post(Entity.entity(item1, MediaType.APPLICATION_JSON), Item.class);
		Item response2 = targetAdd.request(MediaType.APPLICATION_JSON_TYPE)
				.post(Entity.entity(item2, MediaType.APPLICATION_JSON), Item.class);
		WebTarget targetGet = client.target("http://localhost:15000").path("item/get/iPhone6");
		// Get item by name
		Item item = targetGet.request(MediaType.APPLICATION_JSON_TYPE).get(new GenericType<Item>() {
		});
		System.out.println("Item: " + item);
	}
}