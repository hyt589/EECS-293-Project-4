package parser;

import java.util.*;

public enum NonTerminalSymbol implements Symbol {

    EXPRESSION, EXPRESSION_TAIL, TERM, TERM_TAIL, UNARY, FACTOR;

    private static Map<NonTerminalSymbol, List<SymbolSequence>> productionTable = new HashMap<>();

    static {
        buildTable(productionTable, EXPRESSION, SymbolSequence.build(TERM, EXPRESSION_TAIL));

        buildTable(productionTable, EXPRESSION_TAIL, SymbolSequence.build(TerminalSymbol.PLUS, TERM, EXPRESSION_TAIL),
                SymbolSequence.build(TerminalSymbol.MINUS, TERM, EXPRESSION_TAIL),
                SymbolSequence.EPSILON);

        buildTable(productionTable, TERM, SymbolSequence.build(UNARY, TERM_TAIL));

        buildTable(productionTable, TERM_TAIL, SymbolSequence.build(TerminalSymbol.TIMES, UNARY, TERM_TAIL),
                SymbolSequence.build(TerminalSymbol.DIVIDE, UNARY, TERM_TAIL),
                SymbolSequence.EPSILON);

        buildTable(productionTable, UNARY, SymbolSequence.build(TerminalSymbol.MINUS, FACTOR),
                SymbolSequence.build(FACTOR));

        buildTable(productionTable, FACTOR, SymbolSequence.build(TerminalSymbol.OPEN, EXPRESSION, TerminalSymbol.CLOSE),
                SymbolSequence.build(TerminalSymbol.VARIABLE));
    }

    @Override
    public ParseState parse(List<Token> input) {
        Objects.requireNonNull(input, "Input token list cannot be null!");
        List<SymbolSequence> productionList = productionTable.get(this);

        for (SymbolSequence production : productionList) {
            ParseState returnedState = production.match(input);
            if (returnedState.isSuccess()) {
                return returnedState;
            }
        }
        return ParseState.FAILURE;
    }

    static final Optional<Node> parseInput(List<Token> input) {
        Optional<Node> returnedNode;
        Objects.requireNonNull(input, "Input token list cannot be null!");
        ParseState parseState = EXPRESSION.parse(input);
        boolean successfulParseNoRemainder = parseState.isSuccess() && parseState.hasNoRemainder();
        if (successfulParseNoRemainder) {
            returnedNode = Optional.of(parseState.getNode());
        }
        else{
            returnedNode = Optional.empty();
        }
        return returnedNode;
    }

    private static Map<NonTerminalSymbol, List<SymbolSequence>> buildTable(Map<NonTerminalSymbol,
            List<SymbolSequence>> table, NonTerminalSymbol nonTerminalSymbol, List<SymbolSequence> sequences) {
        table.put(nonTerminalSymbol, sequences);
        return table;
    }

    private static Map<NonTerminalSymbol, List<SymbolSequence>> buildTable(Map<NonTerminalSymbol,
            List<SymbolSequence>> table, NonTerminalSymbol nonTerminalSymbol, SymbolSequence... sequences) {
        return buildTable(table, nonTerminalSymbol, Arrays.asList(sequences));
    }

}
