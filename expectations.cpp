// Asir Alam
// 02/18/2021
// Solution to 2021 UCF HSPT Problem: Improovion Expectations

#include <bits/stdc++.h>

#define ll long long

using namespace std;

int main(void) 
{
    int t, n;       // t = number of citizens, n = number of years
    int old_score;  // temporary variable
    int s[1000000]; // an array of maximum size 10^6 to store scores for every year

    // input number of test cases. i. e. number of citizens to evaluate
    cin >> t;

    // loop through every test case
    while(t--)
    {
        // input number of years to consider
        cin >> n;

        // input the scores for each year
        for(int i=0;i<n;i++)
            cin >> s[i]; 

        // need to use long long because the result can get very large
        // due to the input constraints. worst case approx: 10^6 x 10^5 = 10^11
        // regular integer cannot store this and will overflow
        ll res = 0; 

        // the 0th year serves as a starting point and does not have any past scores
        // to improve upon. so we loop starting next year (i = 1). 
        for(int i=1;i<n;i++) {

            // if a year's score is already larger than that of its previous year,
            // there is no need to gain any more points this year
            if (s[i] > s[i-1]) continue;

            // note the original amount of points before we update it
            old_score = s[i];

            // update the current year's score so that we can evaluate the upcoming years
            s[i] = s[i-1] + 1; 

            // the difference between the new score and the old score is the increase necessary
            res += s[i] - old_score;
        }

        // output the sum of all the increases necessary
        cout << res << '\n';
    }

    return 0;
}
