#!/usr/bin/env node

var test = [[1,2,[3,4]],[5,6],[7]];

function flatten( a ) {
  if ( typeof a == 'object' && "reduce" in a ) {
    return a.reduce( function( old, next ) {
      return old.concat( flatten( next ) );
    }, [] );
  } else {
    return a;
  }
}

console.log( flatten( test ) );
