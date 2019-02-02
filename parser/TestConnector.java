package parser;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestConnector {

    @Test public void testBuild(){
        try{
            Connector nullPointer = Connector.build(null);
        }
        catch(NullPointerException e){
            System.out.println(e);
        }

        TerminalSymbol illegalType = TerminalSymbol.VARIABLE;
        try{
            Connector illegalArgument = Connector.build(illegalType);
        }
        catch(IllegalArgumentException e){
            System.out.println(e);
        }

        TerminalSymbol times = TerminalSymbol.TIMES;
        Connector timesConnector = Connector.build(times);
        assertEquals(TerminalSymbol.TIMES, timesConnector.getType());
        assertEquals("*", timesConnector.toString());
    }

}
