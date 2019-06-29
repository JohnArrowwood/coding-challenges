#!/usr/bin/env node

function sum( X ) {
  var t = 0;
  for ( var i = 0 ; i < X.length ; i++ ) {
    t += X[i];
  }
  return t;
}

function range( start, end, step ) {
  if ( step == undefined ) step = 1;
  var result = [];
  var reverse = false;
  if ( start > end && step < 1 ) {
    var t = start;
    start = end;
    end = t;
    step = -1 * step;
    reverse = true;
  } 
  else if ( ( start > end && step > 0 ) || ( start < end && step < 1 ) ) {
    throw new Exception("Invalid range parameters, nincompoop" );
  }
  for ( var i = start ; i <= end ; i += step ) { 
    result.push( i );
  }
  return ( reverse ? result.reverse() : result );
}

console.log( range( 1, 10 ) );
console.log( range( 5, 2, -1 ) );

console.log( sum( range( 1, 10 ) ) );
