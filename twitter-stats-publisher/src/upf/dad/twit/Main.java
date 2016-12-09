/** Main.java
 * This class schedules the twitter post stats job to run every 60 seconds
 * @author Robert (Connor) Byron
 * Fall 2016
 */
package upf.dad.twit;

import java.io.IOException;
import java.util.Date;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class Main {

	/** 
	 * Logs the message to the console/server with a timestamp
	 * @param  msg  a string of the log message
	 */
	public static void logMsg(String msg) {
		String timestamp = new java.text.SimpleDateFormat("[h:mm:ss] ").format(new Date());
		System.out.println(timestamp+msg);
	}

	/** 
	 * Starts the main program and schedules the twitter post stats job
	 * @param  args  (unused array of strings)
	 */
	public static void main(String[] args) throws IOException {
		logMsg("Twitter poster started...\n");

		// Set the job logging level to "ERROR" and
		// schedule the station updater for every 60 seconds
		System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "ERROR");
		JobDetail job = JobBuilder.newJob(TwitterPostStatsJob.class)
				.withIdentity("twitterPostStatsJob").build();
		Trigger trigger = TriggerBuilder.newTrigger()
				.withSchedule(SimpleScheduleBuilder.simpleSchedule()
				.withIntervalInSeconds(60).repeatForever()).build();

		// Schedule the twitter post stats job
		try {
			SchedulerFactory sf = new StdSchedulerFactory();
			Scheduler sched = sf.getScheduler();
			sched.start();
			sched.scheduleJob(job, trigger);
		} catch (SchedulerException e) {
			logMsg("Error running the twitter post stats job...");
			e.printStackTrace();
		}
	}

}