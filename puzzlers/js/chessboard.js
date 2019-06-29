#!/usr/bin/env node

size=100
output = "";

for ( y = 0 ; y < size ; y++ ) {
  for ( x = 0 ; x < size ; x++ ) {
    output += ( (x + y) % 2 ? "##" : "  " );
  }
  output += "\n";
}
console.log( output );
