package ar.edu.unicen.ringo.elasticsearch;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.Map;
import java.util.Properties;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.node.Node;
import org.elasticsearch.rest.RestStatus;
import org.junit.Test;
import org.junit.runner.RunWith;

import ar.edu.unicen.ringo.elasticsearch.internal.ElasticSearchClientFactory;
import ar.edu.unicen.ringo.elasticsearch.internal.TransportClientFactoryImpl;
import ar.edu.unicen.ringo.persistence.InvocationData;
import ar.edu.unicen.ringo.persistence.PersistenceService;

import com.github.tlrx.elasticsearch.test.annotations.ElasticsearchNode;
import com.github.tlrx.elasticsearch.test.annotations.ElasticsearchSetting;
import com.github.tlrx.elasticsearch.test.support.junit.runners.ElasticsearchRunner;

/**
 * Tests the {@link ElasticSearchPersistenceService} class.
 * @author psaavedra
 */
@RunWith(ElasticsearchRunner.class)
public class ElasticSearchPersistenceServiceTest {

    @ElasticsearchNode(local = false, settings = { @ElasticsearchSetting(name = "http.enabled", value = "true") })
    protected Node node;

    private void doTest(Client client) throws InterruptedException {
        PersistenceService service = new ElasticSearchPersistenceService(client);

        InvocationData data = new InvocationData();
        data.setExecutionTime(300);
        data.setMethod("someMethod");
        data.setNode("node1");
        data.setSla("sla1");
        data.setTimestamp(System.currentTimeMillis() - 3600000);
        service.persist(data);

        client.admin().indices().prepareRefresh("agent").execute().actionGet();
        SearchResponse response = client.prepareSearch().execute().actionGet();
        assertThat(response.status(), is(RestStatus.OK));
        assertThat(response.getHits().getTotalHits(),
                equalTo(1L));
        Map<String, Object> map = response.getHits().getHits()[0].getSource();
        System.out.println(map);
        System.out.println("Elapsed time: " + response.getTook());
        assertThat(map.keySet(), hasItems("sla", "node",
                "method", "execution_time", "timestamp"));
        assertThat(String.valueOf(map.get("method")),
                equalTo("someMethod"));
        assertThat(String.valueOf(map.get("sla")),
                equalTo("sla1"));
        assertThat(String.valueOf(map.get("node")),
                equalTo("node1"));
        assertThat(String.valueOf(map.get("execution_time")),
                equalTo("300"));
    }

    @Test
    public void basicTransportIndex() throws InterruptedException {
        assertThat(node, is(not(nullValue())));
        ElasticSearchClientFactory factory = new TransportClientFactoryImpl();
        Properties props = new Properties();
        Client client = factory.createClient(props);
        doTest(client);
        client.close();
    }

}
