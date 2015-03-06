package basaki;

import java.util.HashMap;

import javax.annotation.Resource;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.model.ProcessDefinition;
import org.apache.camel.model.ProcessorDefinition;
import org.apache.camel.processor.DelegateAsyncProcessor;
import org.apache.camel.spi.InterceptStrategy;

/**
 * 
 * <tt>ProcessInterceptor</tt> intercepts all Camel processors before they are
 * executed. Captures the start time, end time, process name, and any other
 * contextual informed stored as a hash map in the exchange header.
 * <p/>
 * 
 * @author Indra Basak
 * 
 */
public class ProcessInterceptor implements InterceptStrategy {

	// bean for routing message
	@Resource(name = "msgProducer")
	private MessageProducer producer;

	/**
	 * Intercepts only processor for service orchestration.
	 * 
	 * @param context
	 *            Camel context
	 * @param definition
	 *            the model this interceptor represents
	 * @param target
	 *            the processor to be wrapped
	 * @param nextTarget
	 *            the next processor to be routed to
	 * @return processor wrapped with an interceptor if the target is a
	 *         processor or not wrapped.
	 * @throws Exception
	 *             can be thrown
	 */
	@Override
	public Processor wrapProcessorInInterceptors(final CamelContext context,
			final ProcessorDefinition<?> definition, final Processor target,
			Processor nextTarget) throws Exception {

		// wraps Camel processor
		if (definition instanceof ProcessDefinition) {

			return new DelegateAsyncProcessor(new Processor() {
				@SuppressWarnings("unchecked")
				public void process(Exchange exchange) throws Exception {
					// pre-execution work
					System.out
							.println("%%%% ProcessInterceptor - entering processor "
									+ target);

					exchange.removeProperty("hist-map");
					long start = System.currentTimeMillis();

					// call the target processor
					target.process(exchange);

					// post-execution work
					long end = System.currentTimeMillis();
					System.out
							.println("%%%% ProcessInterceptor - exiting processor "
									+ target);

					HashMap<String, Object> map = (HashMap<String, Object>) exchange
							.getProperty("hist-map", HashMap.class);
					if (map == null) {
						map = new HashMap<String, Object>();
						map.put("name", definition.getLabel());
						map.put("successful", Boolean.FALSE);
					}

					StringBuffer buffer = new StringBuffer();
					buffer.append("processing time=" + (end - start) + "\t");
					for (String key : map.keySet()) {
						buffer.append(key + "=" + map.get(key) + "\t");
					}

					System.out.println("Buffer: " + buffer);
					producer.process(buffer.toString());
				}
			});
		} else {
			// returns the original unwrapped target
			return target;
		}

	}
}
