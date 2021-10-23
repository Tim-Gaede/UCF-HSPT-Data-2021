# dvd.py by Jacob Steinebronn
# The idea of the solution is to simply simulate. Because
# the maximum length of this simulation is 4*m*n, since 
# you can be going in one of four directions per cell, our
# runtime will be acceptable to simulate.

numTests = (int)(input().strip())

for testNum in range(numTests):
    w1, h1, w2, h2 = list(map(int, input().strip().split(" ")))
    x, y = (1,1) 
    ans = 0
    vdir = 1
    hdir = 1

    while True:
        # Increase ans by 1, since we took one more step
        ans += 1
        # If we're in a corner, quit the loop
        if x+w1 == w2 or x == 0:
            if y+h1 == h2 or y == 0:
                break
        
        # If we're on the left or right side, switch horizontal direction
        if x+w1 == w2 or x == 0:
            hdir *= -1
        # If we're on the top or bottom, switch vertical direction
        if y+h1 == h2 or y == 0:
            vdir *= -1
        # Change our x and y coordinates by our direction
        x += hdir
        y += vdir
        
    # After the simulation exits, we print!
    print(ans)