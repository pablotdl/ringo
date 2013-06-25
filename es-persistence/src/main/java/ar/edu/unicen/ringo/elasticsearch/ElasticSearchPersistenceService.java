package ar.edu.unicen.ringo.elasticsearch;

import java.io.IOException;
import java.util.Date;

import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;

import ar.edu.unicen.ringo.persistence.InvocationData;
import ar.edu.unicen.ringo.persistence.PersistenceService;

public class ElasticSearchPersistenceService implements PersistenceService {

    private final Client client;

    public ElasticSearchPersistenceService(Client client) {
        this.client = client;
    }

    @Override
    public void persist(final InvocationData data) {
        IndexRequestBuilder builder = client
                .prepareIndex("agent", "invocation");
        XContentBuilder sourceBuilder;
        try {
            sourceBuilder = XContentFactory.jsonBuilder();
            sourceBuilder.startObject().field("sla", data.getSla())
                    .field("node", data.getNode())
                    .field("method", data.getMethod())
                    .field("execution_time", data.getExecutionTime())
                    .field("timestamp", new Date(data.getTimestamp()))
                    .endObject();
            builder.setSource(sourceBuilder);
            System.out.println("Indexing data: " + sourceBuilder.string());
            IndexResponse response = builder.execute().actionGet();
            System.out
                    .println(String
                            .format("Successfully written invocation to index %s. "
                                    + "Response type was %s, indexed document %s, version %s",
                                    response.getIndex(), response.getType(),
                                    response.getId(), response.getVersion()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
