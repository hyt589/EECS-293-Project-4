package parser;

import java.util.*;
import java.util.function.*;

final class Cache<T, V> {

    private Map<T, V> cache;

    Cache(){
        cache = new HashMap<>();
    }

    V get(T key, Function<? super T, ? extends V> constructor) {
        V value;
        Objects.requireNonNull(key, "Key cannot be null!");
        Objects.requireNonNull(constructor, "Constructor cannot be null!");

        if (cache.containsKey(key)) {
            value = cache.get(key);
        }
        else {
            value = cache.computeIfAbsent(key, constructor);
        }
        return value;
    }

}
