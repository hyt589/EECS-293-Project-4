package parser;

public final class Connector extends AbstractToken {

    private TerminalSymbol type;

    private static Cache<TerminalSymbol, Connector> cache;

    private Connector(TerminalSymbol type) {
        this.type = type;
    }

    @Override
    public TerminalSymbol getType() {
        return type;
    }

    public static final Connector build(TerminalSymbol type) throws NullPointerException, IllegalArgumentException {

        if (type.equals(null)) {
            throw new NullPointerException("Type is null");
        }

        if (!(type.equals(TerminalSymbol.PLUS) ||
                type.equals(TerminalSymbol.MINUS) ||
                type.equals(TerminalSymbol.TIMES) ||
                type.equals(TerminalSymbol.DIVIDE) ||
                type.equals(TerminalSymbol.OPEN) ||
                type.equals(TerminalSymbol.CLOSE))) {
            throw new IllegalArgumentException("Illegal connector");
        }

        return cache.get(type, Connector::new);
    }

    @Override
    public String toString() {
        switch (this.type) {
            case PLUS:
                return "+";
            case MINUS:
                return "-";
            case TIMES:
                return "*";
            case DIVIDE:
                return "/";
            case OPEN:
                return "(";
            default:
                return ")";
        }

    }
}
