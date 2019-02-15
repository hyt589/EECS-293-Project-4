package parser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

class ParseJson {

    static String getProductionJsonAsString() throws IOException {

        File file = new File("./parser/productions.json");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

        StringBuilder stringBuilder = new StringBuilder();
        String line = bufferedReader.readLine();

        while (line != null) {
            stringBuilder.append(line).append("\n");
            line = bufferedReader.readLine();
        }

        return stringBuilder.toString();
    }

    static Map parseJsonAsMap() throws IOException {
        String json = getProductionJsonAsString();

        Gson gson = new GsonBuilder().registerTypeAdapter(Symbol.class, new SymbolCreator()).create();
        Type type = new TypeToken<Map<NonTerminalSymbol, Map<TerminalSymbol, SymbolSequence>>>(){}.getType();
        Map<NonTerminalSymbol, Map<TerminalSymbol, SymbolSequence>> productions = gson.fromJson(json, type);

        return productions;
    }

}
