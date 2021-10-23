#include <bits/stdc++.h>
#define all(a) begin(a), end(a)
#define nl '\n'
typedef long long int ll;
int oo = -1^1<<31;
using namespace std;

void solve(int t) {
  // Get password
  string pass; cin >> pass;

  // Store lowercase letters
  vector<char> lower;

  // For every character
  for (char c : pass)
    // If it's upper, then print
    if (isupper(c)) cout << c;
    // Otherwise store
    else lower.push_back(c);

  // Print all lower
  for (char c : lower) cout << c;
  cout << nl;
}

int main()
{
  cin.tie(0)->sync_with_stdio(0);
  cin.exceptions(cin.failbit);

  int tt; cin >> tt;
  for (int t = 1; t <= tt; t++)
    solve(t);

  return 0;
}
