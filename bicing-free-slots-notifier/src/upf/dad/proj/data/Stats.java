/** Stats.java
 * Represents the statistics of Bicing stations
 * @author Robert (Connor) Byron
 * Fall 2016
 */
package upf.dad.proj.data;

import upf.dad.proj.server.RestServer;

public class Stats {
	
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

	public Stats() {
		numStations = RestServer.getStations().getStations().size();
		avgNumBikes = RestServer.getStations().getAvgNumBikes();
		avgAltitude = RestServer.getStations().getAvgAltitude();
		openPercent = RestServer.getStations().getOpenPercent();
	}
	
}
