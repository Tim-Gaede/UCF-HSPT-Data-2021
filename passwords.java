
import java.util.Scanner;

public class passwords
{
   public static void main(String[] args) 
   {
      // Read in the number of passwords
      Scanner scan = new Scanner(System.in);
      int n = scan.nextInt();
      scan.nextLine();

      // Loop through the passwords
      for (int i = 0; i < n; i++) 
      {
         // Get this password
         String password = scan.nextLine();

         // Loop through string, print the uppercase letters
         for (int j = 0; j < password.length(); j++) 
         {
            char ch = password.charAt(j);
            if (Character.isUpperCase(ch))
               System.out.print(ch);
         }

         // Loop through string, print the lowercase letters
         for (int j = 0; j < password.length(); j++) 
         {
            char ch = password.charAt(j);
            if (Character.isLowerCase(ch))
               System.out.print(ch);
         }

         // Output newline
         System.out.println();
      }
   }
}

