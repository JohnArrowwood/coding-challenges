//#!/usr/bin/env scala

import java.io.InputStream
import scala.io.Source

object Day01 {

  case class Santa ( val floor : Int, val enteredBasement : Int )

  def endingFloor ( s : String ) = {
    def f( floor : Int, s : String ) : Int = {
      if ( s.length == 0 ) floor
      else {
        s.head match {
          case '(' => f( floor + 1, s.tail )
          case ')' => f( floor - 1, s.tail )
        }
      }
    }
    f( 0, s )
  }

  def stepEnteredBasement ( s : String ) = {
    def f( step : Int, floor : Int, s : String ) : Int = {
      if ( floor < 0 ) step
      else {
        s.head match {
          case '(' => f( step + 1, floor + 1, s.tail )
          case ')' => f( step + 1, floor - 1, s.tail )
        }
      }
    }
    f( 0, 0, s )
  }

  def combined ( s : String ) : Santa = {
    def f( step : Int, floor : Int, basement : Int, s : String ) : Santa = {
      if ( s.length == 0 ) new Santa( floor, basement )
      else {
        val next_step  = step + 1
        val next_floor = s.head match {
          case '('     => floor + 1
          case ')'     => floor - 1
          case default => floor
        }
        val next_basement = if ( basement == 0 && floor < 0 ) step else basement
        f( next_step, next_floor, next_basement, s.tail )
      }
    }
    f( 0, 0, 0, s )
  }

  def main( args: Array[String] ) : Unit = {
    val is : InputStream = getClass.getResourceAsStream("day-01.txt")
    val input = Source.fromInputStream( is ).getLines().toList.head

    val santa = combined( input )
    println( "Santa ends up on floor " + santa.floor )
    println( "Santa first enters the basement on step " + santa.enteredBasement )
  }

}
