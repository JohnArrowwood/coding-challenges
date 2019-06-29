package day08;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Day08 {

	static private List<String> inputs = load();

	private static List<String> load() {
		List<String> list = new ArrayList<String>();
		try {
			try (Stream<String> lines = Files.lines(Paths.get("input", "day-08.txt"), StandardCharsets.UTF_8)) {
				lines.forEach(s -> list.add(s));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	private static int decodedLength(String s) {
		String t = s.replaceAll("^\"(.*)\"$", "$1");
		return t.replaceAll("(?:\\\\(?:x[0-9a-fA-F][0-9a-fA-F]|\"|\\\\)|.)", "-").length();
	}

	private static void part1() {
		int total = inputs.stream().mapToInt(s -> s.length() - decodedLength(s)).sum();
		System.out.println("String length - decoded length = " + total);
	}

	private static int encodedLength(String s) {
		return (s.replaceAll("[\\\\\"]", "--").length() + 2);
	}

	private static void part2() {
		int total = inputs.stream().mapToInt(s -> encodedLength(s) - s.length()).sum();
		System.out.println("Encoded length - String length = " + total);
	}

	public static void main(String[] args) {
		part1();
		part2();
	}

}
