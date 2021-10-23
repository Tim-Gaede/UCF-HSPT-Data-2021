import java.util.*;
import java.io.*;

public class dvd{

	public static void main(String[] args) throws IOException{
		Scanner scan = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int t = scan.nextInt();
		for(int q = 1; q <= t; q++){
			int w1 = scan.nextInt();
			int h1 = scan.nextInt();
			int w2 = scan.nextInt();
			int h2 = scan.nextInt();

			// We can pretend the dvd logo is 1x1 if we subtract the sizes
			h2 -= h1;
			w2 -= w1;

			// initialize position of dvd and direction of dvd
			int px = 0;
			int py = 0;
			int dx = 1;
			int dy = 1;

			int moves = 0;
			while(true){
				// simulate a move
				moves++;
				px += dx;
				py += dy;

				// flipX is on if we are touching a vertical side of the screen
				// similarly flipY is on if we are touching a horizontal side
				boolean flipX = px == 0 || px == w2;
				boolean flipY = py == 0 || py == h2;

				// we are in a corner
				if(flipX && flipY){
					out.println(moves);
					break;
				}

				// if we need to flip, change the direction
				if (flipX) dx *= -1;
				if (flipY) dy *= -1;
			}
		}
		out.flush();
	}
}
