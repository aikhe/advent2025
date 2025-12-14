import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day1 {
	public static void main(String[] args) {
		Rotation rotation = new Rotation("input.txt");

		try {
			List<String> lines = rotation.parseFile();
			for (String line : lines) {
				System.out.println(line);
			}
		} catch (FileNotFoundException e) {
			System.out.println("[Error] File not found: " + e.getMessage());
		}
	}
}

class Rotation {
	private String fileName;
	private int pointer = 50;

	public Rotation(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * Reads all lines from a file and returns them as a List of Strings.
	 *
	 * @return List of lines in the file
	 * @throws FileNotFoundException if the file cannot be read
	 */
	public List<String> parseFile() throws FileNotFoundException {
		List<String> lines = new ArrayList<>();
		File file = new File(fileName);

		try (Scanner scanner = new Scanner(file)) {
			while (scanner.hasNextLine()) {
				lines.add(scanner.nextLine());
			}
		}

		return lines;
	}

	// public static String parseInput(String input) {
	//
	// }
}
