package parser;

import java.util.*;

public interface Node {

    List<Token> toList();

    List<Node> getChildren();

    boolean isFruitful();

    Node simplify();

}