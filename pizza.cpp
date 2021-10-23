// pizza.cpp by Jacob Steinebronn

#include <bits/stdc++.h>
using namespace std;
typedef long long int ll;

// Define some constants based on the bounds
#define MAXN 10
#define MAXK 10
#define MAXMASK 2048
#define MAXUNIQUE 2048
#define MAXM 50000

// Count number of 1 bits in n using Brian Kernighan's Algo
int countSetBits(int n){
    int ans = 0;
    while (n) {
        n &= (n - 1);
        ans++;
    }
    return ans;
}

int n, m, k;
// We will precomp countSetBits for all inputs we care about
int numones[MAXMASK+10];
// dp is our memo table
int dp[MAXK+1][MAXMASK+1][MAXUNIQUE+1];
// This will be for the unique toppings we care about during our dp
vector<int> usables;

int solve(int topsRem, int mask, int idx){
    // If we've used all our toppings or there are none left, 
    // Return the  number of people we've blocked
    if(idx >= usables.size() || topsRem == 0) return numones[mask];
    // Check memoization
    if(dp[topsRem][mask][idx] != -1) return dp[topsRem][mask][idx];

    // Try taking this topping
    int take = solve(topsRem-1, mask | usables[idx], idx+1);
    // Try not taking this topping
    int leave = solve(topsRem, mask, idx+1);

    // Memoize & return with the better of the two
    return dp[topsRem][mask][idx] = max(take, leave);
}

int main(){
    // Precomp num 1 bits for each number we care about
    // to save some time
    for(int i = 0; i < MAXMASK+10; i++){
        numones[i] = countSetBits(i);
    }

    int NUM_TESTS;
    cin >> NUM_TESTS;
    while(NUM_TESTS--){
        cin >> n >> m >> k;

        // Get the bitmasks per topping
        vector<int> toppings(m+1, 0);
        for(int i = 0; i < n; i++){
            int t;
            cin >> t;
            for(int j = 0; j < t; j++){
                int ti;
                cin >> ti;
                // Topping ti can block person i
                toppings[ti] |= 1<<i;
            }
        }

        // Go set up the dp array with -1s
        for(int i = 0; i < n+1; i++){
            for(int j = 0; j < (1<<n)+1; j++){
                for(int kk = 0; kk < (1<<n)+1; kk++){
                    dp[i][j][kk] = -1;
                }
            }
        }
        
        // The idea here is that only 2^n toppings matter, because any more will have
        // duplicates. We can illustrate this idea by taking into consideration
        // two toppings who are each hated by person 1 and 3. We never need to 
        // have both toppings, because adding the second one gives us no benefit
        // Thus, the number of toppings we care about is the number of bitmasks
        // with n bits, or 2^n. We will collect these bitmasks in usables
        usables = vector<int>(0);
        vector<int> usedMasks(MAXMASK+10, 0);
        for(int i = 0; i < toppings.size(); i++){
            if(!usedMasks[toppings[i]]){
                usedMasks[toppings[i]] = 1;
                usables.emplace_back(toppings[i]);
            }
        }

        // If k >= n, we can always pick one topping per person, so no need
        // to do any work finding a minimal solution
        if(k >= n) cout << 0 << endl;
        else cout << n - solve(k, 0, 0) << endl;
    }

    return 0;
}