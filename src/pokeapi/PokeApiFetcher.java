package pokeapi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class PokeApiFetcher {
  public static final String API_URL = "https://pokeapi.co/api/v2/";
  public static final String POKEMON_URL = "pokemon/";
  private static final int FAILED = -1;

  /**
   * Returns the number of Pokemon in the Pokedex by making a GET request to the Pokemon URL of
   * PokeAPI and parsing the count.
   * 
   * @return the number of Pokemon in the Pokedex
   */
  public static int getNumPokemon() throws IOException {
    int numPokemon = FAILED;
    // Makes a URL object given the Pokemon url
    URL url = new URL(API_URL + POKEMON_URL);
    // Gets the response of the GET request
    String response = getApiResponse(url);
    numPokemon = PokeApiParser.parseCount(response);
    return numPokemon;
  }

  /**
   * Returns the API response from the GET request given a url.
   * 
   * @param url the URL to connect to and make a GET request
   * @return the response that the API provides back
   * @throws IOException any errors that occurs such as a bad HTTP response, parsing issues with
   *         JSON, etc.
   */
  private static String getApiResponse(URL url) throws IOException {
    // Makes a connection to the URL and makes a GET request
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    connection.setRequestProperty("User-Agent", "");
    connection.setRequestMethod("GET");

    // Get the response
    InputStream inputStream = connection.getInputStream();
    BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

    String currentLine;
    String response = "";
    // Loops through each line and concatenates it to our response
    while ((currentLine = br.readLine()) != null) {
      response += currentLine;
    }

    // Closes the BufferedReader and InputStream
    br.close();
    inputStream.close();
    return response;
  }
}
