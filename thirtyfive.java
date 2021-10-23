
import java.util.Scanner;

public class thirtyfive
{
   public static void main(String[] args) 
   {
      // Read in the number of cases
      Scanner scan = new Scanner(System.in);
      int t = scan.nextInt();

      // Loop through the cases
      for (int i = 0; i < t; i++) 
      {
         // Get the two digits
         int s = scan.nextInt();
         int o = scan.nextInt();

         // Output the answer (s * 6 + o)
         System.out.println(s * 6 + o);
      }
   }
}

