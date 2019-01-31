package parser;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;

public class TestInternalNode {

    @Test public void testBuild() {
        Token variableX = Variable.build("x");
        LeafNode leafNodeVariableX = LeafNode.build(variableX);
        Token variableY = Variable.build("y");
        LeafNode leafNodeVariableY = LeafNode.build(variableY);

        List<Node> internalList = new ArrayList<>();
        internalList.add(leafNodeVariableX);
        internalList.add(leafNodeVariableY);

        InternalNode listVarXVarY = InternalNode.build(internalList);
        assertEquals(listVarXVarY.getChildren(), internalList);
        System.out.println(listVarXVarY.toList());
        System.out.println(listVarXVarY.toString());
    }

}