package parser;

public final class Variable extends AbstractToken implements Token {

    private TerminalSymbol type = TerminalSymbol.VARIABLE;

    private final String representation;

    private static Cache<String, Variable> cache;

    private Variable(String representation) {
        this.representation = representation;
    }

    public final TerminalSymbol getType() {
        return this.type;
    }

    public final String getRepresentation() {
        return representation;
    }

    public static final Variable build(String representation) {
        return cache.get(representation, Variable::new);
    }

    @Override
    public String toString() {
        return representation;
    }
}
