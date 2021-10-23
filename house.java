import java.util.Scanner;

public class house {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int t = scan.nextInt();
		for (int i = 0; i < t; i++) {
			int s = scan.nextInt();
			// The outer perimeter is composed of 5 pieces, all of the length
			// s. This makes the perimeter equal to 5 * s.
			int perimeter = s * 5;
			System.out.println(perimeter);
		}
	}
}
