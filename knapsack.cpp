/*

The bounds don't allow for the traditional knapsack solution which requires a DP state of n*m.
Instead, we are going to assume we can take every item (get the sum of all values and weights).
Then, we check how much over m we are (totWeight - m). This is how much we need to drop.
Due to the bounds of the problem, this is at most 55,000. This means our state is 1,000*55,000 at most.
Then, we do a take-it-or-leave-it DP that minimizes the value of the items we need to drop.

*/

#include <bits/stdc++.h>
using namespace std;

int n;
int BIG_NUMBER = INT_MAX / 4;
vector<int> values, weights;
vector<vector<int>> dp;

// This is our DP solution
int go(int indx, int currW) {
    // If we drop enough weight at any time, we can stop dropping things
    if (currW <= 0) return 0;
    // If we reach the end of our array and haven't dropped enough weight, this is not a valid solution
    // Return a big number to ensure this path will not be our solution
    if (indx >= n) return BIG_NUMBER;
    // If we already saw this state, return the answer we got
    if (dp[indx][currW] != -1) return dp[indx][currW];

    // If we drop this item, we lose values[indx] and need to update our current weight
    int drop = values[indx] + go(indx + 1, currW - weights[indx]);
    // If we keep this item, we go to the next one
    int keep = go(indx + 1, currW);

    // Our value at this state is the min of drop and keep
    return dp[indx][currW] = min(drop, keep);
}

// Per test case solution
void solve() {
    // Input
    cin >> n;
    int m;
    cin >> m;

    values.assign(n, 0);
    weights.assign(n, 0);
    int totValue = 0, totWeight = 0;
    for (int i = 0; i < n; i++)
    {
        cin >> values[i];
        cin >> weights[i];
        totValue += values[i];      // Sum of all values
        totWeight += weights[i];    // Sum of all weights
    }

    // If we can take it all and not exceed capacity, let's do it
    if (totWeight <= m) cout << totValue << endl;
    // Otherwise, we need to do a take-it-or-leave-it DP
    else {
        // This is how much we need to drop
        int newM = totWeight - m;
        // Fill our DP table with -1
        dp.assign(n, vector<int>(newM+1, -1));
        // Do the DP
        cout << (totValue - go(0, newM)) << endl;
    }
}

int main() {
    // Number of input cases
    int t;
    cin >> t;

    while (t-- > 0)
        solve();

    return 0;
}