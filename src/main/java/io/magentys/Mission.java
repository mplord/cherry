package io.magentys;

public interface Mission<RESULT, M extends Memory<K>, K> {

    RESULT accomplishAs(Agent<M, K> agent);
}
