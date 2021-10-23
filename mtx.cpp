#include <bits/stdc++.h>
#define all(a) begin(a), end(a)
#define nl '\n'
typedef long long int ll;
int oo = -1^1<<31;
using namespace std;

void solve(int t) {
  // Get users
  int n; cin >> n;
  
  // Store current best
  int maxFree = -1, maxPaid = -1;

  for (int i = 0; i < n; i++) {
    // Get user data
    int skill, money; cin >> skill >> money;

    // Calculate power
    int power = skill * (1000 + money);

    // Update the maxFree or maxPaid accordingly
    money == 0 ? maxFree = max(maxFree, power) : maxPaid = max(maxPaid, power);
  }

  // Answer YES if the maxFree player has the higher power
  cout << (maxFree > maxPaid ? "YES" : "NO") << nl;
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
