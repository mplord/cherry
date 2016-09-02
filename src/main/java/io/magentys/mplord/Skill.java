package io.magentys.mplord;

import io.magentys.Agent;
import io.magentys.Mission;

public abstract class Skill implements Mission<Agent> {

    private Agent agent;

    public Skill() {
    }

    public Skill(Agent agent) {
        this.agent = agent;
    }

    public void useAgent(Agent agent) {
        this.agent = agent;
    }

    public Agent accomplish() {
        return accomplishAs(agent);
    }

    @Override
    public abstract Agent accomplishAs(Agent agent);
}