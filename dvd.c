#include <stdio.h>

int main(void) {
  int cases;
  scanf("%d",&cases);
  while(cases-->0){
    int w1, h1, w2, h2;
    scanf("%d%d%d%d",&w1,&h1,&w2,&h2);
    //We can simply run the simulation until the block ends in a corner, and count how many steps that took.
    //This is guaranteed to run fast enough because there are roughly 4*w2*h2 states the block be in. 4 directions it could be travelling in at any point.
    int wDir = 1, hDir = 1;
    int w = 0, h = 0;
    int turn = 0;
    while(1==1){
      //It's in one of the corners
      if((h+h1==h2 && w+w1 == w2) || (h+h1 == h2 && w==0) ||
          (h == 0 && w+w1 == w2) || (turn>0 && h==0 && w==0)) break;
      turn++;
      //If it touches some border, flip the velocity direction to continue moving within bounds.
      if(h+h1 == h2)hDir = -1;
      if(h == 0) hDir = 1;
      if(w+w1 == w2)wDir = -1;
      if(w==0)wDir = 1;
      //Make the move
      w+=wDir;
      h+=hDir;
    }
    printf("%d\n",turn);
  }
  return 0;
}
