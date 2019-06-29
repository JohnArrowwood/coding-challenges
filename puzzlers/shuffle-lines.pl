#!/usr/bin/perl -w

use strict;
use warnings;

sub shuffle {
    my $list = shift;
    my ( $i, $n, $l, $t );
    $l = $#{$list};
    while ( $l ) {
        $n = int(rand()*$l);
        $t = $list->[$l];
        $list->[$l] = $list->[$n];
        $list->[$n] = $t;
        $l--;
    }
}

my @list;
while ( <STDIN> ) {
  push @list, $_;
}
shuffle( \@list );
print join( '', @list ), "\n";
