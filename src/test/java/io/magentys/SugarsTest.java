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
        Agent<StringMemory, String> agent = provideAgent().get();
        given(agent).performs(The.firstStep).and(The.secondStep);
        when(agent).performs(The.missionUnderTest);
        then(verifyAs(agent)).that(The.result, is("Success!"));
    }

    public static class The {
        static final Mission<Agent<StringMemory, String>, StringMemory, String> firstStep = new Mission<Agent<StringMemory, String>, StringMemory, String>() {
            @Override
            public Agent<StringMemory, String> accomplishAs(Agent<StringMemory, String> agent) {
                return agent;
            }
        };

        static final Mission<Agent<StringMemory, String>, StringMemory, String> secondStep = new Mission<Agent<StringMemory, String>, StringMemory, String>() {
            @Override
            public Agent<StringMemory, String> accomplishAs(Agent<StringMemory, String> agent) {
                return agent;
            }
        };

        static final Mission<Agent<StringMemory, String>, StringMemory, String> missionUnderTest = new Mission<Agent<StringMemory, String>, StringMemory, String>() {
            @Override
            public Agent<StringMemory, String> accomplishAs(Agent<StringMemory, String> agent) {
                return agent;
            }
        };

        static final Mission<String, StringMemory, String> result = new Mission<String, StringMemory, String>() {
            @Override
            public String accomplishAs(Agent<StringMemory, String> agent) {
                return "Success!";
            }
        };

    }
}
