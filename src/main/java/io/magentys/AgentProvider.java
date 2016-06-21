package io.magentys;

import static io.magentys.StringMemory.stringMemory;

import java.util.HashSet;
import java.util.Set;

/**
 * Agent Provider creates agents as a Builder
 */
public class AgentProvider<M extends Memory<K>, K> {

    protected AgentProvider(M memory) {
        anAgent = new Agent<M, K>(memory);
    }

    private AgentProvider(Agent<M, K> agent) {
        anAgent = agent;
    }

    private Agent<M, K> anAgent;

    /**
     * Provides a vanilla agent with CoreMemory
     * 
     * @return an empty agent
     */
    public static AgentString agentWithStringMemory() {
        return new AgentString(stringMemory());
    }

    /**
     * Create a new agent with a type of memory
     * 
     * @param memory
     * @return
     */
    public static <M extends Memory<K>, K> Agent<M, K> agentWithMemory(M memory) {
        return new Agent<M, K>(memory);
    }

    /**
     * The starting point for creating agents
     * 
     * @return an AgentProvider
     */
    public static AgentProviderString provideAgent() {
        return new AgentProvider<StringMemory, String>(stringMemory());
    }

    public AgentProvider<M, K> called(String name) {
        anAgent = anAgent.clone();
        anAgent.setName(name);
        return this;
    }

    /**
     * Create a new agent with the memory provided
     * 
     * @param memory
     * @return a new agent
     */
    public AgentProvider<M, K> withMemory(M memory) {
        anAgent = anAgent.clone();
        anAgent.setMemory(memory);
        return this;
    }

    /**
     * Create a new agent with the tools provided
     * 
     * @param tools
     * @return a new agent
     */
    public AgentProvider<M, K> withTools(Object... tools) {
        anAgent = anAgent.clone().obtains(tools);
        return this;
    }

    public AgentProvider<M, K> withNarrators(Narrator... narrators) {
        Set<Narrator> narratorSet = new HashSet<Narrator>();
        for (Narrator narrator : narrators) {
            narratorSet.add(narrator);
        }
        anAgent = anAgent.clone().setNarrators(narratorSet);
        return this;
    }

    /**
     * Return the agent
     * 
     * @return the built agent
     */
    public Agent<M, K> get() {
        return anAgent;
    }

}
