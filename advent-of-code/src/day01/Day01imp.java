package day01;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/* IMPERATIVE PROGRAMMING solution */
public class Day01imp {

  public static void main( String[] args ) 
  throws IOException
  {
    byte[] encoded = Files.readAllBytes( Paths.get( "input", "day-01.txt" ) );
    String instructions = new String( encoded, StandardCharsets.UTF_8 );

    int num_instructions = instructions.length();

    int floor = 0;
    int entered_basement = 0;
    int step;
    char i;

    for ( step = 0 ; step < num_instructions ; step++ ) {
      i = instructions.charAt( step );
      if ( i == '(' ) {
        floor++;
      } else if ( i == ')' ) {
        floor--;
        if ( entered_basement == 0 && floor < 0 ) {
          entered_basement = step + 1;
        }
      }
    }
    System.out.println( "Santa ends up on floor " + floor );
    System.out.println( "Santa first enters the basement on step " + entered_basement );
  }
}
