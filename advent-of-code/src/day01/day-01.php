#!/usr/bin/env php
<?php

$data = file_get_contents( "day-01.txt" );

$floor = 0;
$entered_basement = 0;

$l = strlen( $data );
for ( $i = 0 ; $i < $l ; $i++ ) {
  if ( $data[$i] == '(' ) {
    $floor += 1;
  } else if ( $data[$i] == ')' ) {
    $floor -= 1;
    if ( $entered_basement == 0 && $floor < 0 ) {
      $entered_basement = $i + 1;
    }
  }
}

echo "Santa ended up on floor: ", $floor, "\n";
echo "Santa first entered the basement on step: ", $entered_basement, "\n";
