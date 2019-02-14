package parser;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertNotEquals;

public class TestNonTerminalSymbol {

    @Test
    public void testParseInput(){
        List<Token> tokenList = new ArrayList<>();
        Connector connectorOpen = Connector.build(TerminalSymbol.OPEN);
        Variable variableX = Variable.build("x");
        Connector connectorTimes = Connector.build(TerminalSymbol.TIMES);
        Variable variableY = Variable.build("y");
        Connector connectorClose = Connector.build(TerminalSymbol.CLOSE);
        Connector connectorDivide = Connector.build(TerminalSymbol.DIVIDE);
        Variable variableZ = Variable.build("z");

        tokenList.add(connectorOpen);
        tokenList.add(variableX);
        tokenList.add(connectorTimes);
        tokenList.add(variableY);
        tokenList.add(connectorClose);
        tokenList.add(connectorDivide);
        tokenList.add(variableZ);

        System.out.println("Test: ( x + y ) / z");
        Optional<Node> testOptional = NonTerminalSymbol.parseInput(tokenList);
        System.out.println(testOptional);
        assertNotEquals(Optional.empty(), testOptional);

        List<Token> tokenList2 = new ArrayList<>();

        Variable variableX2 = Variable.build("a");
        Connector connectorPlus2 = Connector.build(TerminalSymbol.PLUS);
        Connector connectorOpen2 = Connector.build(TerminalSymbol.OPEN);
        Variable variableY2 = Variable.build("b");
        Connector connectorTimes2 = Connector.build(TerminalSymbol.TIMES);
        Variable variableZ2 = Variable.build("c");
        Connector connectorClose2 = Connector.build(TerminalSymbol.CLOSE);

        tokenList2.add(variableX2);
        tokenList2.add(connectorPlus2);
        tokenList2.add(connectorOpen2);
        tokenList2.add(variableY2);
        tokenList2.add(connectorTimes2);
        tokenList2.add(variableZ2);
        tokenList2.add(connectorClose2);

        System.out.println("Test: a + ( b * c )");
        Optional<Node> testOptional2 = NonTerminalSymbol.parseInput(tokenList2);
        System.out.println(testOptional2);
        assertNotEquals(Optional.empty(), testOptional2);
    }

}
