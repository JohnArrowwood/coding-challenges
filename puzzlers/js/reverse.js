#!/usr/bin/env node

function reverseArrayInPlace( a ) {
  var end = a.length - 1;
  var last = Math.floor( a.length / 2 )
  var t,n;
  for ( i = 0 ; i <= last ; i++ ) {
    n = end - i;
    t = a[i];
    a[i] = a[n];
    a[n] = t;
  }
}

function reverseArray( a ) {
  var result = [];
  for ( var i = a.length - 1 ; i >= 0 ; i-- ) {
    result.push( a[i] );
  }
  return result;
}

console.log(reverseArray(["A", "B", "C"]));
// → ["C", "B", "A"];
var arrayValue = [1, 2, 3, 4, 5];
reverseArrayInPlace(arrayValue);
console.log(arrayValue);
// → [5, 4, 3, 2, 1]

    
