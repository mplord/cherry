package io.magentys.mplord;

import com.google.inject.Provider;

import io.magentys.mplord.agent.AgentTypedMemory;

public class Skills {
    private AgentTypedMemory agent;

    public void withAgent(AgentTypedMemory agent) {
        this.agent = agent;
    }

    protected AgentTypedMemory getAgent() {
        return agent;
    }

    protected <T extends Skill> T skill(Provider<T> skillProvider) {
        T skill = skillProvider.get();
        skill.useAgent(agent);
        return skill;
    }
}
