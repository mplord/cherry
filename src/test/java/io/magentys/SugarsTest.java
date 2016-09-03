package io.magentys;

//import static io.magentys.AgentProvider.provideAgent;
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
        Agent<String> agent = (new Agent<String>(new CoreMemory()));//provideAgent().get();
        given(agent).performs(The.firstStep).and(The.secondStep);
        when(agent).performs(The.missionUnderTest);
        then(verifyAs(agent)).that(The.result, is("Success!"));
    }

    public static class The {
        static final Mission<Agent<String>, String> firstStep = new Mission<Agent<String>, String>() {
            @Override
            public Agent<String> accomplishAs(Agent<String> agent) {
                return agent;
            }
        };

        static final Mission<Agent<String>, String> secondStep = new Mission<Agent<String>, String>() {
            @Override
            public Agent<String> accomplishAs(Agent<String> agent) {
                return agent;
            }
        };

        static final Mission<Agent<String>, String> missionUnderTest = new Mission<Agent<String>, String>() {
            @Override
            public Agent<String> accomplishAs(Agent<String> agent) {
                return agent;
            }
        };

        static final Mission<String, String> result = new Mission<String, String>() {
            @Override
            public String accomplishAs(Agent<String> agent) {
                return "Success!";
            }
        };

    }
}
