package day05;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Day05 {

	private static Pattern original = Pattern
			.compile("^" + "(?=.*?[aeiou].*?[aeiou].*?[aeiou])" + "(?=.*?(.)\\1)" + "(?!.*?(?:ab|cd|pq|xy))");

	private static Pattern revised = Pattern.compile("^" + "(?=.*?(..).*?\\1)" + "(?=.*?(.).\\2)");

	private static List<String> loadInput() {
		List<String> inputs = new ArrayList<String>();
		try {
			try (Stream<String> lines = Files.lines(Paths.get("input", "day-05.txt"), StandardCharsets.UTF_8)) {
				lines.forEach(word -> inputs.add(word));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return inputs;
	}

	private static List<String> inputs = loadInput();

	public static int numNice(Pattern isNice) {
		int total = 0;
		for (String word : inputs) {
			if (isNice.matcher(word).find()) {
				total++;
			}
		}
		return total;
	}

	public static void main(String[] args) {
		System.out.println("By the original rules, " + numNice(original) + " words are 'nice'");
		System.out.println("By the revised rules, " + numNice(revised) + " words are 'nice'");
	}

}
