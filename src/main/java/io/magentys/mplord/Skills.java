package io.magentys.mplord;

import io.magentys.mplord.agent.AgentTypedMemory;

public class Skills {
    private AgentTypedMemory agent;

    public Skills(AgentTypedMemory agent) {
        this.agent = agent;
    }

    protected <T extends Skill> T skill(Class<T> skillClass) {
        try {
            T skill = skillClass.newInstance();
            skill.useAgent(agent);
            return skill;
        } catch (Exception e) {
            // TODO hacky - fix
        }
        return null;
    }
}
