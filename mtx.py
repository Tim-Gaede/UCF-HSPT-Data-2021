# mtx.py by Jacob Steinebronn

# Get the number of tests to run
numTests = (int)(input().strip())

for testNum in range(numTests):
    # Get the number of players
    n = (int)(input().strip())
    
    # We will keep track of the best power, and if this is the sole best
    # as we take our input
    winningPower = -1
    winningFree = False

    # For each player...
    for i in range(n):
        # Get their skill and money
        s, m = list(map(int, input().strip().split()))
        # Calculate this player's power
        power = s*(1000+m)

        # If it's a tie and it's not free, we lose so far
        if power == winningPower and m != 0:
            winningFree = False
        elif power > winningPower:
            # If this player is the best so far, update our scores
            winningPower = power
            # m==0 is True if the player is free-to-play, and False otherwise
            winningFree = m == 0

    # print "Yes" if a free-to-play player is winning, otherwise print "No"
    print("YES" if winningFree else "NO")


