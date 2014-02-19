package ar.edu.unicen.ringo.mockapp.core;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Configuration
 *
 */
@Component("configuration")
public class Configuration {
	@Value("${invocation.minTime}")
	private int minExecTIme;
	@Value("${invocation.maxTime}")
	private int maxExecTime;
	@Value("${invocation.method}")
	private String method;
	@Value("${invocation.node}")
	private String node;
	@Value("${invocation.sla}")
	private String sla;
	@Value("${sampleInterval}")
	private int sampleInterval;

	public int getSampleInterval() {
		return sampleInterval;
	}
	public void setSampleInterval(int sampleInterval) {
		this.sampleInterval = sampleInterval;
	}
	public int getMinExecTIme() {
		return minExecTIme;
	}
	public void setMinExecTIme(int minExecTIme) {
		this.minExecTIme = minExecTIme;
	}
	public int getMaxExecTime() {
		return maxExecTime;
	}
	public void setMaxExecTime(int maxExecTime) {
		this.maxExecTime = maxExecTime;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getNode() {
		return node;
	}
	public void setNode(String node) {
		this.node = node;
	}
	public String getSla() {
		return sla;
	}
	public void setSla(String sla) {
		this.sla = sla;
	}

}