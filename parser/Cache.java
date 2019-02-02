package parser;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

final class Cache<T, V> {

    private Map<T, V> cache;

    Cache(){
        cache = new HashMap<>();
    }

    //This getter is only for testing purposes
    Map<T, V> getMap(){
        return new HashMap<>(cache);
    }

    V get(T key, Function<? super T, ? extends V> constructor) {
        Objects.requireNonNull(key, "Key cannot be null");
        Objects.requireNonNull(constructor, "Constructor cannot be null");
        if (cache.containsKey(key)) {
            return cache.get(key);
        }
        else {
            V value = constructor.apply(key);
            cache.put(key, value);
            return value;
        }
    }

}
