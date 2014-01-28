package ar.edu.unicen.ringo.elasticsearch;

import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import ar.edu.unicen.ringo.persistence.PersistenceService;

/**
 * Activates or deactivates the bundle, by publishing the
 * {@link PersistenceService} implementation and managing the underlying client
 * lifecycle.
 * @author psaavedra
 */
public class Activator implements BundleActivator {

    private ServiceRegistration<PersistenceService> handle;

    /**
     * Starts the bundle.
     */
    public void start(BundleContext context) throws Exception {
    	String host = System.getProperty("es.host", "localhost");
    	String port = System.getProperty("es.port", "9200");
        PersistenceService service;
		service = new RestBasedElasticSearchPersistenceService(host,
				Integer.parseInt(port));
        handle = context.registerService(PersistenceService.class, service,
                new Hashtable<String, Object>());
    }

    /**
     * Stops the bundle.
     */
    public void stop(BundleContext context) throws Exception {
        this.handle.unregister();
    }

}
