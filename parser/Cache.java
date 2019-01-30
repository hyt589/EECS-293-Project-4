package parser;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;

final class Cache<T, V> {

    private Map<T, V> cache;

    Cache(){
        cache = Collections.emptyMap();
    }

    //This getter is only for testing purposes
    Map<T, V> getCache(){
        return cache;
    }

    V get(T key, Function<? super T, ? extends V> constructor) {
        if (key == null) {
            throw new NullPointerException("Key is null");
        }
        else if (constructor == null){
            throw new NullPointerException("Constructor is null");
        }
        else if (cache.containsKey(key)) {
            return cache.get(key);
        }
        else {
            V value = constructor.apply(key);
            cache.put(key, value);
            return value;
        }
    }

}
