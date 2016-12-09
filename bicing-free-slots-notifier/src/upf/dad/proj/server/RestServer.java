/** RestServer.java
 * This class starts the main HTTP server for the BFSN RESTful API
 * @author Robert (Connor) Byron
 * Fall 2016
 */
package upf.dad.proj.server;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import upf.dad.proj.client.ClientServices;
import upf.dad.proj.data.Stations;
import upf.dad.proj.data.Subscription;

public class RestServer {
	
	public static Stations getStations() {
		return stations;
	}

	public static void setStations(Stations stations) {
		RestServer.stations = stations;
	}

	/**
	 * Finds and returns a subscription object based on a phone number
	 * @param  phone  a string of the subscribed phone number
	 * @return a Subscription object if found, null otherwise
	 * Fall 2016
	 */
	public static Subscription getSubscriptionByPhone(String phone) {
		for (Subscription s : subscriptions) {
			if (s.getPhone().equals(phone))
				return s;
		}
		return null;
	}
	
	public static List<Subscription> getSubscriptions() {
		return subscriptions;
	}

	public static void setSubscriptions(List<Subscription> subscriptions) {
		RestServer.subscriptions = subscriptions;
	}

	private static Stations stations = new Stations();
	private static List<Subscription> subscriptions = new ArrayList<Subscription>();
	
	/** 
	 * Logs the message to the console/server with a timestamp
	 * @param  msg  a string of the log message
	 */
	public static void logMsg(String msg) {
		String timestamp = new java.text.SimpleDateFormat("[h:mm:ss] ").format(new Date());
		System.out.println(timestamp+msg);
	}

	/** 
	 * Starts the main HTTP server and schedules the station updater job
	 * @param  args  (unused array of strings)
	 */
	public static void main(String[] args) throws IOException {
		// Start the main HTTP server
		URI baseUri = UriBuilder.fromUri("http://localhost/").port(15000).build();
		ResourceConfig config = new ResourceConfig(ServerServices.class, ClientServices.class);
		JdkHttpServerFactory.createHttpServer(baseUri, config);

		logMsg("Server started...\n");

		// Set the job logging level to "ERROR" and
		// schedule the station updater for every 60 seconds
		System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "ERROR");
		JobDetail job = JobBuilder.newJob(StationUpdaterJob.class)
				.withIdentity("updateStationsJob").build();
		Trigger trigger = TriggerBuilder.newTrigger()
				.withSchedule(SimpleScheduleBuilder.simpleSchedule()
				.withIntervalInSeconds(60).repeatForever()).build();
		// Schedule the station updater
		try {
			SchedulerFactory sf = new StdSchedulerFactory();
			Scheduler sched = sf.getScheduler();
			sched.start();
			sched.scheduleJob(job, trigger);
		} catch (SchedulerException e) {
			logMsg("Error running the station updater job...");
			e.printStackTrace();
		}
	}

}