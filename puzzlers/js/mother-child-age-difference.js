#!/usr/bin/env node

var ancestry = JSON.parse( require( './ancestry.js' ) );

function sum( array ) {
  return array.reduce( function (a,b) { return a + b } );
}
function average( array ) {
  return sum(array) / array.length;
}

var byName = {};
ancestry.forEach( function (p) {
  byName[p.name] = p;
});

function motherIsKnown ( p ) {
  return ( p.mother in byName );
}
function ageDifference( p ) {
  return ( p.born - byName[p.mother].born );
}

var ageDifferences = ancestry.filter( motherIsKnown ).map( ageDifference );
console.log( average( ageDifferences ) );
