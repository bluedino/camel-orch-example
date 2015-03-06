package basaki;

import org.apache.camel.spring.Main;

/**
 * 
 * <tt>TestCamelOrchestration</tt> is the main class for starting the Camel
 * framework for testing example service orchestration.
 * <p/>
 * 
 * @author Indra Basak
 * 
 */
public class TestCamelOrchestration {

	/**
	 * Starts Camel framework by read the sprinf-camel application context file.
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		Main main = new Main();
		main.setApplicationContextUri("/META-INF/spring/camel-context.xml");
		main.run(args);
	}
}
