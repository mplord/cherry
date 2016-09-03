package io.magentys;

public interface Mission<RESULT, KEY> {

    RESULT accomplishAs(Agent<KEY> agent);
}
