package io.magentys.exceptions;

/**
 * Not Available Exception for tools and memory entries
 */
public class NotAvailableException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Main Contructor
     * 
     * @param message
     */
    public NotAvailableException(final String message) {
        super(message);
    }

    /**
     * Not available Memory entry for class
     * 
     * @param clazz
     */
    public <T> NotAvailableException(final Class<T> clazz) {
        super(String.format("Tool of type: \"%s\" not found in agent's toolset... Use agent.obtains(<tool>) method to assign tools to agent.",
                clazz.toString()));
    }
}
