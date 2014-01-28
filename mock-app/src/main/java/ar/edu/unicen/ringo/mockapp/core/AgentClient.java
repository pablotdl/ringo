package ar.edu.unicen.ringo.mockapp.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Client class that post call information to the agent.
 * @author pablosaavedra
 */
@Component("agentClient")
public class AgentClient {

	private static final Logger logger = LoggerFactory
			.getLogger(AgentClient.class);

	@Autowired
	private RestTemplate template;

	@Value("${agent.url}")
	private String agentUrl;

	public void invoke(CallInfo call) {
		logger.info("Starting call to the agent with info {}", call);
		template.postForLocation(agentUrl, call);
		logger.info("Agent invoked successfully");
	}

	public void setTemplate(RestTemplate template) {
		this.template = template;
	}

	public void setAgentUrl(String agentUrl) {
		this.agentUrl = agentUrl;
	}

}
