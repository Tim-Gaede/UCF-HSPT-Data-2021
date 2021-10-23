#include <bits/stdc++.h>
#define all(a) begin(a), end(a)
#define nl '\n'
typedef long long int ll;
int oo = -1^1<<31;
using namespace std;

void solve(int t) {
  // Get digits
  int large, small; cin >> large >> small;

  // Print ans (dig*base^1 + dig*base^0)
  cout << large*6 + small << nl;
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
