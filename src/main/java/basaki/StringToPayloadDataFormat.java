package basaki;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;

import org.apache.camel.Exchange;
import org.apache.camel.spi.DataFormat;

/**
 * 
 * <tt>StringToPayloadDataFormat</tt> is used for marshal a <tt>Payload</tt>
 * object to a string. It also unmarshals a string to a <tt>Payload</tt> object.
 * <p/>
 * 
 * @author Indra Basak
 * 
 */
public class StringToPayloadDataFormat implements DataFormat {

	/**
	 * Marshals a string message from a payload object to an output stream. If
	 * the endpoint is of file: type, the marshaled object is saved as a text
	 * file starting with 'p' followed by current time in milliseconds.
	 * 
	 * @param exchange
	 *            the current exchange
	 * @param graph
	 *            the object to be marshaled
	 * @param stream
	 *            the output stream to write the marshaled result to
	 * @throws Exception
	 *             can be thrown
	 */
	@Override
	public void marshal(Exchange exchange, Object graph, OutputStream stream)
			throws Exception {
		System.out.println("*** entering StringToPayloadDataFormat.marshal");

		exchange.getOut().setHeader(Exchange.FILE_NAME,
				"p" + Calendar.getInstance().getTime().getTime() + ".txt");

		System.out.println("payload1: "
				+ exchange.getIn().getBody(Payload.class));
		Payload payload = exchange.getIn().getBody(Payload.class);
		stream.write(payload.getMessage().getBytes());

		System.out.println("*** exiting StringToPayloadDataFormat.marshal");
	}

	/**
	 * Unmarshals the given stream into a <tt>Payload</tt> object.
	 * <p/>
	 * 
	 * @param exchange
	 *            the current exchange
	 * @param stream
	 *            the input stream with the object to be unmarshaled
	 * @return the unmarshaled object
	 * @throws Exception
	 *             can be thrown
	 */
	@Override
	public Object unmarshal(Exchange exchange, InputStream stream)
			throws Exception {
		System.out.println("*** entering StringToPayloadDataFormat.unmarshal");
		String message = exchange.getIn().getBody(String.class);
		System.out.println("message: " + message);
		Payload payload = new Payload();
		message = (message != null) ? message.trim() : message;
		payload.setMessage(message);
		System.out.println("*** exiting StringToPayloadDataFormat.unmarshal");
		return payload;
	}

}
