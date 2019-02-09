package parser;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestParseState {

    @Test
    public void testBuild(){
        List<Token> tokenList = new ArrayList<>();
        Variable variableX = Variable.build("x");
        Variable variableY = Variable.build("y");
        tokenList.add(variableX);
        tokenList.add(variableY);

        LeafNode leafVarX = LeafNode.build(variableX);

        ParseState testState1 = ParseState.build(leafVarX, tokenList);
        assertTrue(testState1.isSuccess());
        assertEquals(testState1.getNode(), leafVarX);
        assertFalse(testState1.hasNoRemainder());
        assertEquals(testState1.getRemainder(), tokenList);
    }

}
