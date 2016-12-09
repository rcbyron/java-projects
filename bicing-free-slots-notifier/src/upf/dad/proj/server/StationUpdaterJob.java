/** StationUpdaterJob.java
 * This scheduled job updates the local Stations cache from the Bicing API
 * @author Robert (Connor) Byron
 * Fall 2016
 */
package upf.dad.proj.server;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import upf.dad.proj.data.Stations;

public class StationUpdaterJob implements Job {
	private static Client client = ClientBuilder.newClient();

	/**
	 * Executes the job to update the local Stations cache via the Bicing API
	 * @param context the JobExecutionContext used by quartz scheduler
	 * @throws JobExecutionException
	 */
	public void execute(JobExecutionContext context) throws JobExecutionException {
		WebTarget listedTarget = client.target("http://wservice.viabicing.cat").path("v2/stations");
		Stations s = listedTarget.request(MediaType.APPLICATION_JSON_TYPE).get(new GenericType<Stations>() { });
		RestServer.setStations(s);
		RestServer.logMsg("Updated stations.");
	}

}
