package parser;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestTerminalSymbol {

    @Test
    public void testParse(){
        List<Token> tokenList = new ArrayList<>();
        Variable variableX = Variable.build("x");
        Variable variableY = Variable.build("y");
        tokenList.add(variableX);
        tokenList.add(variableY);
        LeafNode leafVarA = LeafNode.build(variableX);
        //sublist from 1 to size() is to test remainder below
        List<Token> tokenList2 = new ArrayList<>(tokenList.subList(1, tokenList.size()));

        ParseState testState1 = ParseState.build(leafVarA, tokenList2);
        assertTrue(testState1.isASuccess());
        assertEquals(testState1.getNode(), leafVarA);
        assertFalse(testState1.hasNoRemainder());
        assertEquals(testState1.getRemainder(), tokenList2);


        TerminalSymbol testSymbol1 = TerminalSymbol.VARIABLE;
        ParseState testParse1 = testSymbol1.parse(tokenList);
        assertEquals(testParse1.getNode().toString(), testState1.getNode().toString());
        assertEquals(testParse1.getRemainder(), testState1.getRemainder());

        TerminalSymbol testSymbol2 = TerminalSymbol.OPEN;
        assertEquals(testSymbol2.parse(tokenList), ParseState.FAILURE);
    }

}
