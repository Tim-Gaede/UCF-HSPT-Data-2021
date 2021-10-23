import java.util.Scanner;

public class nomor {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		int l = scan.nextInt();
		// scan.nextInt() doesn't scan in the \n character, so this is
		// necessary to advance the scanner to the first line of input.
		scan.nextLine(); 

		for (int i = 0; i < l; i++) {
			String line = scan.nextLine();
			// For each character in the line, print if the character is not 'e'
			for (int j = 0; j < line.length(); j++) {
				if (line.charAt(j) != 'e') {
					System.out.print(line.charAt(j));
				}
			}
			// Finish the line.
			System.out.println();
		}
	}
}
