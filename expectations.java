import java.util.*; //import statements
import java.io.*;
public class expectations {
	public static void main(String[] args) throws IOException {
		
		Scanner scan = new Scanner(System.in); //create scanner

        	long T = scan.nextLong(); //reads in the value of t

        	for(int i = 0; i < T; i++) { //loops t times (once for each of the next lines)

            		long answer = 0; //resets the answer for each new line

            		long n = scan.nextLong(); //reads in the value of n in the specific line

            		long min = scan.nextLong(); //reads in the first score
            		long temp = 0; //a temporary integer

           		for(int j = 1; j < n; j++) { //starts at j=1 because the first score is already read by minimum

                		temp = scan.nextLong();//gets the next score to compare
                		min++; //increases the minimum needed to improve on the current year

                		if(min > temp) {//This shows that the person needs to gain points
                    			answer = answer + min - temp; //This shows how many points are needed to avoid banishment
                		}
                		else if(temp > min) {//This means the person exceeded the minimum requirement and the next year the minimum needed to improve will be greater then expected
                    			min = temp;//This adjusts the minimum needed to improve to the correct value
                		}
            		}

            		System.out.println(answer); //prints out the answer
        	}
		
        	scan.close(); //closes the Scanner
	}
}













