package io.magentys;

import io.magentys.mplord.agent.AgentBase;

public interface Mission<RESULT, AGENT extends AgentBase<AGENT>> {

    RESULT accomplishAs(AGENT agent);
}
