package parser;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestConnector {

    @Test public void testBuild(){
        String nullString = null;
        try{
            Connector nullPointer = Connector.build(nullString);
        }
        catch(NullPointerException e){
            System.out.println(e);
        }

        String illegalString = "x";
        try{
            Connector illegalArgument = Connector.build(illegalString);
        }
        catch(IllegalArgumentException e){
            System.out.println(e);
        }

        String times = "*";
        Connector timesConnector = Connector.build(times);
        assertEquals(TerminalSymbol.TIMES, timesConnector.getType());
        assertEquals(times, timesConnector.toString());
    }

}
