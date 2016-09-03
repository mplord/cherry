package io.magentys.mplord.agent;

import io.magentys.commons.typemap.TypedKey;
import io.magentys.mplord.commons.Memory;

public class AgentTypedMemory extends AgentBase<AgentTypedMemory> {

    protected Memory memory;

    public AgentTypedMemory() {
        super();
        this.memory = new Memory();
    }

    public <T> void keepsInMind(final TypedKey<T> key, final T value) {
        this.memory.commit(key, value);
    }

    public <T> T recalls(final TypedKey<T> key) {
        return (T) memory.recall(key);
    }

    public Memory getMemory() {
        return memory;
    }

    public <T> AgentTypedMemory askThe(final AgentTypedMemory anotherAgent, TypedKey<T> key) {
        // memory.commit(key, anotherAgent.getMemory().recall(key));
        T value = anotherAgent.getMemory().recall(key);
        memory.commit(key, value);
        return this;
    }

}
