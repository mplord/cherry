package io.magentys;

//import static io.magentys.AgentProvider.agent;
import static io.magentys.AgentVerifier.verifyAs;
import static org.hamcrest.core.Is.is;

import org.junit.Test;

/**
 * Created by kostasmamalis on 29/04/16.
 */
public class AgentVerifierTest {

    Mission<String, String> reverseStringOf(final String s) {
        return new Mission<String, String>() {
            @Override
            public String accomplishAs(Agent<String> agent) {
                return new StringBuilder(s).reverse().toString();
            }
        };
    }

    @Test
    public void shouldPerformVerificationsAsAgent() throws Exception {
        verifyAs(new Agent<String>(new CoreMemory())).that(reverseStringOf("string"), is("gnirts"));
    }
}
