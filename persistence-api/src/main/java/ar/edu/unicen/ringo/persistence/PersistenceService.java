package ar.edu.unicen.ringo.persistence;

/**
 * Interface for classes that persist data retrieved by the agent.
 * @author ps
 */
public interface PersistenceService {

    /**
     * Persists the given invocation data.
     * @param data The data to persist.
     */
	void persist(InvocationData data);
}
