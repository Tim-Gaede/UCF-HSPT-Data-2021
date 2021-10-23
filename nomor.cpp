#include <bits/stdc++.h>
#define all(a) begin(a), end(a)
#define nl '\n'
typedef long long int ll;
int oo = -1^1<<31;
using namespace std;

void solve() {
    // Get the full line
    string line; getline(cin, line);
    
    // For every character, if it's not e print it
    for (char c : line)
        if (c != 'e')
            cout << c;
    cout << nl;
}

int main()
{
    cin.tie(0)->sync_with_stdio(0);
    cin.exceptions(cin.failbit);

    int tt; cin >> tt;
    // Get rid of new line
    string newLinesSucc; getline(cin, newLinesSucc);
    for (int t = 0; t < tt; t++)
        solve();

    return 0;
}
