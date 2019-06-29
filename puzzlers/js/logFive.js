#!/usr/bin/env node

function logFive( iterable ) {
  var n = 5;
  while ( iterable.hasNext() && (n-- > 0) ) {
    console.log( iterable.next() );
  }
}

function ArraySeq( a ) {
  this.data = a;
  this.pos = 0;
}
var p = ArraySeq.prototype;
p.hasNext = function() { return this.pos < this.data.length; }
p.next    = function() { return this.data[this.pos++]; }
p.reset   = function() { this.pos = 0; }

function RangeSeq( start, end, step ) {
  if ( step == undefined ) step = ( start < end ? 1 : -1 );
  if ( ( start < end && step < 1 ) || ( start > end && step > 0 ) ) {
    throw new Exception( "Invalid step value for range" );
  }
  this.start   = start;
  this.end     = end;
  this.step    = step;
  this.current = start;
}
p = RangeSeq.prototype;
p.isValid = function( i ) { return ( this.step > 0 ? i <= this.end : i >= this.end ); }
p.hasNext = function() { return this.isValid( this.current + this.step ); }
p.next    = function() { 
  var n = this.current + this.step;
  if ( this.isValid( n ) ) {
    this.current = n;
    return n;
  } else {
    throw new Exception( "Attempt to call Next on an empty iterator" );
  }
}
p.reset   = function() { this.current = this.start; }

logFive(new ArraySeq([1, 2]));
// → 1
// → 2
logFive(new RangeSeq(100, 1000));
// → 100
// → 101
// → 102
// → 103
// → 104
