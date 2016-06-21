package io.magentys.data;

/**
 * A generic key to enable strongly typed mapping between keys and values of different types.
 * 
 *
 * @param <V> The value type this key maps to
 */
public class TypedKey<V> {
    public final String name;

    /**
     * @param name The name of this key, used as a differentiator
     */
    public TypedKey(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}