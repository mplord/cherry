package io.magentys;

import static io.magentys.AgentProvider.provideAgent;
import static io.magentys.narrators.SysoutNarrator.sysout;

import org.junit.Test;

import io.magentys.annotations.Narrate;

public class NarratorTest {

    @Test
    public void shouldNarrateSuccessfully() throws Exception {
        Agent agent = provideAgent().called("immaculate").get();
        agent.addNarrators(sysout());
        final MyMission mission = new MyMission();
        agent.performs(mission);
        final MyMissionWithBeforeAndAfter mission1 = new MyMissionWithBeforeAndAfter();
        agent.performs(mission1);
        agent.performAll(mission, mission1);
    }


    @Narrate("Print this before")
    private class MyMission implements Mission<Agent, Agent> {
        @Override
        public Agent accomplishAs(Agent agent) {
            return agent;
        }
    }

    @Narrate(value = "print before", after = "print after")
    private class MyMissionWithBeforeAndAfter implements Mission<Agent, Agent> {
        @Override
        public Agent accomplishAs(Agent agent) {
            agent.narrateThat("hello world");
            agent.narrateThat("info", "hello world!");
            return agent;
        }
    }
}
