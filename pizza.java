import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class pizza {
    static int n, m, k;
    static int[][][] dp;
    static int cap;
    static ArrayList<Integer> dislikes;
    public static void main(String[] args) throws FileNotFoundException {
        //When you see this problem just imagine to yourself, what if you brought gum to school:
        //Everybody gonna be like "yo, can you share with the class"? Then you tell em, nah it's orange flavor,
        //you won't like it. Half the people stop coming after you, but half will be like "yo, I don't mind."
        //That's when you tell em "ye but it's also watermelon flavored." A few more of the remaining people will
        //stop coming after you.
        //Just imagine how you would personally act and code it.
        Scanner scan = new Scanner(System.in);
        int d = scan.nextInt();
        while (d-- > 0) {
            n = scan.nextInt();
            m = scan.nextInt();
            k = scan.nextInt();
            /*If k > n, then for each person you can just select a topping that they hate
            and they won't ask you to share with the class anymore, so you need to use at most n different toppings
            */
            if(k>n)
                k = n;
            cap = 1<<n;
            dp = new int[cap][cap][k+1];
            for(int i = 0;i<cap;i++){
                for(int j = 0;j<cap;j++) {
                    Arrays.fill(dp[i][j], -1);
                }
            }
            //This List will store for each topping, the mask of people that hate it.
            //The maximum number of people that can hate a topping is 10, therefore the maximum value of each mask
            //Will be 2^10
            dislikes = new ArrayList<>();
            for(int i = 0;i<m;i++){
                dislikes.add(0);
            }
            for(int i = 0;i<n;i++){
                int t = scan.nextInt();
                for(int j =0;j<t;j++){
                    int currTopping = scan.nextInt()-1;
                    int maskHere = dislikes.get(currTopping);
                    //marks the mask, setting the value at index i to 1, showing that person i does not like
                    //this topping
                    maskHere |= 1<<i;
                    dislikes.set(currTopping,maskHere);
                }
            }
            //Convert the dislikes list to a hashset
            //Since hashsets dont store duplicates, and max value is 2^10, max size of set will be 2^10
            HashSet<Integer> disliked = new HashSet<>();
            for(Integer curr:dislikes){
                disliked.add(curr);
            }
            //Put it back in a list
            dislikes = new ArrayList<Integer>();
            for(Integer curr:disliked){
                dislikes.add(curr);
            }
            int ans = go(0,0,k);
            System.out.println(n-ans);
        }
    }
    static int go(int topping, int mask, int left){
        //recursive function to explore all possible dp states.
        if(left==0 || topping==dislikes.size()){
            //you can no longer add toppings
            //and your mask is not all ones, that means you haven't kicked out all the people,
            //that means your mask is not equal to 2^n-1
            //well you will have a few people still begging you to share with the class.
            String s = Integer.toBinaryString(mask);
            int count =0;
            for(int i = 0;i<s.length();i++){
                if(s.charAt(i)=='1'){
                    //that means we successfully made this person not want pizza
                    count++;
                }
            }
            return count;
        }
        //Find out the highest # of people we can kick off from this state.
        int best = 0;
        //our transitions are:
        //take the topping and edit our mask accordingly
        best = go(topping+1,mask|dislikes.get(topping),left-1);
        //not take the topping and no changes occur.
        best = Math.max(best,go(topping+1,mask,left));
        return dp[topping][mask][left] = best;
    }
}
