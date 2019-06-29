#!/usr/bin/env node

function deepEquals( a, b ) {
  if ( a === b ) return true;
  if ( typeof a != typeof b ) return false;
  var key;
  for ( key in a ) if ( !( key in b ) ) return false;
  for ( key in b ) if ( !( key in a ) ) return false;
  for ( key in a ) {
    if ( ! deepEquals( a[key], b[key] ) ) return false;
  }
  return true;
}

var obj = {here: {is: "an"}, object: 2};
console.log(deepEquals(obj, obj));
// → true
console.log(deepEquals(obj, {here: 1, object: 2}));
// → false
console.log(deepEquals(obj, {here: {is: "an"}, object: 2}));
// → true
