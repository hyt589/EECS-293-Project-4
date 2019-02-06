package parser;

import java.util.List;

interface Symbol {

    ParseState parse(List<Token> input);

}
