/** ErrorMessage.java
 * Represents a custom error message for thrown exceptions
 * @author Robert (Connor) Byron
 * Fall 2016
 */
package upf.dad.proj.data;

public class ErrorMessage {
	
	public String getError() { return error; }

	public void setError(String error) { this.error = error; }

	private String error;
	
	public ErrorMessage() { }
	
	public ErrorMessage(String error) { this.error = error; }
}