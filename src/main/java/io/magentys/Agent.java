package io.magentys;

import io.magentys.mplord.agent.AgentBase;

public class Agent extends AgentBase<Agent> {

    protected Memory memory;

    public Agent(final Memory memory) {
        super();
        this.memory = memory;
    }

    public void setMemory(final Memory mem) {
        this.memory = mem;
    }

    public <VALUE> void keepsInMind(final String key, final VALUE value) {
        this.memory.remember(key, value);
    }

    public <VALUE> VALUE recalls(final String key, final Class<VALUE> clazz) {
        return (VALUE) memory.recall(key, clazz);
    }

    public Memory getMemory() {
        return memory;
    }

    public <KEY> Agent askThe(final Agent anotherAgent, KEY key){
        anotherAgent.getMemory().transferTo(memory, key);
        return this;
    }

    public Agent clone() {
        return new Agent(memory).setTools(tools).setNarrators(narrators);
    }

}
