package io.magentys;

public interface Mission<RESULT, AGENT extends AgentBase<AGENT>> {

    RESULT accomplishAs(AGENT agent);
}
