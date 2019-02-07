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

    V get(T key, Function<? super T, ? extends V> constructor) {
        Objects.requireNonNull(key, "Key cannot be null!");
        Objects.requireNonNull(constructor, "Constructor cannot be null!");
        if (cache.containsKey(key)) {
            return cache.get(key);
        }
        else {
            //Constructors are defined as Function<? super String, ? extends Variable> variableConstructor =
            //(Void) -> new Variable(representation);
            //So I don't know why this doesn't work: V value = constructor.apply(Void);
            //V value = constructor.apply(key);
            //cache.put(key, value);
            V value = cache.computeIfAbsent(key, constructor);
            return value;
        }
    }

}
