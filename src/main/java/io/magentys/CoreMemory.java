package io.magentys;

import static io.magentys.utils.Any.any;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import io.magentys.exceptions.NotAvailableException;
import io.magentys.utils.Any;
import io.magentys.utils.Clazz;

public class CoreMemory<T> implements Memory<T> {

    private Map<T, Any<?>> map = new ConcurrentHashMap<T, Any<?>>();

    @Override
    public <VALUE> void remember(final T key, final VALUE value) {
         map.put(key, any(value));
    }

    @Override
    public void remember(T key, Any<?> any) {
        map.put(key, any);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <VALUE> VALUE recall(final T key, final Class<VALUE> clazz) {
        final Any<?> result = map.get(key);
        if(result == null) {
            return null;
        }
        final Object unwrapped = result.get();
        if (Clazz.isClassOrSubclass(clazz, unwrapped.getClass())) {
           return (VALUE) unwrapped;
        }
        throw new NotAvailableException("Expected value in memory was not of type: " + clazz);
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public Any<?> recall(T key) {
        return map.get(key);
    }

    @Override
    public <K> void transferTo(T myKey, Memory<K> memory, K theirKey) {
        // requires(memory instanceof CoreMemory, "It's not a Core Memory");
        // CoreMemory casted = (CoreMemory) memory;
        memory.remember(theirKey, map.get(myKey));
    }
}
