package ar.edu.unicen.ringo.mockapp.core;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * A task that generates random call data from a given configuration.
 * 
 * @author pablosaavedra
 */
@Component("callGenerator")
public class CallGeneratorTask implements Runnable {

	@Value("${invocation.minTime}")
	private int minExecTIme;
	@Value("${invocation.maxTime}")
	private int maxExecTime;
	@Value("${invocation.method}")
	private String method;
	@Value("${invocation.node}")
	private String node;
	@Value("${invocation.sla}")
	private String sla;

	@Autowired
	private AgentClient client;
	
	
	@Override
	public void run() {
		CallInfo info = new CallInfo();
		info.setMethod(method);
		info.setTimestamp(System.currentTimeMillis());
		info.setExecutionTime(calculateExecutionTime());
		info.setMethod(method);
		info.setNode(node);
		info.setSla(sla);
		client.invoke(info);
	}

	/**
	 * Generates a random number between the configured bounds.
	 * @return The execution time.
	 */
	private int calculateExecutionTime() {
		ThreadLocalRandom random = ThreadLocalRandom.current();
		return random.nextInt(minExecTIme, maxExecTime);
	}

}
