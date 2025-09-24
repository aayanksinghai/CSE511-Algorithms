#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
 
vector<vector<int>> g;
vector<ll> cnt;
 
void dfs(int v, int p) {
    bool is_leaf = true;
    for (auto u : g[v]) {
        if (u != p) { // If 'u' is a child of 'v'
            is_leaf = false; // Then 'v' is not a leaf
            dfs(u, v);
            cnt[v] += cnt[u];
        }
    }
    // If the loop finished and we found no children, 'v' is a leaf.
    if (is_leaf) {
        cnt[v] = 1;
    }else {
        for (auto u : g[v]) {
            if (u != p) {
                dfs(u, v);
                cnt[v] += cnt[u];
            }
        }
    }
}
 
void solve() {
    int n, q;
    cin >> n;
 
    g.assign(n, vector<int>());
    
    for (int i = 0; i < n - 1; i++) {
        int u, v;
        cin >> u >> v;
        u--; v--;
        g[u].push_back(v);
        g[v].push_back(u);
    }
 
    cnt.assign(n, 0);
    dfs(0, -1);
 
    cin >> q;
    for (int i = 0; i < q; i++) {
        int c, k;
        cin >> c >> k;
        c--; k--;
 
        ll res = cnt[c] * cnt[k];
        cout << res << '\n';
    }
}
 
signed main() {
    int tests;
    cin >> tests;
    while (tests--) {
        solve();
    }
 
    return 0;
}