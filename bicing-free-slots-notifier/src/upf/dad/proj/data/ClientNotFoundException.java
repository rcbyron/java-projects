/** ClientNotFoundException.java
 * This exception class is used when a client/subscription cannot be found
 * @author Robert (Connor) Byron
 * Fall 2016
 */
package upf.dad.proj.data;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class ClientNotFoundException extends WebApplicationException {

	private static final long serialVersionUID = 1L;
	
	/** 
	 * The constructor takes in an error message to be returned (as JSON)
	 * @param message is a custom ErrorMessage object
	 */
	public ClientNotFoundException(ErrorMessage message) {
        super(Response.status(Response.Status.NOT_FOUND)
            .entity(message).type(MediaType.APPLICATION_JSON).build());
    }
}