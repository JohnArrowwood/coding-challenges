package day01;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/* OBJECT-ORIENTED solution (encapsulated imparative) */
public class Day01oo {
  
  private int floor = 0;
  private int step = 0;
  private int entered_basement = 0;

  public void follow( String instructions ) {
    int i;
    int l = instructions.length();
    for ( i = 0 ; i < l ; i++ ) {
      followInstruction( instructions.charAt(i) );
    }
  }

  public void followInstruction( char c ) {
    step++;
    if ( c == '(' ) {
      floor++;
    } else if ( c == ')' ) {
      floor--;
      if ( entered_basement == 0 && floor < 0 ) {
        entered_basement = step;
      }
    }
  }

  public int onFloor() { return floor; }
  public int enteredBasement() { return entered_basement; }


  public String getInstructions() 
  throws IOException
  {
    byte[] encoded = Files.readAllBytes( Paths.get( "input", "day-01.txt" ) );
    return new String( encoded, StandardCharsets.UTF_8 );
  }

  public static void main( String[] args ) 
  throws IOException
  {
    Day01oo santa = new Day01oo();
    santa.follow( santa.getInstructions() );
    System.out.println( "Santa ends up on floor " + santa.onFloor() );
    System.out.println( "Santa first enters the basement on step " + santa.enteredBasement() );
  }
}
