package parser;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ParseJsonTest {

    @Test
    void parseJsonAsMap() {
        Map map;
        try {
            map = ParseJson.parseJsonAsMap();
            System.out.println(map.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}