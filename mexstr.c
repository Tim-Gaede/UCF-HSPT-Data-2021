// 2021 UCF HSPT - Minimum Excluded String
// Author: Sathvik Kuthuru

#include <stdio.h>
#include <string.h>

#define MAX_SIZE 501

void solve() {
	char a[MAX_SIZE];
	char res[3] = "";
	int foundMex = 0;
	scanf("%s", a);

	/* 
	Even though a recursive solution would work we can notice that
	the only way to have a string with a mex length of three or greater
	is for it to be 26 ^ 2 in length which is > 500 (not within bounds)
	so we only have to consider mex strings of length 1 and length 2
	*/

	// Try to check if mex string of length 1 exists
	for(char i = 'a'; i <= 'z'; i++) {
		res[0] = i;
		if(strstr(a, res) == NULL) {
			foundMex = 1;
			break;
		}
	}
	// If mex string of length 1 doesn't exist, try all strings of length 2
	// Make sure to go from 'a' to 'z' to ensure you get the smallest
	if(!foundMex) {
		for(char i = 'a'; i <= 'z'; i++) {
			for(char j = 'a'; j <= 'z'; j++) {
				res[0] = i;
				res[1] = j;
				if(strstr(a, res) == NULL) {
					foundMex = 1;
					break;
				}
			}
			if(foundMex) break;
		}
	}
	// Print out answer
	printf("%s\n", res);
}

int main() {
	// Scan in number of strings to process and solve for each one
	int numTests;
	scanf("%d", &numTests);
	for(int i = 1; i <= numTests; i++) {
		solve();
	}	
	return 0;
}
