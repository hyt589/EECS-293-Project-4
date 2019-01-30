package parser;

public abstract class AbstractToken implements Token {

    public final boolean matches(TerminalSymbol type) {
        return this.getType() == type;
    }

}
