// 2021 UCF HSPT - Blob
// Author: Josh Delgado

import java.util.*;

public class blob {
  public static void main(String[] args) {
    // Create a scanner for input
    Scanner in = new Scanner(System.in); 

    // Grab the number of test cases (the number of classes Glenn is teaching)
    int numTests = in.nextInt(); 

    // Go through each test case
    for (int test=0; test<numTests; test++) {
      // Grab the input
      int numStudents = in.nextInt(); 
      int numCollisions = in.nextInt(); 
      
      // We'll keep track of the connections and store in a 2D array
      ArrayList<Integer>[] adj = new ArrayList[numStudents];
      for (int i=0; i<numStudents; i++) {
        adj[i] = new ArrayList<Integer>();
      }

      // We'll also keep track of if a pair of people are connected
      // This is to prevent repeat connections between the same two people
      boolean[][] isConnected = new boolean[numStudents][numStudents];
  
      // We'll also keep track of the number of hands used by each student
      int[] numHandsUsed = new int[numStudents];
      
      // We'll go through each collision
      for (int i=0; i<numCollisions; i++) {
        // Take in the input
        int a = in.nextInt()-1;
        int b = in.nextInt()-1;
        
        // If this pair is already connected, don't connect them again
        if (isConnected[a][b]) {
          continue;
        }
        
        // If either child has already used up both their hands
        // they don't stick together, so don't process this collision
        if (numHandsUsed[a] >= 2 || numHandsUsed[b] >= 2) {
          continue;
        }
        
        // Otherwise create a two-way connection between a and b
        adj[a].add(b);  // A is adjacent to B
        adj[b].add(a);  // B is adjacent to A
        isConnected[a][b] = true;
        isConnected[b][a] = true;

        // And each child has used up one of their hands
        numHandsUsed[a]++;
        numHandsUsed[b]++;
      }
      
      // Now we'll process each child and see how many people they can reach
      // We'll use a visited array to prevent redundant checks on the same children
      boolean[] visited = new boolean[numStudents];
      
      // Keeping track of the answer
      int ans = 1;
      
      // Process each child by exploring all the children the child can reach
      for (int startingChild=0; startingChild<numStudents; startingChild++) {
        // If we've already visited this child after starting from another child,
        // we don't need to re-explore from this child
        if (visited[startingChild]) {
          continue;
        }
  
        // Now we'll Breadth-First Search (BFS) out from this child to see all the children it's connected to
        int size = 0;
        ArrayDeque<Integer> queue = new ArrayDeque<>();
  
        // We'll start at the current starting child
        queue.addLast(startingChild);
  
        // BFS from this child.
        while (!queue.isEmpty()) {
          // Grab the current child in the BFS
          int curChild = queue.pollFirst(); 

          // Don't re-visit already explored children
          if (visited[curChild]) {
            continue;
          }

          // Mark this child as visited and add it to the size of this component
          visited[curChild] = true;
          size++;
  
          // Add all my unvisited neighbors to the queue
          for (int adjChild : adj[curChild]) {
            if (!visited[adjChild]) {
              queue.add(adjChild);
            }
          }
        }
  
        // Update the answer
        ans = Math.max(ans, size);
      }
  
      // Print the best answer from all points
      System.out.println(ans);
    }
  }
}
