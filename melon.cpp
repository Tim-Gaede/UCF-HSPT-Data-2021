// 2021 UCF HSPT - Melon
// Author: Josh Delagdo

#include <bits/stdc++.h>

using namespace std;

int main() {
  // Read in the number of tests
  int numberTests;
  cin >> numberTests;

  // Go through every test
  for (int test=0; test<numberTests; test++) {
    // Read in both x and y
    int x, y;
    cin >> x >> y;

    // The path you must travel is along x and y, so it's x+y for one way
    // And you have to go to the melon AND back, so you add it again
    int totalLength = x+y + x+y;

    // Print the total length
    cout << totalLength << endl;
  }

  return 0;
}