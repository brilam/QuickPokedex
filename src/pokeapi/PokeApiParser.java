package pokeapi;

import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import java.io.IOException;
import java.io.StringReader;


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
}
