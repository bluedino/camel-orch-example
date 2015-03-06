package basaki;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;

import org.apache.camel.Exchange;
import org.apache.camel.spi.DataFormat;

/**
 * 
 * <tt>FileNameDataFormat</tt> is used to marshal a string to a text file
 * starting with 'l' followed by current time in milliseconds.
 * <p/>
 * 
 * @author Indra Basak
 * 
 */
public class FileNameDataFormat implements DataFormat {

	/**
	 * Marshals a string object to an output stream. If the endpoint is of file:
	 * type, the marshaled object is saved as a text file starting with 'l'
	 * followed by current time in milliseconds.
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
		System.out.println("*** entering ExchangeToStringDataFormat.marshal");

		exchange.getOut().setHeader(Exchange.FILE_NAME,
				"l" + Calendar.getInstance().getTime().getTime() + ".txt");
		String message = exchange.getIn().getBody(String.class);
		;
		System.out.println("message: " + message);
		stream.write(message.getBytes());

		System.out.println("*** exiting ExchangeToStringDataFormat.marshal");
	}

	/**
	 * Unmarshals the given stream into an object. Not implemented.
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
		return null;
	}

}
