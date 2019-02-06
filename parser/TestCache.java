package parser;

import org.junit.Test;
import java.util.HashMap;
import static org.junit.Assert.assertEquals;

public class TestCache {

    @Test public void testGet(){
        Cache<String, String> cache = new Cache<>();
        String cacheResult = cache.get("a", String::new);
        assertEquals(cacheResult, "a");
    }

}