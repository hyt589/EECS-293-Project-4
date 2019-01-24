package parser;

import java.util.Map;
import java.util.function.Function;

class Cache<T, V> {

    private Map<T, V> cache;

    V get(T key, Function<? super T, ? extends V> constructor) {
        if (key.equals(null) || constructor.equals(null)) {
            throw new NullPointerException();
        }

        if (cache.containsKey(key)) {
            return cache.get(key);
        }

        return constructor.apply(key);
    }
}
