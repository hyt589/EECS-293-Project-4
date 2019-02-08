package parser;

import java.util.List;
import java.util.Objects;

public enum TerminalSymbol implements Symbol {
    VARIABLE, PLUS, MINUS, TIMES, DIVIDE, OPEN, CLOSE;

    @Override
    public ParseState parse(List<Token> input){
        Objects.requireNonNull(input, "Input token list cannot be null!");
        ParseState returnState;
        if (!input.isEmpty() && input.get(0).matches(this)){
            returnState = ParseState.build(LeafNode.build(input.get(0)), input.subList(1, input.size()));
        }
        else {
            returnState = ParseState.FAILURE;
        }
        return returnState;
    }
}
