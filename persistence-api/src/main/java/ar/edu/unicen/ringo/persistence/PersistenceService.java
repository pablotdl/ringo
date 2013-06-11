package ar.edu.unicen.ringo.persistence;

/**
 * Interface for classes that persist data retrieved by the agent.
 * @author ps
 */
public interface PersistenceService {

	void persist(InvocationData data);
}
