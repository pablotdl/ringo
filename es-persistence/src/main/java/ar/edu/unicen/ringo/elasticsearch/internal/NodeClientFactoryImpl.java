package ar.edu.unicen.ringo.elasticsearch.internal;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Properties;

import org.elasticsearch.client.Client;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.NodeBuilder;

/**
 * Implementation of {@link ElasticSearchClientFactory} that creates node
 * clients.
 * @author psaavedra
 */
public class NodeClientFactoryImpl implements ElasticSearchClientFactory {

    @Override
    public Client createClient(Properties config) {
        NodeBuilder builder = NodeBuilder.nodeBuilder();
        builder.client(true);
        builder.local(Boolean.parseBoolean(config.getProperty("local.only",
                "false")));
        final Node node = builder.node();
        node.start();
        final Client client = node.client();
        //Create a proxy to make sure the node is closed when the client is.
        Client proxy = (Client) Proxy.newProxyInstance(getClass()
                .getClassLoader(), new Class[] { Client.class },
                new InvocationHandler() {
            
            @Override
            public Object invoke(Object proxy, Method method, Object[] args)
                    throws Throwable {
                Object retval = method.invoke(client, args);
                if (method.getName().equals("close")) {
                    node.stop();
                    node.close();
                }
                return retval;
            }
        });
        return proxy;
    }

}
