package parser;

import java.util.*;

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

    boolean isSuccess(){
        return success;
    }

    Node getNode(){
        return node;
    }

    List<Token> getRemainder() {
        List<Token> remainder = new ArrayList<>();
        Collections.copy(remainder, this.remainder);
        return remainder;
    }

    final boolean hasNoRemainder(){
        return this.remainder.isEmpty();
    }

    public static final ParseState build(Node node, List<Token> remainderList){
        Objects.requireNonNull(node, "Node cannot be null!");
        Objects.requireNonNull(remainderList, "Remainder list cannot be null!");
        return new ParseState(true, node, remainderList);
    }

}
