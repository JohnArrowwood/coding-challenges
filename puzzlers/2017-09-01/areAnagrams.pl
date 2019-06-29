#!/usr/bin/perl -w

use strict;
use warnings;

# Fastest only because this is an interpreted language
sub fastest {
    my $s1 = join('',sort(split('',shift)));
    my $s2 = join('',sort(split('',shift)));
    return $s1 eq $s2;
}

# most readable
sub withCharactersSorted {
    my $s = shift;
    my @as_array = split( '', $s );
    my @sorted = sort ( @as_array );
    my $as_string = join( '', @sorted );
    return $as_string;
}
sub readable {
    my $s1 = withCharactersSorted( shift );
    my $s2 = withCharactersSorted( shift );
    return $s1 eq $s2;
}

# most memory efficient
# would be most CPU efficient, too, in a compiled language
sub efficient {
    my $s1 = shift;
    my $s2 = shift;

    my $l1 = length( $s1 );
    my $l2 = length( $s2 );

    # if the lengths are not the same, they are not anagrams, period
    return 0 unless $l1 == $l2;

    my $i; # index/offset
    my $c; # character
    my %count; # count 

    # count how many there are of each character in the first string
    for ( $i = 0 ; $i < $l1 ; $i++ ) {
        $c = substr( $s1, $i, 1 );
        $count{$c}++;
    }

    # count how many there are of each character in the second string
    # but decrement instead of increment
    # so that for every character that appears the same number of times in both strings,
    # the value will be 0
    for ( $i = 0 ; $i < $l2 ; $i++ ) {
        $c = substr( $s2, $i, 1 );
        $count{$c}--;
    }

    # if any values are not 0, it is clearly not an anagram 
    foreach $c ( keys %count ) {
        return 0 unless $count{$c} == 0;
    }
    return 1
}

sub areAnagrams {
    # put the one to test first
    return efficient(@_);
    return readable(@_);
    return fastest(@_);
}

sub true {
    my $a = shift;
    my $b = shift;
    if ( ! areAnagrams( $a, $b ) ) {
        print "'$a' and '$b' are supposed to be anagrams, but the function returned false";
        die;
    }
}
sub false {
    my $a = shift;
    my $b = shift;
    if ( areAnagrams( $a, $b ) ) {
        print "'$a' and '$b' are not supposed to be anagrams, but the function returned true";
        die;
    }
}

print( "Testing anagrams\n" );
true( "", "" );
true( "a", "a" );
true( "abc", "abc" );
true( "abc", "acb" );
true( "abc", "bac" );
true( "abc", "bca" );
true( "abc", "cab" );
true( "abc", "cba" );

print( "Testing non-anagrams\n" );
false( "abcd", "abc" );
false( "abc", "xyz" );
false( "aabbcc", "aaabbc" );

print( "Evaluating speed - hope you are timing!\n" );
for ( my $i = 0 ; $i < 1000000 ; $i++ ) {
    true( "HpvgWMKtaFCEVw3P4qy5Iors92xA7OzdcmXNkUnTLjJZe1blYDf6Q8uB0iGSRh",
          "PgKTi5mjF4nUd93WEZN7sQce1kuXoRpLtzCYVM02IlaOByGrvf6qJHwSDAbxh8"
      );
}
