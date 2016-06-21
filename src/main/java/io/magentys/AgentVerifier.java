package io.magentys;

import static org.hamcrest.MatcherAssert.assertThat;

import org.hamcrest.Matcher;

public class AgentVerifier<M extends CoreMemory<K>, K> {

    public Agent<M, K> getAgent() {
        return agent;
    }

    protected final Agent<M, K> agent;

    public AgentVerifier(Agent<M, K> agent) {
        this.agent = agent;
    }

    public static <MEM extends CoreMemory<KEY>, KEY> AgentVerifier<MEM, KEY> verifyAs(Agent<MEM, KEY> agent) {
        return new AgentVerifier<MEM, KEY>(agent);
    }

    public <TYPE> void that(Mission<TYPE, M, K> obj, Matcher<TYPE> objectMatcher) {
        assertThat(obj.accomplishAs(agent), objectMatcher);
    }

    public <TYPE> void that(TYPE obj, Matcher<TYPE> objectMatcher) {
        assertThat(obj, objectMatcher);
    }


}
