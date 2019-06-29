package day10;

public class Day10 {

	private static String input = "1113122113";

	public static String SayIt(String s) {
		StringBuilder result = new StringBuilder();
		if (s.length() < 1)
			return "";
		char current = s.charAt(0);
		int n = 1;
		for (int i = 1; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == current) {
				n++;
			} else {
				result.append(n);
				result.append(current);
				current = c;
				n = 1;
			}
		}
		result.append(n);
		result.append(current);
		return result.toString();
	}

	public static String sayItAgain(String s, int times) {
		String result = s;
		for (int i = 0; i < times; i++) {
			result = SayIt(result);
		}
		return result;
	}

	public static void main(String[] args) {
		System.out.println(sayItAgain(input, 40).length());
		System.out.println(sayItAgain(input, 50).length());
	}

}
