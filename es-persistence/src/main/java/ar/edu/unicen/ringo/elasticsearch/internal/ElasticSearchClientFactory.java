package ar.edu.unicen.ringo.elasticsearch.internal;

import java.util.Properties;

import org.elasticsearch.client.Client;

/**
 * Abstract factory for creating Elastic Search clients.
 * @author psaavedra
 */
public interface ElasticSearchClientFactory {

    /**
     * Creates an Elastic Search client with the given configuration.
     * <p>
     * All the client instances returned from factory implementations should be
     * thread safe.
     * @param config The configuration for the client.
     * @return A newly created client instance, never null.
     */
    Client createClient(Properties config);
}
