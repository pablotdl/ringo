package ar.edu.unicen.ringo.agent;

import javax.servlet.ServletException;

import org.glassfish.jersey.servlet.ServletContainer;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.http.HttpService;
import org.osgi.service.http.NamespaceException;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

/**
 * {@link ServiceTrackerCustomizer} implementation that (un)registers the agent REST API
 * from the {@link HttpService}. 
 * @author ps
 */
public class HttpServiceTracker implements ServiceTrackerCustomizer<HttpService, HttpService> {

	private BundleContext bc;

	private ServletContainer sc;

	public HttpServiceTracker(final BundleContext context, final AgentApplication app) {
		this.bc = context;
		this.sc = new ServletContainer(app);
	}

	/**
	 * Registers the Agent API with the HTTP service.
	 * @param reference The HTTP service reference.
	 * @return The passed in reference.
	 */
	@Override
	public HttpService addingService(ServiceReference<HttpService> reference) {
		HttpService service = bc.getService(reference);
		try {
			service.registerServlet("/api", sc, null, null);
			System.out.println("Registered servlet context at /api");
		} catch (ServletException | NamespaceException e) {
			e.printStackTrace();
		}
		return service;
	}

	@Override
	public void modifiedService(ServiceReference<HttpService> reference,
			HttpService service) {
	}

	@Override
	public void removedService(ServiceReference<HttpService> reference,
			HttpService service) {
		service.unregister("/api");
	}

}
