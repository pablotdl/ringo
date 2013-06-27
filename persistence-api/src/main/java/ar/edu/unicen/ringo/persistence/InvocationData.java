package ar.edu.unicen.ringo.persistence;

import java.io.Serializable;

/**
 * Represents the datum obtained from an application monitored by the agent. 
 * @author ps
 */
public class InvocationData implements Serializable {

    /**
     * Serial version.
     */
    private static final long serialVersionUID = 5123160309790966648L;

    private String sla;
    private String node;
    private String method;
    private int executionTime;
    private long timestamp;
    public String getSla() {
        return sla;
    }
    public void setSla(String sla) {
        this.sla = sla;
    }
    public String getNode() {
        return node;
    }
    public void setNode(String node) {
        this.node = node;
    }
    public String getMethod() {
        return method;
    }
    public void setMethod(String method) {
        this.method = method;
    }
    public int getExecutionTime() {
        return executionTime;
    }
    public void setExecutionTime(int executionTime) {
        this.executionTime = executionTime;
    }
    public long getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("InvocationData [sla=").append(sla).append(", node=")
                .append(node).append(", method=").append(method)
                .append(", executionTime=").append(executionTime)
                .append(", timestamp=").append(timestamp).append("]");
        return builder.toString();
    }

}
