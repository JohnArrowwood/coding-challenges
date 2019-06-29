#!/usr/bin/env node

function repeat(string, times) {
  var result = "";
  for (var i = 0; i < times; i++)
    result += string;
  return result;
}

function TextCell(text) {
  this.text = text.split("\n");
}
TextCell.prototype.minWidth = function() {
  return this.text.reduce(function(width, line) {
    return Math.max(width, line.length);
  }, 0);
};
TextCell.prototype.minHeight = function() {
  return this.text.length;
};
TextCell.prototype.draw = function(width, height) {
  var result = [];
  for (var i = 0; i < height; i++) {
    var line = this.text[i] || "";
    result.push(line + repeat(" ", width - line.length));
  }
  return result;
};

function StretchedCell(inner, min_width, min_height) {
  this.inner      = inner;
  this.min_width  = min_width;
  this.min_height = min_height;
}
var p = StretchedCell.prototype;
p.minWidth = function() {
  return Math.max( this.inner.minWidth(), this.min_width );
};
p.minHeight = function() {
  return Math.max( this.inner.minHeight() + 1, this.min_height );
};
p.draw = function( width, height ) {
  return this.inner.draw( width, height );
}

var sc = new StretchedCell(new TextCell("abc"), 1, 2);
console.log(sc.minWidth());
// → 3
console.log(sc.minHeight());
// → 2
console.log(sc.draw(3, 2));
// → ["abc", "   "]
