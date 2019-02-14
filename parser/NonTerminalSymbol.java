package parser;

import java.util.*;

public enum NonTerminalSymbol implements Symbol {

    EXPRESSION, EXPRESSION_TAIL, TERM, TERM_TAIL, UNARY, FACTOR;

    private static HashMap<NonTerminalSymbol, HashMap<TerminalSymbol, SymbolSequence>> productions = new HashMap<>();

    static {
        HashMap<TerminalSymbol, SymbolSequence> expression = new HashMap<>();
        expression.put(TerminalSymbol.MINUS, SymbolSequence.build(TERM, EXPRESSION_TAIL));
        expression.put(TerminalSymbol.OPEN, SymbolSequence.build(TERM, EXPRESSION_TAIL));
        expression.put(TerminalSymbol.VARIABLE, SymbolSequence.build(TERM, EXPRESSION_TAIL));
        productions.put(EXPRESSION, expression);

        HashMap<TerminalSymbol, SymbolSequence> expressionTail = new HashMap<>();
        expressionTail.put(TerminalSymbol.PLUS, SymbolSequence.build(TerminalSymbol.PLUS, TERM, EXPRESSION_TAIL));
        expressionTail.put(TerminalSymbol.MINUS, SymbolSequence.build(TerminalSymbol.MINUS, TERM, EXPRESSION_TAIL));
        expressionTail.put(TerminalSymbol.CLOSE, SymbolSequence.EPSILON); //if remainder starts with close, can only be EPSILON
        expressionTail.put(null, SymbolSequence.EPSILON);
        productions.put(EXPRESSION_TAIL, expressionTail);

        HashMap<TerminalSymbol, SymbolSequence> term = new HashMap<>();
        term.put(TerminalSymbol.MINUS, SymbolSequence.build(UNARY, TERM_TAIL));
        term.put(TerminalSymbol.OPEN, SymbolSequence.build(UNARY, TERM_TAIL));
        term.put(TerminalSymbol.VARIABLE, SymbolSequence.build(UNARY, TERM_TAIL));
        productions.put(TERM, term);

        HashMap<TerminalSymbol, SymbolSequence> termTail = new HashMap<>();
        termTail.put(TerminalSymbol.TIMES, SymbolSequence.build(TerminalSymbol.TIMES, UNARY, TERM_TAIL));
        termTail.put(TerminalSymbol.DIVIDE, SymbolSequence.build(TerminalSymbol.DIVIDE, UNARY, TERM_TAIL));
        termTail.put(TerminalSymbol.PLUS, SymbolSequence.EPSILON); //if remainder starts with plus, can only be EPSILON
        termTail.put(TerminalSymbol.MINUS, SymbolSequence.EPSILON); //if remainder starts with minus, can only be EPSILON
        termTail.put(null, SymbolSequence.EPSILON);
        productions.put(TERM_TAIL, termTail);

        HashMap<TerminalSymbol, SymbolSequence> unary = new HashMap<>();
        unary.put(TerminalSymbol.MINUS, SymbolSequence.build(TerminalSymbol.MINUS, FACTOR));
        unary.put(TerminalSymbol.OPEN, SymbolSequence.build(FACTOR));
        unary.put(TerminalSymbol.VARIABLE, SymbolSequence.build(FACTOR));
        productions.put(UNARY, unary);

        HashMap<TerminalSymbol, SymbolSequence> factor = new HashMap<>();
        factor.put(TerminalSymbol.OPEN, SymbolSequence.build(TerminalSymbol.OPEN, EXPRESSION, TerminalSymbol.CLOSE));
        factor.put(TerminalSymbol.VARIABLE, SymbolSequence.build(TerminalSymbol.VARIABLE));
        productions.put(FACTOR, factor);
    }

    @Override
    public ParseState parse(List<Token> input) {
        Objects.requireNonNull(input, "Input token list cannot be null!");
        HashMap<TerminalSymbol, SymbolSequence> productionList = productions.get(this);

        TerminalSymbol lookAhead = null;
        if(!input.isEmpty()){
            lookAhead = input.get(0).getType();
        }
        //else lookAhead stays null

        SymbolSequence production;
        if(productionList.containsKey(lookAhead)){
            production = productionList.get(lookAhead);
        }
        else{
            return ParseState.FAILURE;
        }

        ParseState returnedState = production.match(input);
        if (returnedState.isSuccess()) {
            return returnedState;
        }
        else {
            return ParseState.FAILURE;
        }
    }

    static final Optional<Node> parseInput(List<Token> input) {
        Objects.requireNonNull(input, "Input token list cannot be null!");
        Optional<Node> returnedNode;
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

}
