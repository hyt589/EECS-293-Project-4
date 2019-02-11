package parser;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestSymbolSequence {

    @Test
    public void testParse(){
        TerminalSymbol testSymbol1 = TerminalSymbol.OPEN;
        TerminalSymbol testSymbol2 = TerminalSymbol.VARIABLE;
        TerminalSymbol testSymbol3 = TerminalSymbol.VARIABLE;
        TerminalSymbol testSymbol4 = TerminalSymbol.CLOSE;
        List<Symbol> testSymbolList1 = new ArrayList<>();
        testSymbolList1.add(testSymbol1);
        testSymbolList1.add(testSymbol2);
        testSymbolList1.add(testSymbol3);
        testSymbolList1.add(testSymbol4);

        SymbolSequence testSequence1 = SymbolSequence.build(testSymbolList1);
        SymbolSequence testSequence2 = SymbolSequence.build(testSymbol1, testSymbol2, testSymbol3, testSymbol4);
        assertEquals(testSequence1.toString(), testSequence2.toString());

        List<Token> tokenList = new ArrayList<>();
        Connector connectorOpen = Connector.build(TerminalSymbol.OPEN);
        Variable variableX = Variable.build("x");
        Variable variableY = Variable.build("y");
        Connector connectorClose = Connector.build(TerminalSymbol.CLOSE);
        tokenList.add(connectorOpen);
        tokenList.add(variableX);
        tokenList.add(variableY);
        tokenList.add(connectorClose);

        ParseState testState1 = testSequence1.match(tokenList);
        assertTrue(testState1.isSuccess());
        assertTrue(testState1.hasNoRemainder());
        assertEquals(testState1.getRemainder(), new ArrayList<>());
        assertEquals(testState1.getNode().toString(), "[(,x,y,)]");

        ParseState testState2 = testSequence2.match(tokenList);
        assertTrue(testState2.isSuccess());
        assertTrue(testState2.hasNoRemainder());
        assertEquals(testState2.getRemainder(), new ArrayList<>());
        assertEquals(testState2.getNode().toString(), "[(,x,y,)]");



        TerminalSymbol testSymbol5 = TerminalSymbol.VARIABLE;
        TerminalSymbol testSymbol6 = TerminalSymbol.PLUS;
        TerminalSymbol testSymbol7 = TerminalSymbol.VARIABLE;
        TerminalSymbol testSymbol8 = TerminalSymbol.DIVIDE;
        TerminalSymbol testSymbol9 = TerminalSymbol.VARIABLE;
        List<Symbol> testSymbolList3 = new ArrayList<>();
        testSymbolList3.add(testSymbol5);
        testSymbolList3.add(testSymbol6);
        testSymbolList3.add(testSymbol7);
        testSymbolList3.add(testSymbol8);
        testSymbolList3.add(testSymbol9);
        SymbolSequence testSequence3 = SymbolSequence.build(testSymbolList3);
        System.out.println(testSequence3.toString());

        List<Token> tokenList3 = new ArrayList<>();
        Variable variableA = Variable.build("a");
        Connector connectorPlus = Connector.build(TerminalSymbol.PLUS);
        Variable variableB = Variable.build("b");
        Connector connectorDivide = Connector.build(TerminalSymbol.DIVIDE);
        Variable variableC = Variable.build("c");
        tokenList3.add(variableA);
        tokenList3.add(connectorPlus);
        tokenList3.add(variableB);
        tokenList3.add(connectorDivide);
        tokenList3.add(variableC);

        ParseState testState3 = testSequence3.match(tokenList3);
        assertTrue(testState3.isSuccess());
        assertTrue(testState3.hasNoRemainder());
        assertEquals(new ArrayList<>(), testState3.getRemainder());
        assertEquals("[a,+,b,/,c]", testState3.getNode().toString());
        System.out.println(testState3.getNode().toString());
    }

}
