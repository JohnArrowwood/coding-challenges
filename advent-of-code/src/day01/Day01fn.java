package day01;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/* FUNCTIONAL solution */
public class Day01fn {

  private static int traverse( String instructions, int step, int floor ) {
    if ( step >= instructions.length() ) {
      return floor;
    } else {
      char c = instructions.charAt( step );
      if ( c == '(' ) {
        return traverse( instructions, step + 1, floor + 1 );
      } else if ( c == ')' ) {
        return traverse( instructions, step + 1, floor - 1 );
      } else {
        return traverse( instructions, step + 1, floor );  // better never happen
      }
    }
  }

  public static int endingFloor( String instructions ) {
    return traverse( instructions, 0, 0 );
  }

  private static int traverseUntilBasement( String instructions, int step, int floor ) {
    if ( floor < 0 ) {
      return step;
    } else {
      char c = instructions.charAt( step );
      if ( c == '(' ) {
        return traverseUntilBasement( instructions, step + 1, floor + 1 );
      } else if ( c == ')' ) {
        return traverseUntilBasement( instructions, step + 1, floor - 1 );
      } else {
        return traverseUntilBasement( instructions, step + 1, floor );  // better never happen
      }
    }
  }

  public static int enteredBasement( String instructions ) {
    return traverseUntilBasement( instructions, 0, 0 );
  }

  public static void main( String[] args ) 
  throws IOException
  {
    byte[] encoded = Files.readAllBytes( Paths.get( "input", "day-01.txt" ) );
    String instructions = new String( encoded, StandardCharsets.UTF_8 );
    System.out.println( "Santa ends up on floor " + endingFloor( instructions ) );
    System.out.println( "Santa first enters the basement on step " + enteredBasement( instructions ) );
  }
}
