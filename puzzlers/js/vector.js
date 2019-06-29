#!/usr/bin/env node

function Vector( x, y ) {
  this.x = x;
  this.y = y;
}
p = Vector.prototype;
p.plus = function( v ) {
  return new Vector( this.x + v.x, this.y + v.y );
}
p.minus = function( v ) {
  return new Vector( this.x - v.x, this.y - v.y );
}
Object.defineProperty( p, "length", {
  configurable: false,
  enumerable: true,
  get: function() {
    return Math.sqrt( Math.pow( this.x, 2 ) + Math.pow( this.y, 2 ) );
  }
});

console.log(new Vector(1, 2).plus(new Vector(2, 3)));
// → Vector{x: 3, y: 5}
console.log(new Vector(1, 2).minus(new Vector(2, 3)));
// → Vector{x: -1, y: -1}
console.log(new Vector(3, 4).length);
// → 5


