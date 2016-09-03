package io.magentys;

import java.util.HashSet;
import java.util.Set;

/**
 * Agent Provider creates agents as a Builder
 */
public class AgentProvider<KEY> {

    public AgentProvider(Memory<KEY> memory) {
        anAgent = new Agent<KEY>(memory);//agent();
    }

    public AgentProvider(Agent<KEY> agent) {
        anAgent = agent;
    }

    private Agent<KEY> anAgent;

    /**
     * Provides a vanilla agent with CoreMemory
     * 
     * @return an empty agent
     */
    //public static Agent<KEY> agent() {
    //    return new Agent<KEY>(newMemory());
    //}

    /**
     * Create a new agent with a type of memory
     * 
     * @param memory
     * @return
     */
    public Agent<KEY> agentWithMemory(Memory<KEY> memory) {
        return new Agent<KEY>(memory);
    }

    /**
     * The starting point for creating agents
     * 
     * @return an AgentProvider
     */
    //public static <KEY> AgentProvider<KEY> provideAgent() {
    //    return new AgentProvider<KEY>();
    //}

    public AgentProvider<KEY> called(String name) {
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
    public AgentProvider<KEY> withMemory(Memory<KEY> memory) {
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
    public AgentProvider<KEY> withTools(Object... tools) {
        anAgent = anAgent.clone().obtains(tools);
        return this;
    }

    public AgentProvider<KEY> withNarrators(Narrator... narrators) {
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
    public Agent<KEY> get() {
        return anAgent;
    }

}
