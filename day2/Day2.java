import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day2 {
	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("Hello, Day 2!");

		Range input = new Range("input.txt");
		List<String> ranges = input.parseFile();

		int id = 0;
		for (String range : ranges) {
			System.out.println("Range " + id + ": " + range);
			id++;
		}
	}
}

class Range {
	private final String fileName;

	public Range(String fileName) {
		this.fileName = fileName;
	}

	public List<String> parseFile() throws FileNotFoundException {
		List<String> ranges = new ArrayList<>();
		File file = new File(fileName);
		Scanner scanner = new Scanner(file);
		// use reges to get each reange separated by comma
		scanner.useDelimiter("\\s*,\\s*");

		while (scanner.hasNext()) {
			String range = scanner.next();
			ranges.add(range);
		}

		scanner.close();
		return ranges;
	}
}
