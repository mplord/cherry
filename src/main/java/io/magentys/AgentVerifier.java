package io.magentys;

import static org.hamcrest.MatcherAssert.assertThat;

import org.hamcrest.Matcher;

public class AgentVerifier<KEY> {

    public Agent<KEY> getAgent() {
        return agent;
    }

    protected final Agent<KEY> agent;

    public AgentVerifier(Agent<KEY> agent) {
        this.agent = agent;
    }

    public static <KEY> AgentVerifier<KEY> verifyAs(Agent<KEY> agent) {
        return new AgentVerifier<KEY>(agent);
    }

    public <TYPE> void that(Mission<TYPE, KEY> obj, Matcher<TYPE> objectMatcher) {
        assertThat(obj.accomplishAs(agent), objectMatcher);
    }

    public <TYPE> void that(TYPE obj, Matcher<TYPE> objectMatcher) {
        assertThat(obj, objectMatcher);
    }

}
