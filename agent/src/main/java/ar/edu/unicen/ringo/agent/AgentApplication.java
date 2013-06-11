package ar.edu.unicen.ringo.agent;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

public class AgentApplication extends Application {

	private Set<Object> singletons;

	public AgentApplication(Object... services) {
		this.singletons = new HashSet<>(Arrays.asList(services));
	}

	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}


	
}
