package io.magentys.mplord.skills;

import com.google.inject.Provider;

import io.magentys.mplord.agent.AgentBase;

public class Skills<AGENT extends AgentBase<AGENT>> {
    private AGENT agent;

    public void withAgent(AGENT agent) {
        this.agent = agent;
    }

    protected AGENT getAgent() {
        return agent;
    }

    protected <T extends Skill<AGENT>> T skill(Provider<T> skillProvider) {
        T skill = skillProvider.get();
        skill.useAgent(agent);
        return skill;
    }
}
