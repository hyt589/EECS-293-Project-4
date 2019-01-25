package parser;

public final class Variable extends AbstractToken implements Token {

    private TerminalSymbol type = TerminalSymbol.VARIABLE;

    public final TerminalSymbol getType() {
        return this.type;
    }

}
