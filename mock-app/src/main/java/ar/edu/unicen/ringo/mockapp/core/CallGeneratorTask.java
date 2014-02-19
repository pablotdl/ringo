package ar.edu.unicen.ringo.mockapp.core;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * A task that generates random call data from a given configuration.
 * 
 * @author pablosaavedra
 */
@Component("callGenerator")
public class CallGeneratorTask implements Runnable {

	@Autowired
	private Configuration data;
	@Autowired
	private AgentClient client;
	
	
	@Override
	public void run() {
		CallInfo info = new CallInfo();
		info.setMethod(data.getMethod());
		info.setTimestamp(System.currentTimeMillis());
		info.setExecutionTime(calculateExecutionTime());
		info.setMethod(data.getMethod());
		info.setNode(data.getNode());
		info.setSla(data.getSla());
		client.invoke(info);
	}

	/**
	 * Generates a random number between the configured bounds.
	 * @return The execution time.
	 */
	private int calculateExecutionTime() {
		ThreadLocalRandom random = ThreadLocalRandom.current();
		return random.nextInt(data.getMinExecTIme(), data.getMaxExecTime());
	}

}
