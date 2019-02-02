package parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

final class ParseState {

    private final boolean success;
    private final Node node;
    private final List<Token> remainder;
    final static ParseState FAILURE = new ParseState(false, null, null);

    private ParseState(boolean success, Node node, List<Token> remainder){
        this.success = success;
        this.node = node;
        this.remainder = remainder;
    }

    boolean getSuccess(){
        return success;
    }

    Node getNode(){
        return node;
    }

    List<Token> getRemainder(){
        return new ArrayList<Token>(remainder);
    }

    final boolean hasNoRemainder(){
        return remainder == null;
    }

    public static final ParseState build(Node node, List<Token> remainderList){
        Objects.requireNonNull(node, "Node cannot be null");
        Objects.requireNonNull(remainderList, "Remainder list cannot be null");
        return new ParseState(true, node, remainderList);
    }

}
