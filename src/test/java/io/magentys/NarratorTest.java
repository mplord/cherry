package io.magentys;

import static io.magentys.narrators.SysoutNarrator.sysout;

import org.junit.Test;

import io.magentys.annotations.Narrate;

public class NarratorTest {

    @SuppressWarnings("unchecked")
    @Test
    public void shouldNarrateSuccessfully() throws Exception {
        AgentString agent = provideAgent().called("immaculate").get();
        agent.addNarrators(sysout());
        final MyMission mission = new MyMission();
        agent.performs(mission);
        final MyMissionWithBeforeAndAfter mission1 = new MyMissionWithBeforeAndAfter();
        agent.performs(mission1);
        agent.performAll(mission, mission1);
    }


    @Narrate("Print this before")
    private class MyMission implements Mission<Agent<String>, String> {
        @Override
        public Agent<String> accomplishAs(Agent<String> agent) {
            return agent;
        }
    }

    @Narrate(value = "print before", after = "print after")
    private class MyMissionWithBeforeAndAfter implements Mission<Agent<String>, String> {
        @Override
        public Agent<String> accomplishAs(Agent<String> agent) {
            agent.narrateThat("hello world");
            agent.narrateThat("info", "hello world!");
            return agent;
        }
    }
}
