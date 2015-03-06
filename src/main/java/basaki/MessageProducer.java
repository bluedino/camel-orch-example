package basaki;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.springframework.stereotype.Component;

/**
 * 
 * <tt>MessageProducer</tt> is a bean used for routing.
 * <p/>
 * 
 * @author Indra Basak
 * 
 */
@Component("msgProducer")
public class MessageProducer {

	// The @Produce annotation is attached to this bean property.
	// It allows the bean to access any Camel endpoint without
	// using one of Camel’s DSLs.
	@Produce(uri = "direct:start")
	private ProducerTemplate template;

	/**
	 * Routes a string message asynchronously to the end point specified in the @Produce
	 * annotation via a producer template.
	 * 
	 * @param message
	 *            string message to be routed
	 * @return returns the results of asynchronous routing
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	public Object process(String message) throws InterruptedException,
			ExecutionException {
		System.out.println("---- MessageProducer.process " + message);
		Future<Object> future = template.asyncSendBody(
				template.getDefaultEndpoint(), message);
		return future.get();
	}
}
