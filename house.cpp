// Asir Alam
// 02/18/2021
// Solution to 2021 UCF HSPT Problem: Build-a-House

#include <bits/stdc++.h>

using namespace std;

int main(void) 
{    
    int t, s;

    // input number of test cases. i. e. number of houses
    cin >> t;

    // loop through every case
    while(t--)
    {
        // input length s
        cin >> s;

        // the answer is simply (5 * s) because the house's
        // outer perimeter involves 5 pieces, and each of them 
        // have an equal length of s
        cout << 5 * s << endl;
    }

    return 0;
}
