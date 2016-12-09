/** Subscription.java
 * Represents a client subscription to selected Bicing stations
 * @author Robert (Connor) Byron
 * Fall 2016
 */
package upf.dad.proj.data;

import java.util.ArrayList;
import java.util.List;

public class Subscription {

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getTelegramToken() {
		return telegramToken;
	}

	public void setTelegramToken(String telegram_token) {
		this.telegramToken = telegram_token;
	}

	public List<Integer> getStationIds() {
		return stationIds;
	}

	public void setStationIds(List<Integer> stationIds) {
		this.stationIds = stationIds;
	}

	private String phone;
	private String telegramToken;
	private List<Integer> stationIds;
	
	public Subscription() {
		this.phone = "";
		this.telegramToken = "";
		this.stationIds = new ArrayList<Integer>();
	}
	
	public Subscription(String phone, String telegramToken, List<Integer> stationIds) {
		this.phone = phone;
		this.telegramToken = telegramToken;
		this.stationIds = stationIds;
	}
	
	@Override
	public String toString() {
		return phone+" subscribed to "+stationIds+" with token: "+telegramToken;
	}
}