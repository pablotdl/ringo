package ar.edu.unicen.ringo.agent.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import ar.edu.unicen.ringo.persistence.InvocationData;
import ar.edu.unicen.ringo.persistence.PersistenceService;

@Path("/agent")
public class AgentService {

	private PersistenceService persistenceService;

	@POST
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response monitor(CallInfo info) {
		if (persistenceService == null) {
			return Response.status(Status.SERVICE_UNAVAILABLE)
					.entity("Service unavailable").build();
		}
		try {
			InvocationData data = null;
			this.persistenceService.persist(data);
			return Response.ok().build();
		} catch (RuntimeException e) {
			return Response.status(Status.SERVICE_UNAVAILABLE)
					.entity("Service unavailable").build();
		}
 	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public Response status() {
		return Response.ok("It's working").build();
	}

	public void setPersistenceService(PersistenceService persistenceService) {
		this.persistenceService = persistenceService;
	}

}
