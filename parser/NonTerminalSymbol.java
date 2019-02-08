package parser;

import java.util.*;

public enum NonTerminalSymbol implements Symbol {

    EXPRESSION, EXPRESSION_TAIL, TERM, TERM_TAIL, UNARY, FACTOR;

    private static Map<NonTerminalSymbol, List<SymbolSequence>> productionTable = new HashMap<>();

    static {
        ArrayList<SymbolSequence> expression = new ArrayList<>();
        expression.add(SymbolSequence.build(TERM, EXPRESSION_TAIL));
        productionTable.put(EXPRESSION, expression);

        ArrayList<SymbolSequence> expressionTail = new ArrayList<>();
        expressionTail.add(SymbolSequence.build(TerminalSymbol.PLUS, TERM, EXPRESSION_TAIL));
        expressionTail.add(SymbolSequence.build(TerminalSymbol.MINUS, TERM, EXPRESSION_TAIL));
        expressionTail.add(SymbolSequence.EPSILON);
        productionTable.put(EXPRESSION_TAIL, expressionTail);

        ArrayList<SymbolSequence> term = new ArrayList<>();
        term.add(SymbolSequence.build(UNARY, TERM_TAIL));
        productionTable.put(TERM, term);

        ArrayList<SymbolSequence> termTail = new ArrayList<>();
        termTail.add(SymbolSequence.build(TerminalSymbol.TIMES, UNARY, TERM_TAIL));
        termTail.add(SymbolSequence.build(TerminalSymbol.DIVIDE, UNARY, TERM_TAIL));
        termTail.add(SymbolSequence.EPSILON);
        productionTable.put(TERM_TAIL, termTail);

        ArrayList<SymbolSequence> unary = new ArrayList<>();
        unary.add(SymbolSequence.build(TerminalSymbol.MINUS, FACTOR));
        unary.add(SymbolSequence.build(FACTOR));
        productionTable.put(UNARY, unary);

        ArrayList<SymbolSequence> factor = new ArrayList<>();
        factor.add(SymbolSequence.build(TerminalSymbol.OPEN, EXPRESSION, TerminalSymbol.CLOSE));
        factor.add(SymbolSequence.build(TerminalSymbol.VARIABLE));
        productionTable.put(FACTOR, factor);
    }

    @Override
    public ParseState parse(List<Token> input) {
        Objects.requireNonNull(input, "Input token list cannot be null!");
        List<SymbolSequence> productionList = productionTable.get(this);

        for (SymbolSequence production : productionList) {
            ParseState returnedState = production.match(input);
            if (returnedState.isASuccess()) {
                return returnedState;
            }
        }
        return ParseState.FAILURE;
    }

    static final Optional<Node> parseInput(List<Token> input) {
        Optional<Node> returnedNode;
        Objects.requireNonNull(input, "Input token list cannot be null!");
        ParseState parseState = EXPRESSION.parse(input);

        if (parseState.isASuccess() && parseState.hasNoRemainder()) {
            returnedNode = Optional.of(parseState.getNode());
        }
        else{
            returnedNode = Optional.empty();
        }
        return returnedNode;
    }



}
