// 2021 UCF HSPT - Knapsack?
// Author: Natalie Longtin

/*
the traditional knapsack problem can be solved with a dynamic programming
algorithm in O(nm) time, where n is the number of items and m is the capacity of the knapsack.
for this problem, however, this approach is too slow due to the large bounds on m.

consider reversing the problem: start by taking every single object, and then run
knapsack to find the minimum value of items that we must remove to get below the allowed capacity.
taking into account the unusual bounds, observe that we will only need to consider
removing items with a total weight of 55,000 or less.
*/

import java.util.Arrays;
import java.util.Scanner;

public class knapsack {
	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		
		//process test cases
		int t=scan.nextInt();
		for(int tt=0;tt<t;tt++) {
			n=scan.nextInt();
			m=scan.nextInt();
			
			values=new int[n];
			weights=new int[n];
			
			int totalweight=0;
			int totalvalue=0;
			for(int i=0;i<n;i++) {
				values[i]=scan.nextInt();
				weights[i]=scan.nextInt();
				totalvalue+=values[i];
				totalweight+=weights[i];
			}
			
			//if the total weight of all objects is less than the max capacity,
			//we can just take them all, our job is already done:)
			if(totalweight<=m) {
				System.out.println(totalvalue);
				continue;
			}
			
			overcapacity=totalweight-m;
			
			//otherwise, we are totalweight-m over capacity.
			//we must figure out some subset of items to leave behind,
			//such that this subset of items has a total weight greater than totalweight-m,
			//and the total value of this subset of items is minimal.

			dp=new int[n][overcapacity+1];
			for(int[] d:dp) Arrays.fill(d,-1);
			
			//at last, we need to subtract this minimum value to remove
			//from the total value of all the items.
			System.out.println(totalvalue-go(0,0));
		}
	}
	public static int go(int at, int rem) {
		if(at==n) {
			//we have reached the end of the array.
			//if we have removed enough items, this is a valid way to go
			//otherwise, we haven't reached the goal and this is an approach
			//that can never be considered; we return an arbitrarily high value to be sure
			
			return rem==overcapacity?0:INF;
		}
		
		if(dp[at][rem]!=-1) {
			//we have already calculated the answer for this state
			//and stored the answer in this array
			return dp[at][rem];
		}
		
		//res = the min sum of values of the items we must remove from this point on
		int res=INF;
		
		//we do not remove this item from the knapsack; we simply move on
		res=Math.min(res,go(at+1,rem));
		
		//we choose to remove this item; the weight we removed increases,
		//and the sum of values we removed also increases
		res=Math.min(res,values[at]+go(at+1,Math.min(overcapacity,rem+weights[at])));
		
		return dp[at][rem]=res;
	}
	static int n,m;
	static int overcapacity;

	static int[] values, weights;
	static int[][] dp;
	
	static final int INF=Integer.MAX_VALUE/2-5;
}
