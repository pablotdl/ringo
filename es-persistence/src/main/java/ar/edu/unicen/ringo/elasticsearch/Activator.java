package ar.edu.unicen.ringo.elasticsearch;

import java.util.Hashtable;
import java.util.Properties;

import org.elasticsearch.client.Client;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import ar.edu.unicen.ringo.elasticsearch.internal.ElasticSearchClientFactory;
import ar.edu.unicen.ringo.elasticsearch.internal.TransportClientFactoryImpl;
import ar.edu.unicen.ringo.persistence.PersistenceService;

/**
 * Activates or deactivates the bundle, by publishing the
 * {@link PersistenceService} implementation and managing the underlying client
 * lifecycle.
 * @author psaavedra
 */
public class Activator implements BundleActivator {

    private static final String DEFAULT_FACTORY = TransportClientFactoryImpl.class.getName();

    private Client client;

    private ServiceRegistration<PersistenceService> handle;

    /**
     * Starts the bundle.
     */
    public void start(BundleContext context) throws Exception {
        ElasticSearchClientFactory factory = Class.forName(DEFAULT_FACTORY)
                .asSubclass(ElasticSearchClientFactory.class).newInstance();
        Properties props = new Properties();
        this.client = factory.createClient(props);
        PersistenceService service;
        service = new ElasticSearchPersistenceService(client);
        handle = context.registerService(PersistenceService.class, service,
                new Hashtable<String, Object>());
    }

    /**
     * Stops the bundle.
     */
    public void stop(BundleContext context) throws Exception {
        this.handle.unregister();
        this.client.close();
    }

}
