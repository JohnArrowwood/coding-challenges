package day04;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Day04 {

	private static String input = "ckczppom";

	public static int mineForAdventCoins(int startAt, int leadingZeros) throws NoSuchAlgorithmException {
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		int i = startAt - 1;
		String hash;
		do {
			i++;
			String text = input + i;
			md5.reset();
			md5.update(text.getBytes());
			hash = new BigInteger(1, md5.digest()).toString(16);

		} while (hash.length() > (32 - leadingZeros));
		return i;
	}

	public static void main(String[] args) throws NoSuchAlgorithmException {
		System.out.println("First part:" + mineForAdventCoins(1, 5));
		System.out.println("Second part:" + mineForAdventCoins(1, 6));
	}
}
