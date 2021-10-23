import java.util.*;

class mexstr {

  // Returns true if the substring is excluded in the word
  static boolean excluded(String word, String sub) {
    return !word.contains(sub);
  }

  // Returns the lexicographically next string
  static String addOne(char word[], int index) {
    // Add one to current index, and if it is too big then wrap
    if (++word[index] > 'z') {

      // Can't wrap first letter
      if (index == 0) return "DONE";

      // Reset letter
      word[index] = 'a';

      // Wrap
      return addOne(word, index-1);
    }
    // Reconstruct the String object
    return new String(word);
  }

  // Bruteforce every letter combination of a given length (in order)
  // Returns the substring if sucsessful. "" if not
  static String bruteLen(String word, int len) {
    // Create string of a's of length len
    String sub = "";
    for (int i = 0; i < len; i++) sub += "a";

    // Try every permutation
    while (true) {
      // If it's valid, populate res
      if (excluded(word, sub))
        return sub;

      // Keep adding
      if ((sub = addOne(sub.toCharArray(), sub.length()-1)) == "DONE")
        return "";
    }
  }

  // Solves a case
  static void solve(String word) {
    String res;

    // Try every possible length
    for (int i = 1; i <= word.length(); i++) {
      if ((res = bruteLen(word, i)) != "") {
        System.out.println(res);
        return;
      }
    }
  }

  public static void main(String[] args)
  {
    Scanner in = new Scanner(System.in);
    int tt = in.nextInt();
    for (int t = 1; t <= tt; t++)
      solve(in.next());    
  }
}
