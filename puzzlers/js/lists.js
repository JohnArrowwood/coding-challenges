#!/usr/bin/env node

function arrayToList( a ) {
  function f( i ) {
    if ( i == a.length - 1 ) {
      return {
        head: a[i],
        tail: null
      };
    } else {
      return {
        head: a[i],
        tail: f(i+1)
      };
    }
  }
  return f( 0 );
}

function listToArray( l ) {
  var a = [];
  e = l;
  while ( e ) {
    a.push( e.head );
    e = e.tail;
  }
  return a;
}

function prepend ( e, l ) {
  return {
    head: e,
    tail: l
  };
}

function nth ( l, n ) {
  p = l;
  while ( p && n ) {
    p = p.tail;
    n--;
  }
  return ( p ? p.head : undefined );
}

function nthRecursive ( l, n ) {
  if ( l == null ) {
    return undefined;
  } 
  if ( n == 0 ) {
    return l.head;
  }
  return nthRecursive( l.tail, n - 1 );
}


console.log(arrayToList([10, 20]));
// → {value: 10, rest: {value: 20, rest: null}}
console.log(listToArray(arrayToList([10, 20, 30])));
// → [10, 20, 30]
console.log(prepend(10, prepend(20, null)));
// → {value: 10, rest: {value: 20, rest: null}}
console.log(nth(arrayToList([10, 20, 30]), 1));
// → 20
