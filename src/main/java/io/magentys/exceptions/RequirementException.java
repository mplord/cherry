package io.magentys.exceptions;

/**
 * Created by kostasmamalis on 08/04/16.
 */
public class RequirementException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public RequirementException(final String message) {
        super(message);
    }

    public <T> RequirementException(final Class<T> clazz) {
        super(String.format("Tool of type: \"%s\" not found in agent's toolset... Use agent.obtains(<tool>) method to assign tools to agent.",
                clazz.toString()));
    }
}
