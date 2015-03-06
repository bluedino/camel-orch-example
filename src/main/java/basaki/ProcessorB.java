package basaki;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

/**
 * 
 * <tt>ProcessorB</tt> Is used for manipulating incoming message by adding a
 * string to it. Also adds contextual information related to service
 * orchestration.
 * <p/>
 * 
 * @author Indra Basak
 * 
 */
public class ProcessorB implements Processor {

	private static int counter = 0;
	/**
	 * Processes the message exchange by appending a string to the incoming
	 * message. Also adds contextual information related to service
	 * orchestration. The processor fails every second, fourth, and fifth attempt.
	 * 
	 * @param exchange
	 *            the message exchange
	 * @throws Exception
	 *             if an internal processing error has occurred.
	 */
	@Override
	public void process(Exchange exchange) throws Exception {
		System.out.println("*** entering ProcessorB.process");

		System.out.println(exchange.getIn().getBody());
		Payload payload = exchange.getIn().getBody(Payload.class);
		String message = payload.getMessage();
		System.out.println(message);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "ProcessorB");
		map.put("successful", Boolean.TRUE);
		exchange.setProperty("hist-map", map);

		counter++;
		System.out.println("counter = " + counter);
		if (counter == 2 || counter == 4 || counter == 5) {
			map.put("successful", Boolean.FALSE);
			System.out
					.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% ProcessorB failed to consume message");
			if (counter == 5) {
				counter = 0;
			}
			throw new BusinessException("counter = " + counter);
		}

		payload.setMessage(message + " ProcessorB message");
		System.out.println("*** exiting ProcessorB.process");
	}
}
