/** Message.java
 * Represents a Telegram message object (with required instance variables)
 * NOTE: changing instance variable names in this class could cause errors
 * @author Robert (Connor) Byron
 * Fall 2016
 */
package upf.dad.proj.data;

public class Message {
	public long getChat_id() {
		return chat_id;
	}

	public void setChat_id(long chat_id) {
		this.chat_id = chat_id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	private long chat_id;
	private String text;
	
	public Message(long chat_id, String text) {
		this.chat_id = chat_id;
		this.text = text;
	}
	
	public Message() { }
	
}
