package parser;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestLeafNode {

    @Test public void testBuild(){
        Token variableX = Variable.build("x");
        LeafNode leafNodeVariableX = LeafNode.build(variableX);
        assertEquals(variableX, leafNodeVariableX.getToken());
        assertEquals(variableX.toString(), leafNodeVariableX.getToken().toString());
        System.out.println(leafNodeVariableX.toList());
    }

}