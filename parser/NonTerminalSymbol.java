package parser;

import java.util.*;

public enum NonTerminalSymbol implements Symbol {

    EXPRESSION, EXPRESSION_TAIL, TERM, TERM_TAIL, UNARY, FACTOR;

    private static Map<NonTerminalSymbol, List<SymbolSequence>> productionTable = new HashMap<>();
    static {
        productionTable.put(EXPRESSION, Arrays.asList(SymbolSequence.build(TERM, EXPRESSION_TAIL)));
        productionTable.put(EXPRESSION_TAIL, Arrays.asList(SymbolSequence.build(TerminalSymbol.PLUS, TERM, EXPRESSION_TAIL),
                SymbolSequence.build(TerminalSymbol.MINUS, TERM, EXPRESSION_TAIL),
                SymbolSequence.EPSILON));
        productionTable.put(TERM, Arrays.asList(SymbolSequence.build(UNARY, TERM_TAIL)));
        productionTable.put(TERM_TAIL, Arrays.asList(SymbolSequence.build(TerminalSymbol.TIMES, UNARY, TERM_TAIL),
                SymbolSequence.build(TerminalSymbol.DIVIDE, UNARY, TERM_TAIL),
                SymbolSequence.EPSILON));
        productionTable.put(UNARY, Arrays.asList(SymbolSequence.build(TerminalSymbol.MINUS, FACTOR),
                SymbolSequence.build(FACTOR)));
        productionTable.put(FACTOR, Arrays.asList(SymbolSequence.build(TerminalSymbol.OPEN, EXPRESSION, TerminalSymbol.CLOSE),
                SymbolSequence.build(TerminalSymbol.VARIABLE)));
    }

    @Override
    public ParseState parse(List<Token> input) {
        Objects.requireNonNull(input, "Input token list cannot be null!");
        for (List<SymbolSequence> productionList :
                productionTable.values()) {
            for (SymbolSequence production :
                    productionList) {
                if (production.match(input).getSuccess()) {
                    return production.match(input);
                }
            }
        }
        return ParseState.FAILURE;
    }

    

    private static final Optional<Node> parseInput(List<Token> input) {
        Objects.requireNonNull(input, "Input token list should not be null");
        for (SymbolSequence production :
                productionTable.get(EXPRESSION)) {
            if (production.match(input).getSuccess()) {
                return Optional.of(production.match(input).getNode());
            }
        }
        return Optional.empty();
    }


}
