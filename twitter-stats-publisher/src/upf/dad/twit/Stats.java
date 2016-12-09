/** Stats.java
 * This class represents the twitter stats JSON to be read in from the BFSN
 * @author Robert (Connor) Byron
 * Fall 2016
 */
package upf.dad.twit;

import java.util.Random;

public class Stats {

	private static Random rand = new Random();
	
	public int getNumStations() {
		return numStations;
	}

	public void setNumStations(int numStations) {
		this.numStations = numStations;
	}

	public int getAvgNumBikes() {
		return avgNumBikes;
	}

	public void setAvgNumBikes(int avgNumBikes) {
		this.avgNumBikes = avgNumBikes;
	}

	public int getAvgAltitude() {
		return avgAltitude;
	}

	public void setAvgAltitude(int avgAltitude) {
		this.avgAltitude = avgAltitude;
	}

	public String getOpenPercent() {
		return openPercent;
	}

	public void setOpenPercent(String openPercent) {
		this.openPercent = openPercent;
	}

	private int numStations;
	private int avgNumBikes;
	private int avgAltitude;
	private String openPercent;

	public Stats() { }
	
	/** 
	 * Returns a randomly selected twitter status based on BFSN stats
	 * @returns a random twitter status based on BFSN stats
	 */
	@Override
	public String toString() {
		switch(rand.nextInt(3)) {
		case 0:  return "There are "+avgNumBikes+" bikes at each station, on average.";
		case 1:  return "Currently, "+openPercent+"% of Bicing stations are open.";
		case 2:  return "The average altitude of stations is "+avgAltitude+" meters.";
		default: return "There are currently "+numStations+" Bicing stations.";
		}
	}
}
