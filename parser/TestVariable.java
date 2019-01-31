package parser;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestVariable {

    @Test
    public void testBuild(){
        Variable variableA1 = Variable.build("a");
        Variable variableA2 = Variable.build("a");
        assertTrue(variableA1 == variableA2);
        assertEquals(variableA1.getRepresentation(), variableA2.toString());
    }

}