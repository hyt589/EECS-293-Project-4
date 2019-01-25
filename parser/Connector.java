package parser;

public final class Connector extends AbstractToken {

    private TerminalSymbol type;

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

        return new Connector(type);
    }

    public boolean equals(Connector connector) {
        return type.equals(connector.getType());
    }
}
