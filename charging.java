import java.util.Scanner;

// charging.java by Jacob Steinebronn
public class charging {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);

        // Get the number of test cases
        int n = s.nextInt();

        // For each testcase
        for(int i = 0; i < n; i++){
            // Get the amount the battery is charged
            int battery = s.nextInt();
            // Print 100-battery, because that is how much
            // remains to be charged
            System.out.println(100-battery);
        }

        s.close();
    }
}