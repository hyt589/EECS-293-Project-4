package parser;

import com.google.gson.InstanceCreator;

import java.lang.reflect.Type;
import java.util.List;

public class SymbolCreator implements InstanceCreator<Symbol> {

    @Override
    public Symbol createInstance(Type type) {
        return new Symbol() {
            @Override
            public ParseState parse(List<Token> input) {
                return null;
            }
        };
    }
}
