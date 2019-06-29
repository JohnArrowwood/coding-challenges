package day11;

public class Day11 {

	private static String input = "vzbxkghb";

	private static Boolean contains_all_lowercase_letters(String s) {
		for (char c : s.toCharArray()) {
			if (c < 'a' || c > 'z') {
				return false;
			}
		}
		return true;
	}

	private static Boolean contains_i_o_l(String s) {
		for (char c : s.toCharArray()) {
			switch (c) {
			case 'i':
			case 'o':
			case 'l':
				return true;
			}
		}
		return false;
	}

	private static Boolean contains_straight(String s) {
		int i;
		char[] c = s.toCharArray();
		if (c.length < 3)
			return false;
		for (i = 2; i < c.length; i++) {
			if ((c[i - 2] == (c[i] - 2)) && (c[i - 1] == c[i] - 1))
				return true;
		}
		return false;
	}

	private static Boolean contains_multiple_non_overlapping_pairs(String s) {
		int i;
		char[] c = s.toCharArray();
		int pairs = 0;
		if (c.length < 4)
			return false;
		for (i = 1; i < c.length; i++) {
			if (c[i - 1] == c[i]) {
				pairs++;
				i++;
			}
		}
		return (pairs > 1);
	}

	private static Boolean isValid(String s) {
		return (contains_all_lowercase_letters(s) && !contains_i_o_l(s) && contains_straight(s)
				&& contains_multiple_non_overlapping_pairs(s));
	}

	private static String increment(String s) {
		Boolean done = false;
		char[] chars = s.toCharArray();
		int i = chars.length - 1;
		while (!done) {
			if (chars[i] == 'z') {
				chars[i] = 'a';
				i--;
				if (i < 0) {
					done = true;
				}
			} else {
				chars[i]++;
				done = true;
			}
		}
		return new String(chars);
	}

	private static String newPassword(String old) {
		String password = old;
		do {
			password = increment(password);
		} while (!isValid(password));
		return password;
	}

	public static void main(String[] args) {
		assert (contains_all_lowercase_letters("abcdefgh") == true);
		assert (contains_all_lowercase_letters("Abcdefgh") == false);
		assert (contains_all_lowercase_letters("abcdefgH") == false);
		assert (contains_all_lowercase_letters("abcDefgh") == false);

		assert (contains_i_o_l("abcdefgh") == false);
		assert (contains_i_o_l("abidefgh") == true);
		assert (contains_i_o_l("abodefgh") == true);
		assert (contains_i_o_l("abldefgh") == true);
		assert (contains_i_o_l("ibcdefgh") == true);
		assert (contains_i_o_l("obcdefgh") == true);
		assert (contains_i_o_l("lbcdefgh") == true);
		assert (contains_i_o_l("abcdefgi") == true);
		assert (contains_i_o_l("abcdefgo") == true);
		assert (contains_i_o_l("abcdefgl") == true);

		assert (contains_straight("aaaaaaaa") == false);
		assert (contains_straight("zyxwvuts") == false);
		assert (contains_straight("abababab") == false);
		assert (contains_straight("abcaaaaa") == true);
		assert (contains_straight("aabcaaaa") == true);
		assert (contains_straight("aaabcaaa") == true);
		assert (contains_straight("aaaabcaa") == true);
		assert (contains_straight("aaaaabca") == true);
		assert (contains_straight("aaaaaabc") == true);

		assert (contains_multiple_non_overlapping_pairs("abcdefgh") == false);
		assert (contains_multiple_non_overlapping_pairs("aacdefgh") == false);
		assert (contains_multiple_non_overlapping_pairs("aaadefgh") == false);
		assert (contains_multiple_non_overlapping_pairs("aaaaefgh") == true);
		assert (contains_multiple_non_overlapping_pairs("abbdefgh") == false);
		assert (contains_multiple_non_overlapping_pairs("abbccfgh") == true);
		assert (contains_multiple_non_overlapping_pairs("abbcddgh") == true);
		assert (contains_multiple_non_overlapping_pairs("abbcdeeh") == true);
		assert (contains_multiple_non_overlapping_pairs("aabbccdd") == true);
		assert (contains_multiple_non_overlapping_pairs("abcdeeff") == true);

		assert (increment("aaaaaaaa").equals("aaaaaaab"));
		assert (increment("aaaaaaab").equals("aaaaaaac"));
		assert (increment("aaaaaaay").equals("aaaaaaaz"));
		assert (increment("aaaaaaaz").equals("aaaaaaba"));
		assert (increment("aaaaaaza").equals("aaaaaazb"));
		assert (increment("aaaaaazz").equals("aaaaabaa"));
		assert (increment("aaaaazzz").equals("aaaabaaa"));
		assert (increment("aaaazzzz").equals("aaabaaaa"));
		assert (increment("aaazzzzz").equals("aabaaaaa"));
		assert (increment("aazzzzzz").equals("abaaaaaa"));
		assert (increment("azzzzzzz").equals("baaaaaaa"));
		assert (increment("zzzzzzzz").equals("aaaaaaaa"));

		System.out.println("isValid(hijklmmn) fails: " + (!isValid("hijklmmn")));
		System.out.println("isValid(abbceffg) fails: " + (!isValid("abbceffg")));
		System.out.println("isValid(abbcegjk) fails: " + (!isValid("abbcegjk")));

		assert (contains_all_lowercase_letters("abcdffaa") == true);
		assert (contains_i_o_l("abcdffaa") == false);
		assert (contains_straight("abcdffaa") == true);
		assert (contains_multiple_non_overlapping_pairs("abcdffaa") == true);
		assert (isValid("abcdffaa") == true);

		String np = newPassword("abcdefgh");
		System.out.println("abcdefgh is followed by abcdffaa:" + np.equals("abcdffaa") + " - " + np);

		np = newPassword("ghijklmn");
		System.out.println("ghijklmn is followed by ghjaabcc:" + np.equals("ghjaabcc") + " - " + np);

		System.out.println("Original password is: " + input);
		String password = input;
		password = newPassword(password);
		System.out.println("New password is:" + password);
		password = newPassword(password);
		System.out.println("Next new password is:" + password);

	}

}
