package ar.edu.unicen.ringo.agent;

import org.glassfish.jersey.server.ResourceConfig;

public class AgentApplication extends ResourceConfig {

	public AgentApplication(Object... services) {
		for (Object service : services) {
			this.register(service);
		}
	}

}
