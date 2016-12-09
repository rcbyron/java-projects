/** ServerServices.java
 * This class handles requests for general Bicing info
 * @author Robert (Connor) Byron
 * Fall 2016
 */
package upf.dad.proj.server;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import upf.dad.proj.data.Stations;
import upf.dad.proj.data.Stats;

@Path("/api")
public class ServerServices {

	/**
	 * This method returns the cached JSON info of Bicing stations
	 * e.g. - HTTP GET localhost:15000/api/getStations
	 * @returns a Stations object of all stations (converted to JSON)
	 */
	@GET
	@Path("/getStations")
	@Produces(MediaType.APPLICATION_JSON)
	public Stations getStations() {
		RestServer.logMsg("Showing stations: "+RestServer.getStations());
		return RestServer.getStations();
	}
	
	/**
	 * This method returns the current Bicing station stats
	 * e.g. - HTTP GET localhost:15000/api/getStats
	 * @returns a Stats object (converted to JSON)
	 */
	@GET
	@Path("/getStats")
	@Produces(MediaType.APPLICATION_JSON)
	public Stats getStats() {
		Stats stats = new Stats();
		RestServer.logMsg("Showing stats...");
		return stats;
	}

}