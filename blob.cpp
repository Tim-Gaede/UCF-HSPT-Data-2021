#include <bits/stdc++.h>
#define all(a) begin(a), end(a)
#define nl '\n'
typedef long long int ll;
int oo = -1^1<<31;
using namespace std;

// Inverse Ackerman DSU
struct DSU {
    vector<int> s;
    DSU(int n): s(n, -1) {}
    int find(int i) { return s[i] < 0 ? i : s[i] = find(s[i]); }
    bool join(int a, int b) {
        a = find(a), b = find(b);
        if (a == b) return false;
        if (s[a] > s[b]) swap(a, b);
        s[a] += s[b], s[b] = a;
        return true;
    }
    int size(int i) { return -s[find(i)]; }
    bool same(int a, int b) { return find(a) == find(b); }
};

void solve() {
    int n, m; cin >> n >> m;

    // Create DSU on children
    DSU dsu = DSU(n);
    // Keep track of how many hands each child still has
    vector<int> freeHands(n, 2);
    // Max blob size
    int maxBlob = 1;

    // For every collision
    for (int i = 0; i < m; i++) {
        // Get and 0 index collision
        int a, b; cin >> a >> b;
        a--; b--;

        // If either child doesn't have hands left, then don't join
        if (freeHands[a] == 0 || freeHands[b] == 0) continue;

        // Reduce hands and join
        freeHands[a]--; freeHands[b]--;
        dsu.join(a, b);
        maxBlob = max(maxBlob, dsu.size(a));
    }
    cout << maxBlob << nl;
}

int main()
{
    cin.tie(0)->sync_with_stdio(0);
    cin.exceptions(cin.failbit);

    int tt; cin >> tt;
    for (int t = 0; t < tt; t++)
        solve();

    return 0;
}
