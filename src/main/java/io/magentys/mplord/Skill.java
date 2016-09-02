package io.magentys.mplord;

import io.magentys.mplord.agent.AgentTypedMemory;
import io.magentys.mplord.mission.MissionTypedMemory;

public abstract class Skill implements MissionTypedMemory<AgentTypedMemory> {

    private AgentTypedMemory agent;

    public Skill() {
    }

    public Skill(AgentTypedMemory agent) {
        this.agent = agent;
    }

    public void useAgent(AgentTypedMemory agent) {
        this.agent = agent;
    }

    public AgentTypedMemory accomplish() {
        return accomplishAs(agent);
    }

    @Override
    public abstract AgentTypedMemory accomplishAs(AgentTypedMemory agent);
}