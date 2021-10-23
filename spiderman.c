// 2021 UCF HSPT - Stealing Spider-Man
// Author: Sathvik Kuthuru

#include <stdio.h>
#include <stdlib.h>

// Recursive Depth First Search function
int go(int at, int **adj, int *v, int n) {
	// If we reached the last vertex return true
	if(at == n - 1) return 1;
	v[at] = 1;
	for(int i = 0; i < n; i++) {
		// If there is a road and we have not visited i
		if(adj[at][i] && !v[i]) {
			// Check if last vertex is reachable from here
			if(go(i, adj, v, n)) return 1;
		}
	}
	// Last vertex is not reachable from here
	return 0;
}

// Helper function to free our dynamically allocated adj matrix
void freeAdj(int **adj, int n) {
	for(int i = 0; i < n; i++) {
		free(adj[i]);
	}
	free(adj);
}

void solve() {
	int n, m;
	scanf("%d %d", &n, &m);
	int **adj = (int **) malloc(sizeof(int *) * n);

	// Create an adjacency matrix where
	// adj[i][j] = 1 = there exists a road between intersection i and j
	// adj[i][j] = 0 = there is no road between i and j
	for(int i = 0; i < n; i++) {
		adj[i] = (int *) calloc(n, sizeof(int));
	}
	for(int i = 0; i < m; i++) {
		int a, b;
		scanf("%d %d", &a, &b);
		// Subtract because vertices are 0 indexed in adj matrix
		a--; b--;
		// Set a bidirectional edge (road) between intersections a and b
		adj[a][b] = adj[b][a] = 1;
	}
	for(int i = 0; i < n; i++) {
		for(int j = i + 1; j < n; j++) {
			// If there is a road between i and j
			// Try to remove the road and see if it is still possible to reach the last vertex
			if(adj[i][j]) {
				adj[i][j] = adj[j][i] = 0;
				int *visited = (int *) calloc(n, sizeof(int));
				int reached = go(0, adj, visited, n);
				free(visited);
				// If we cannot reach print and return
				if(!reached) {
					freeAdj(adj, n);
					printf("Halt, Spider-Man! Plans Thwarted!\n");
					return;
				}
				adj[i][j] = adj[j][i] = 1;
			}
		}
	}
	// Free the dynamic memory
	freeAdj(adj, n);
	// If we got here then it is impossible to block one road to prevent to getting to last vertex
	printf("How Sad, Perpetrator Triumphed.\n");
}

int main() {
	// Scan in number of test cases to process and solve for each one
	int numTests;
	scanf("%d", &numTests);
	for(int i = 1; i <= numTests; i++) {
		solve();
	}	return 0;
}
