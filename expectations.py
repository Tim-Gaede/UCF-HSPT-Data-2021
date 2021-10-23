# expectations.py by Jacob Steinebronn
# The idea of the solution is to keep track of the min
# possible score, and record the improvements we had to make

numTests = (int)(input().strip())

for testNum in range(numTests):
    arr = list(map(int, input().strip().split(" ")))
    n = arr[0]
    ans = 0
    scoreToBeat = 0

    # Iterate over all the items (ignoring the first one, since
    # it's the number of values and not a value itself)
    for x in arr[1:]:
        # If x isn't high enough, record the needed change
        if scoreToBeat >= x:
            ans += scoreToBeat - x + 1
        # Regardless of what we've done, we have to increase by 1
        # or to x, whichever's greater
        scoreToBeat = max(scoreToBeat+1, x)
        
    print(ans)