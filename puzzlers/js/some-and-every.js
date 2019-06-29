#!/usr/bin/env node

function some( a, p ) {
  for ( var i = 0 ; i < a.length ; i++ )
    if ( p( a[i] ) ) return true;
  return false;
}

function every( a, p ) {
  for ( var i = 0 ; i < a.length ; i++ )
    if ( ! p( a[i] ) ) return false
  return true;
}

console.log(every([NaN, NaN, NaN], isNaN));
// → true
console.log(every([NaN, NaN, 4], isNaN));
// → false
console.log(some([NaN, 3, 4], isNaN));
// → true
console.log(some([2, 3, 4], isNaN));
// → false
