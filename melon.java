// 2021 UCF HSPT - Interstellon Melon
// Author: Sathvik Kuthuru

import java.util.Scanner;

public class melon {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // Scan in the number of melons we have to look at
        int numTests = scan.nextInt();
        for(int i = 0; i < numTests; i++) {
            // Scan in the coordinates for the melon
            int x = scan.nextInt(), y = scan.nextInt();
            // We can only travel in straight lines, no diagonals
            // Therefore getting to the location is just the manhattan distance, x + y
            int oneWayDist = x + y;
            // A full round trip is just the distance * 2
            int fullTrip = oneWayDist * 2;
            System.out.println(fullTrip);
        }
    }
}
