package parser;

import java.util.*;
import java.util.function.*;

final class Cache<T, V> {

    private Map<T, V> cache;

    Cache(){
        cache = new HashMap<>();
    }

    V get(T key, Function<? super T, ? extends V> constructor) {
        Objects.requireNonNull(key, "Key cannot be null!");
        Objects.requireNonNull(constructor, "Constructor cannot be null!");

        V value;
        if (cache.containsKey(key)) {
            value = cache.get(key);
        }
        else {
            value = cache.computeIfAbsent(key, constructor);
        }
        return value;
    }

}
