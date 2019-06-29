#!/usr/bin/env node

var fs = require( 'fs' );

var santa = {
  floor: 0,
  step: 0,
  entered_basement: 0,

  goUp: function(){
    this.step++;
    this.floor++;
  },

  goDown: function(){
    this.step++;
    this.floor--;
    if ( this.floor < 0 && ! this.entered_basement )
      this.entered_basement = this.step;
  }
};


fs.readFile( __dirname + '/day-01.txt', function( err, data ) {
  if ( err ) {
    throw err;
  }
  s = data.toString();
  var l = s.length;
  var i;
  var c;
  for ( i = 0 ; i < l ; i++ ) {
    c = s.charAt(i);
    if      ( c == '(' ) santa.goUp()
    else if ( c == ')' ) santa.goDown()
  }
  console.log( "Santa ends up on floor", santa.floor );
  console.log( "Santa first enters the basement on step ", santa.entered_basement );
});
