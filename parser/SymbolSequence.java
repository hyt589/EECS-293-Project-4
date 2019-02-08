package parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

class SymbolSequence {

    private final List<Symbol> production;
    static final SymbolSequence EPSILON = new SymbolSequence(new ArrayList<>());

    private SymbolSequence(List<Symbol> symbolList){
        production = symbolList;
    }

    static final SymbolSequence build(List<Symbol> production){
        Objects.requireNonNull(production, "Production symbol list cannot be null!");
        return new SymbolSequence(production);
    }

    static final SymbolSequence build(Symbol... symbols){
        Objects.requireNonNull(symbols, "Variable array of symbols cannot be null!");
        return new SymbolSequence(Arrays.asList(symbols));
    }

    ParseState match(List<Token> input){
        Objects.requireNonNull(input, "Input token list cannot be null!");
        List<Token> remainder = new ArrayList<>(input);
        List<Node> children = new ArrayList<>();
        for (Symbol symbol : production){
            ParseState parsedRemainderFromSymbol = symbol.parse(remainder);
            if (parsedRemainderFromSymbol.isASuccess()){
                children.add(parsedRemainderFromSymbol.getNode());
                remainder = parsedRemainderFromSymbol.getRemainder();
            }
            else{
                return ParseState.FAILURE;
            }
        }
        return ParseState.build(InternalNode.build(children), remainder);
    }

    @Override
    public String toString(){
        return production.toString();
    }

}
