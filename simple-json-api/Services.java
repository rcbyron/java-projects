package upf.dad.s3.server;

@Path("/item")
public class Services {
	static List<Item> items = new ArrayList<Item>();

	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(Item item) {
		items.add(item);
		return Response.status(200).entity(item).build();
	}

	@GET
	@Path("/get/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public Item get(@PathParam("name") String name) {
		for (Item item : items) {
			if (item.getName().equalsIgnoreCase(name)) {
				return item;
			}
		}
		return null;
	}
	// Add method get all items
}