package io.magentys;

import static org.hamcrest.MatcherAssert.assertThat;

import org.hamcrest.Matcher;

import io.magentys.mplord.agent.AgentBase;

public class AgentVerifier<AGENT extends AgentBase<AGENT>> {

    public AGENT getAgent() {
        return agent;
    }

    protected final AGENT agent;

    public AgentVerifier(AGENT agent) {
        this.agent = agent;
    }

    public static <AGENT extends AgentBase<AGENT>> AgentVerifier verifyAs(AGENT agent) {
       return new AgentVerifier(agent);
    }

    public <TYPE> void that(Mission<TYPE, AGENT> obj, Matcher<TYPE> objectMatcher) {
        assertThat(obj.accomplishAs(agent), objectMatcher);
    }

    public <TYPE> void that(TYPE obj, Matcher<TYPE> objectMatcher) {
        assertThat(obj, objectMatcher);
    }


}
