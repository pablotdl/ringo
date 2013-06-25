package ar.edu.unicen.ringo.elasticsearch.internal;

import java.util.Properties;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.ImmutableSettings.Builder;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

/**
 * {@link ElasticSearchClientFactory} implementation that creates
 * {@link TransportClient} instances.
 * @author psaavedra
 */
public class TransportClientFactoryImpl implements ElasticSearchClientFactory {

    @Override
    public Client createClient(Properties config) {
        Builder builder = ImmutableSettings.settingsBuilder().put(config);
        Settings settings = builder.put("client.transport.sniff", true)
                .build();
        TransportClient client = new TransportClient(settings);
        String hosts = config.getProperty("es.hosts", "localhost:9300");
        for (String host : hosts.split(",")) {
            String[] parts = host.split(":");
            client.addTransportAddress(new InetSocketTransportAddress(parts[0],
                    Integer.valueOf(parts[1])));
        }
        return client;
    }

}
