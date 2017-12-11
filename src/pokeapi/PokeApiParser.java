package pokeapi;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A class used to parse the JSON from the response after making a PokeAPI GET request.
 */
public class PokeApiParser {
  /**
   * Given an API response made to the Pokemon or Types URL, parse the number of Pokemon in the
   * Pokedex or the number of types of Pokemon.
   * 
   * @param response the response from the API made to http://pokeapi.co/api/v2/pokemon/
   * @return the count (number of Pokemon in the Pokedex or number of types)
   * @throws IOException errors with parsing
   */
  public static int parseCount(String response) throws IOException {
    // Creates a StringReader to read the API response string
    StringReader strReader = new StringReader(response);
    // Creates a JSON reader to read the JSON from the API response string
    JsonReader jsonReader = new JsonReader(strReader);
    // Creates a JsonParser object to parse JSON
    JsonParser jsonParser = new JsonParser();
    // Gets the count
    int count = jsonParser.parse(jsonReader).getAsJsonObject().get("count").getAsInt();
    // Closes the readers
    jsonReader.close();
    strReader.close();
    return count;
  }
  
  /**
   * Given an API response made to the Types URL, parses the types, and returns a List of Pairs
   * (left side is the ID of the type, and right side is the type name).
   * @param response the response from the API made to http://pokeapi.co/api/v2/types
   * @return a List of Pairs (left side is the ID of the type, and right side is the type name).
   * @throws IOException errors when parsing
   */
  public static List<Map<Integer, String>> parseTypes(String response) throws IOException {
    // Creates a StringReader to read the API response string
    StringReader strReader = new StringReader(response);
    // Creates a JSON reader to read the JSON from the API response string
    JsonReader jsonReader = new JsonReader(strReader);

    // Creates a JsonParser object to parse JSON
    JsonParser jsonParser = new JsonParser();
    // Gets the results array
    JsonArray jsonArray =
        jsonParser.parse(jsonReader).getAsJsonObject().get("results").getAsJsonArray();

    // Creates a List of type pairs
    List<Map<Integer, String>> types = new ArrayList<>();

    for (int index = 0; index < jsonArray.size(); index++) {
      strReader = new StringReader(jsonArray.get(index).toString());
      jsonReader = new JsonReader(strReader);
      // Skips through the information we don't need
      jsonReader.beginObject();
      jsonReader.nextName();
      // Gets the URL which contains the ID number
      String url = jsonReader.nextString();
      // Parsing the substringed URL to get the ID
      int id = Integer
          .parseInt(url.substring(url.indexOf(PokeApiFetcher.TYPES_URL) 
              + PokeApiFetcher.TYPES_URL.length(), url.length() - 1));
      // Skips through the name
      jsonReader.nextName();
      // Gets the name
      String typeName = jsonReader.nextString();
      Map<Integer, String> type = new HashMap<>();
      type.put(id, typeName);
      types.add(type);
    }
    // Close the readers since we are done reading
    jsonReader.close();
    strReader.close();
    return types;
  }
  
}
