package upf.dad.s3.server;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import upf.dad.s3.data.Item;

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
		throw new WebApplicationException(404);
	}
	
	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Item> list() {
		return items;
	}
}