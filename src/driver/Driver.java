package driver;

import java.io.IOException;
import pokeapi.PokeApiFetcher;

/**
 * This class is used for getting all the Pokemon information from
 * PokeAPI which includes: making a GET request to PokeAPI, saving
 * the required information for a Pokedex into a SQLite database,
 * and saving all sprite information.
 */
public class Driver {
  /**
   * Runs the program (meant to simulate the Android application).
   * 
   * @param args no arguments needed
   */
  public static void main(String[] args) {
    try {
      System.out.println(PokeApiFetcher.getNumPokemon());
      /*
       * TODO: Create a Connection and make GET requests to each
       * Pokemon endpoint in the API, and write that information
       * to a SQLite database. Also, extract the sprites along the
       * way.
       */
    } catch (IOException e) {
      System.err.println("Uh-oh! Encountered an error: " + e.getMessage());
    }
  }

}
