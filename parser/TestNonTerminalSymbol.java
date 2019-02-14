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
        Connector connectorMinus = Connector.build(TerminalSymbol.MINUS);
        Variable variableX = Variable.build("x");
        Connector connectorPlus = Connector.build(TerminalSymbol.PLUS);
        Variable variableY = Variable.build("y");
        Connector connectorDivide = Connector.build(TerminalSymbol.DIVIDE);
        Variable variableZ = Variable.build("z");
        Connector connectorOpen = Connector.build(TerminalSymbol.OPEN);
        Connector connectorClose = Connector.build(TerminalSymbol.CLOSE);

        tokenList.add(connectorOpen);
        tokenList.add(connectorOpen);
        tokenList.add(variableX);
        tokenList.add(connectorPlus);
        tokenList.add(variableY);
        tokenList.add(connectorClose);
        tokenList.add(connectorMinus);
        tokenList.add(variableZ);
        tokenList.add(connectorClose);
        tokenList.add(connectorDivide);
        tokenList.add(variableZ);

        System.out.println("Test: (( x + y ) - z) / z");
        Optional<Node> testOptional = NonTerminalSymbol.parseInput(tokenList);
        System.out.println(testOptional);
        assertNotEquals(testOptional, Optional.empty());
    }

}
