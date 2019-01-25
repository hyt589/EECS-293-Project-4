package parser;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;

class Cache<T, V> {

    private Map<T, V> cache = Collections.emptyMap();

    //The description on the assignment doc is really confusing on this one. Be sure to check back later.
    V get(T key, Function<? super T, ? extends V> constructor) {
        if (key.equals(null) || constructor.equals(null)) {
            throw new NullPointerException("Key or constructor is null");
        }

        if (cache.containsKey(key)) {
            return cache.get(key);
        }

        V value = constructor.apply(key);
        cache.put(key, value);

        return value;
    }


}
