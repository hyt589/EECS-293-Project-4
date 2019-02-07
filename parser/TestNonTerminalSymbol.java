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
        Variable variableX = Variable.build("x");
        Connector connectorTimes = Connector.build(TerminalSymbol.TIMES);
        Variable variableY = Variable.build("y");

        tokenList.add(variableX);
        tokenList.add(connectorTimes);
        tokenList.add(variableY);

        System.out.println("Test: x * y");
        Optional<Node> testOptional = NonTerminalSymbol.parseInput(tokenList);
        System.out.println(testOptional);
        assertNotEquals(testOptional, Optional.empty());
    }

}
