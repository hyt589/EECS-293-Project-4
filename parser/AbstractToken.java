package parser;

public abstract class AbstractToken implements Token {

    @Override
    public final boolean matches(TerminalSymbol type) {
        return this.getType() == type;
    }

}
