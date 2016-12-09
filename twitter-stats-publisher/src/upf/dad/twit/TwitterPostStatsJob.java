/** TwitterPostStatsJob.java
 * This class is run on a schedule updates the Bicing twitter status
 * @author Robert (Connor) Byron
 * Fall 2016
 */
package upf.dad.twit;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import upf.dad.twit.Stats;

public class TwitterPostStatsJob implements Job {
	
	private static Client client = ClientBuilder.newClient();
	private static TwitterFactory factory = new TwitterFactory();
	private static Twitter twitter = factory.getInstance();

	// Set the static twitter OAuth info
	static {
		twitter.setOAuthConsumer("CpA1obYGdnndaiiLLknv1AaOY", "LpfDdz3xUFbBHBTtuFdB3NtjkMiNm8gqRFMJtSYUhowcjOdvWt");
		twitter.setOAuthAccessToken(new AccessToken("801162337260544000-C6jrDU8XWW6xkdZM3sSnfvFcn68tgax",
				"sMogGdHczvutIjkz9AEhHqASQRJTQP4JKe62Igovz9zhz"));
	}
	
	/** 
	 * Executes the job to post a random status of Bicing stats to twitter
	 * @param  context  the JobExecutionContext used by quartz scheduler
	 * @throws JobExecutionException
	 */
	public void execute(JobExecutionContext context) throws JobExecutionException {
		try {
			// Fetch the statistics, and post a human-readable status to twitter
			WebTarget listedTarget = client.target("http://localhost:15000").path("api/getStats");
			Stats stats = listedTarget.request(MediaType.APPLICATION_JSON_TYPE).get(new GenericType<Stats>() {});
			twitter.updateStatus(stats.toString());
			Main.logMsg("Posted stats to twitter.");
		} catch (TwitterException e) {
			Main.logMsg("Failed to post stats to twitter.");
		} catch (ProcessingException e) {
			Main.logMsg("Failed to connect to BFSN.");
		} 
	}

}
