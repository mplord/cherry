package io.magentys.mplord.skills;

import io.magentys.Mission;
import io.magentys.mplord.agent.AgentBase;

public abstract class Skill<AGENT extends AgentBase<AGENT>> implements Mission<AGENT, AGENT> {

    private AGENT agent;

    public Skill() {
    }

    public Skill(AGENT agent) {
        this.agent = agent;
    }

    public void useAgent(AGENT agent) {
        this.agent = agent;
    }

    public AGENT accomplish() {
        return accomplishAs(agent);
    }

    @Override
    public abstract AGENT accomplishAs(AGENT agent);
}