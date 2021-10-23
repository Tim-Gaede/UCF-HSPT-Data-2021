def bfs(adjList, skipU, skipV, n):

    # Basic BFS with a skipped edge
    q = []
    visited = [False for _ in range(n)]
    q.append(0)
    visited[0] = True
    while q:
        u = q.pop(0)
        for v in adjList[u]:
            if visited[v]:
                continue
            if u == skipU and v == skipV:
                continue
            q.append(v)
            visited[v] = True
    return visited[n-1]

def solve():

    n, m = [int(x) for x in input().split()]

    # List comprehension for 2D array
    adjList = [[] for _ in range(n)]

    # For every edge
    for _ in range(m):
        # Get and 0 index the indices
        u, v = [int(x) for x in input().split()]
        u-=1
        v-=1

        # Create edge between u and v
        adjList[u].append(v)
        adjList[v].append(u)

    # For every node
    for u in range(n):
        # For every edge in that node
        for v in adjList[u]:
            skipU, skipV = u, v
            # BFS without edge, if it fails we win
            if not bfs(adjList, skipU, skipV, n):
                print("Halt, Spider-Man! Plans Thwarted!")
                return

    # Made it through every edge safely
    print("How Sad, Perpetrator Triumphed.")


if __name__ == "__main__":
    t = int(input())
    for _ in range(t):
        solve()
