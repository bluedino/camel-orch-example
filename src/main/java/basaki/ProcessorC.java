package basaki;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

/**
 * 
 * <tt>Processorc</tt> is used for manipulating incoming message by adding a
 * string to it. Also adds contextual information related to service
 * orchestration.
 * <p/>
 * 
 * @author Indra Basak
 * 
 */
public class ProcessorC implements Processor {

	/**
	 * Processes the message exchange by appending a string to the incoming
	 * message. Also adds contextual information related to service
	 * orchestration.
	 * 
	 * @param exchange
	 *            the message exchange
	 * @throws Exception
	 *             if an internal processing error has occurred.
	 */
	@Override
	public void process(Exchange exchange) throws Exception {
		System.out.println("*** entering ProcessorC.process");
		System.out.println(exchange.getIn().getBody());
		Payload payload = exchange.getIn().getBody(Payload.class);
		String message = payload.getMessage();
		System.out.println(message);
		payload.setMessage(message + " ProcessorC message");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "ProcessorC");
		map.put("successful", Boolean.TRUE);
		exchange.setProperty("hist-map", map);
		System.out.println("*** exiting ProcessorC.process");
	}
}
