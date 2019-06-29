#!/usr/bin/perl -w

use strict;
use warnings;

my $input = '1113122113';

sub process {
	my $s = shift;
	my @list = split( /(?<=([0-9]))(?!\1)/, $s );
	my $output;
	while ( @list ) {
		my $length = length( shift( @list ) );
		my $what = shift( @list );
		$output .= $length . $what;
	}
	return $output;
}

my $x = $input;
foreach my $i ( 1 .. 50 ) {
	$x = process( $x );
} 
print $x, "\n";
print length( $x ), "\n";
