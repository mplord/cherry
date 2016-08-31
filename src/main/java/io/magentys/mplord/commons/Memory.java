package io.magentys.mplord.commons;

import io.magentys.commons.typemap.TypedKey;
import io.magentys.commons.typemap.TypedKeyMap;

public class Memory {

    private final TypedKeyMap values = new TypedKeyMap();

    public <T> Memory commit(TypedKey<T> key, T value) {
        values.put(key, value);
        return this;
    }

    public <T> T recall(TypedKey<T> key) {
        return values.get(key);
    }

    public <T> T recall(TypedKey<T> key, T defaultValue) {
        T value = recall(key);
        return (value == null) ? defaultValue : value;
    }

    public TypedKeyMap values() {
        return values;
    }

}