package ar.edu.unicen.ringo.agent;

import java.util.HashMap;

import org.glassfish.jersey.servlet.ServletContainer;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.http.HttpService;
import org.osgi.util.tracker.ServiceTracker;

import ar.edu.unicen.ringo.agent.api.AgentService;

public class Activator implements BundleActivator {

	private BundleContext bc = null;
	private ServiceTracker<HttpService, HttpService> httpTracker = null;
	private AgentApplication jaxRsApplication = null;
	private AgentService service = new AgentService();
	
    public void start(BundleContext context) throws Exception {
    	jaxRsApplication = new AgentApplication(service);
		httpTracker = new ServiceTracker<HttpService, HttpService>(bc,
				HttpService.class, null) {

					@Override
					public HttpService addingService(
							ServiceReference<HttpService> reference) {
						HttpService httpService = super.addingService(reference);
						if (httpService == null) {
							return null;
						}
						httpService.registerServlet("/api", new ServletContainer(), null, null);
						return httpService;
					}

					@Override
					public void removedService(
							ServiceReference<HttpService> reference,
							HttpService service) {
						// TODO Auto-generated method stub
						super.removedService(reference, service);
					}
		};
    }

    public void stop(BundleContext context) throws Exception {
    }

}
