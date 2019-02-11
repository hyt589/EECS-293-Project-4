package parser;

import java.util.*;

public enum NonTerminalSymbol implements Symbol {

    EXPRESSION, EXPRESSION_TAIL, TERM, TERM_TAIL, UNARY, FACTOR;

    private static Map<NonTerminalSymbol, List<SymbolSequence>> productionTable = new HashMap<>();

    static {
//        ArrayList<SymbolSequence> expression = new ArrayList<>();
//        expression.add(SymbolSequence.build(TERM, EXPRESSION_TAIL));
//        productionTable.put(EXPRESSION, expression);
        buildTable(productionTable, EXPRESSION, SymbolSequence.build(TERM, EXPRESSION_TAIL));

//        ArrayList<SymbolSequence> expressionTail = new ArrayList<>();
//        expressionTail.add(SymbolSequence.build(TerminalSymbol.PLUS, TERM, EXPRESSION_TAIL));
//        expressionTail.add(SymbolSequence.build(TerminalSymbol.MINUS, TERM, EXPRESSION_TAIL));
//        expressionTail.add(SymbolSequence.EPSILON);
//        productionTable.put(EXPRESSION_TAIL, expressionTail);
        buildTable(productionTable, EXPRESSION_TAIL, SymbolSequence.build(TerminalSymbol.PLUS, TERM, EXPRESSION_TAIL),
                SymbolSequence.build(TerminalSymbol.MINUS, TERM, EXPRESSION_TAIL),
                SymbolSequence.EPSILON);

//        ArrayList<SymbolSequence> term = new ArrayList<>();
//        term.add(SymbolSequence.build(UNARY, TERM_TAIL));
//        productionTable.put(TERM, term);

        buildTable(productionTable, TERM, SymbolSequence.build(UNARY, TERM_TAIL));

//        ArrayList<SymbolSequence> termTail = new ArrayList<>();
//        termTail.add(SymbolSequence.build(TerminalSymbol.TIMES, UNARY, TERM_TAIL));
//        termTail.add(SymbolSequence.build(TerminalSymbol.DIVIDE, UNARY, TERM_TAIL));
//        termTail.add(SymbolSequence.EPSILON);
//        productionTable.put(TERM_TAIL, termTail);

        buildTable(productionTable, TERM_TAIL, SymbolSequence.build(TerminalSymbol.TIMES, UNARY, TERM_TAIL),
                SymbolSequence.build(TerminalSymbol.DIVIDE, UNARY, TERM_TAIL),
                SymbolSequence.EPSILON);

//        ArrayList<SymbolSequence> unary = new ArrayList<>();
//        unary.add(SymbolSequence.build(TerminalSymbol.MINUS, FACTOR));
//        unary.add(SymbolSequence.build(FACTOR));
//        productionTable.put(UNARY, unary);

        buildTable(productionTable, UNARY, SymbolSequence.build(TerminalSymbol.MINUS, FACTOR),
                SymbolSequence.build(FACTOR));

//        ArrayList<SymbolSequence> factor = new ArrayList<>();
//        factor.add(SymbolSequence.build(TerminalSymbol.OPEN, EXPRESSION, TerminalSymbol.CLOSE));
//        factor.add(SymbolSequence.build(TerminalSymbol.VARIABLE));
//        productionTable.put(FACTOR, factor);

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

        if (parseState.isSuccess() && parseState.hasNoRemainder()) {
            returnedNode = Optional.of(parseState.getNode());
        }
        else{
            returnedNode = Optional.empty();
        }
        return returnedNode;
    }

    private static Map<NonTerminalSymbol, List<SymbolSequence>> buildTable(Map<NonTerminalSymbol, List<SymbolSequence>> table, NonTerminalSymbol nonTerminalSymbol, List<SymbolSequence> sequences) {
        table.put(nonTerminalSymbol, sequences);
        return table;
    }

    private static Map<NonTerminalSymbol, List<SymbolSequence>> buildTable(Map<NonTerminalSymbol, List<SymbolSequence>> table, NonTerminalSymbol nonTerminalSymbol, SymbolSequence... sequences) {
        return buildTable(table, nonTerminalSymbol, Arrays.asList(sequences));
    }



}
