package parser;

import java.util.List;
import java.util.Objects;

public enum TerminalSymbol implements Symbol {
    VARIABLE, PLUS, MINUS, TIMES, DIVIDE, OPEN, CLOSE;

    @Override
    public ParseState parse(List<Token> input){
        Objects.requireNonNull(input, "Input token list cannot be null!");
        if (input.get(0).matches(this)){
            return ParseState.build(LeafNode.build(input.get(0)), input.subList(1, input.size()));
        }
        else {
            return ParseState.FAILURE;
        }
    }
}
