package parser;

public abstract class AbstractToken {

    private TerminalSymbol type;

    public final boolean matches(TerminalSymbol type) {
        if (type.equals(this.type)) {
            return true;
        }
        return false;
    }
}
