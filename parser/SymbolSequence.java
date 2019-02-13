package parser;

import java.util.*;

class SymbolSequence {

    private final List<Symbol> production;
    static final SymbolSequence EPSILON = new SymbolSequence(new ArrayList<>());

    private SymbolSequence(List<Symbol> symbolList){
        this.production = new ArrayList<>(symbolList);
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

        InternalNode.Builder childBuilder = new InternalNode.Builder();
        for (Symbol symbol : this.production){
            ParseState parsedRemainderFromSymbol = symbol.parse(remainder);
            if (parsedRemainderFromSymbol.isSuccess()){
                childBuilder.addChild(parsedRemainderFromSymbol.getNode());
                remainder = parsedRemainderFromSymbol.getRemainder();
            }
            else{
                return ParseState.FAILURE;
            }
        }
        return ParseState.build(childBuilder.build(), remainder);
    }

    @Override
    public String toString(){
        return this.production.toString();
    }

}
