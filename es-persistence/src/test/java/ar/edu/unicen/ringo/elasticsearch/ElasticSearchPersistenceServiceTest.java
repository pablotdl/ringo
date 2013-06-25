package ar.edu.unicen.ringo.elasticsearch;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.Map;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.node.Node;
import org.elasticsearch.rest.RestStatus;
import org.junit.Test;
import org.junit.runner.RunWith;

import ar.edu.unicen.ringo.persistence.InvocationData;
import ar.edu.unicen.ringo.persistence.PersistenceService;

import com.github.tlrx.elasticsearch.test.annotations.ElasticsearchIndex;
import com.github.tlrx.elasticsearch.test.annotations.ElasticsearchNode;
import com.github.tlrx.elasticsearch.test.annotations.ElasticsearchSetting;
import com.github.tlrx.elasticsearch.test.support.junit.runners.ElasticsearchRunner;

/**
 * Tests the {@link ElasticSearchPersistenceService} class.
 * @author psaavedra
 */
@RunWith(ElasticsearchRunner.class)
public class ElasticSearchPersistenceServiceTest {

    @ElasticsearchNode(local = false, settings = {
            @ElasticsearchSetting(name = "http.enabled", value = "true"),
            @ElasticsearchSetting(name = "action.auto_create_index", value = "true") })
    protected Node node;

    private void doTest() {
        PersistenceService service = new RestBasedElasticSearchPersistenceService();

        InvocationData data = new InvocationData();
        data.setExecutionTime(300);
        data.setMethod("someMethod");
        data.setNode("node1");
        data.setSla("sla1");
        data.setTimestamp(System.currentTimeMillis() - 3600000);
        service.persist(data);

        node.client().admin().indices().prepareRefresh("agent").execute().actionGet();
        SearchResponse response = node.client().prepareSearch().execute().actionGet();
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
    @ElasticsearchIndex(indexName = "agent", forceCreate = true)
    public void basicTransportIndex() {
        assertThat(node, is(not(nullValue())));
        doTest();
    }

}
