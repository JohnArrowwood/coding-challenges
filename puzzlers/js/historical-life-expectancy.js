#!/usr/bin/env node

var ancestry = JSON.parse( require( './ancestry.js' ) );

function sum( array ) {
  return array.reduce( function (a,b) { return a + b } );
}
function average( array ) {
  return sum(array) / array.length;
}
function keys( object ) {
  var result = [];
  for ( var key in object )
    if ( object.hasOwnProperty( key ) )
	result.push( key );
  return result;
}


function groupBy( array, predicate ) {
  var result = {};
  array.forEach( function(x) {
    p = predicate( x );
    if ( ! (p in result) ) result[p] = [];
    result[p].push( x );
  });
  return result;
}

function centuryOfDeath( p ) { return p.died - ( p.died % 100 ); }
function ageAtDeath( p ) { return p.died - p.born; }

var byCentury = groupBy( ancestry, centuryOfDeath );

var order = keys( byCentury );
order.sort( function(a,b) { return a - b } );

order.forEach( function (n) {
  console.log( n, average( byCentury[n].map( ageAtDeath ) ) );
});

