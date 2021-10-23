
import java.util.Scanner;

public class mtx
{
   public static void main(String[] args) 
   {
      // Read in the number of servers
      Scanner scan = new Scanner(System.in);
      int t = scan.nextInt();

      // Loop through the servers
      for (int i = 0; i < t; i++) 
      {
         // Get the number of players
         int n = scan.nextInt();

         // Loop over the players
         int maxPowerForFreePlayer = -1;
         int maxPowerForPaidPlayer = -1;
         for (int j = 0; j < n; j++) 
         {
            // Get the skill level and money spent for this player
            int s = scan.nextInt();
            int m = scan.nextInt();

            // Compute power for this player
            int power = s * (1000 + m);

            // Track various maximums
            if ( (power > maxPowerForFreePlayer) && (m == 0) )
               maxPowerForFreePlayer = power;
            if ( (power > maxPowerForPaidPlayer) && (m > 0) )
               maxPowerForPaidPlayer = power;
         }

         // Output accordingly
         if (maxPowerForFreePlayer > maxPowerForPaidPlayer)
            System.out.println("YES");
         else
            System.out.println("NO");
      }
   }
}

