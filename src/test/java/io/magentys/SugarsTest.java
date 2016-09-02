package io.magentys;

import static io.magentys.AgentProvider.provideAgent;
import static io.magentys.AgentVerifier.verifyAs;
import static io.magentys.utils.Sugars.given;
import static io.magentys.utils.Sugars.then;
import static io.magentys.utils.Sugars.when;
import static org.hamcrest.core.Is.is;

import org.junit.Test;

/**
 * Created by kostasmamalis on 19/05/16.
 */
public class SugarsTest {



    @Test
    public void shouldReadTasty() throws Exception {
        Agent agent = provideAgent().get();
        given(agent).performs(The.firstStep).and(The.secondStep);
        when(agent).performs(The.missionUnderTest);
        then(verifyAs(agent)).that(The.result, is("Success!"));
    }

    public static class The {
        static final Mission<Agent, Agent> firstStep = new Mission<Agent, Agent>() {
            @Override
            public Agent accomplishAs(Agent agent) {
                return agent;
            }
        };

        static final Mission<Agent, Agent> secondStep = new Mission<Agent, Agent>() {
            @Override
            public Agent accomplishAs(Agent agent) {
                return agent;
            }
        };

        static final Mission<Agent, Agent> missionUnderTest = new Mission<Agent, Agent>() {
            @Override
            public Agent accomplishAs(Agent agent) {
                return agent;
            }
        };

        static final Mission<String, Agent> result = new Mission<String, Agent>() {
            @Override
            public String accomplishAs(Agent agent) {
                return "Success!";
            }
        };

    }
}
