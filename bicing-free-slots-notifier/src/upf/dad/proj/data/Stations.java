/** Stations.java
 * Represents a group of Stations and provides station statistic methods
 * @author Robert (Connor) Byron
 * Fall 2016
 */
package upf.dad.proj.data;

import java.util.ArrayList;
import java.util.List;

public class Stations {
	/** 
	 * Gets the average number of bikes at each station
	 * @return nearest int of the average number of bikes at each station
	 */
	public int getAvgNumBikes() {
		int totalBikes = 0;
		for (Station s : stations)
			totalBikes += Integer.parseInt(s.getBikes());
		return totalBikes / stations.size();
	}

	/** 
	 * Gets the average altitude of each station
	 * @return nearest int of the average altitude of each station
	 */
	public int getAvgAltitude() {
		int totalAltitude = 0;
		for (Station s : stations)
			totalAltitude += Integer.parseInt(s.getAltitude());
		return totalAltitude / stations.size();
	}

	/** 
	 * Gets the percent of OPEN stations (out of total stations)
	 * @return a string of the open percent float
	 */
	public String getOpenPercent() {
		double openCount = 0;
		for (Station s : stations)
			if (s.getStatus().equals("OPN"))
				openCount++;
		return String.format("%.2f", (openCount*100 / stations.size()));
	}
	
	public List<Station> getStations() {
		return stations;
	}
	
	/** 
	 * Gets the selected Station objects based on a list of station ids
	 * @param ids is a List<Integer> of station ids
	 * @return a List<Station> of the selected stations
	 */
	public List<Station> getStationsById(List<Integer> ids) {
		List<Station> selectedStations = new ArrayList<Station>();
		for (Station s : stations) {
			if (ids.contains(Integer.parseInt(s.getId())))
				selectedStations.add(s);
		}
		return selectedStations;
	}

	public void setStations(List<Station> stations) {
		this.stations = stations;
	}

	private List<Station> stations;
	
	public Stations() {
		stations = new ArrayList<Station>();
	}
	
	@Override
	public String toString() {
		String str = "{";
		for (Station s : stations)
			str += s+", ";
		return str+"}";
	}
}
