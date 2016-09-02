package io.magentys.mplord;

import io.magentys.Agent;

public class Skills {
    private Agent agent;

    public Skills(Agent agent) {
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
