package parser;

import java.util.*;
import java.util.function.*;

public final class Variable extends AbstractToken {

    private final String representation;
    private static Cache<String, Variable> cache = new Cache<>();

    private Variable(String representation) {
        this.representation = representation;
    }

    @Override
    public final TerminalSymbol getType() {
        return TerminalSymbol.VARIABLE;
    }

    public final String getRepresentation() {
        return this.representation;
    }

    public static final Variable build(String representation) {
        Function<? super String, ? extends Variable> variableConstructor = (String rep) -> new Variable(rep);
        return cache.get(Objects.requireNonNull(representation, "Representation string cannot be null!"),
                variableConstructor);
    }

    @Override
    public String toString() {
        return this.representation;
    }

}