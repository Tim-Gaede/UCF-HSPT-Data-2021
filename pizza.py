# Solution for Pizza (HSPT 2021 - On Site)
# Author: Atharva Nagarkar

# read in number of test cases
tests = int(input())

# handy function to count number of on bits in a number's binary form
def bitcount(n): return bin(n).count("1")

for tc in range(1, tests + 1):

    # read in input for bounds
    n, m, k = map(int, input().split())
    
    # k (max number of toppings to choose) should never be more than n
    # this is because if you can choose more than n. This is because
    # if k is greater than or equal to n, you can simply choose one topping per person
    k = min(k, n)

    # for each topping, keep a bitmask of the people who hate this topping
    toppings = [ 0 for i in range(m) ]
    
    # read input for the topping preferences
    for i in range(n):
        line = input().split()
        t = int(line[0])
        for j in range(1, t+1):
            x = int(line[j]) - 1
            toppings[x] |= 1 << i

    # only keep around unique topping preferences
    # this reduces our bound on m from 50,000 to be at most 2^n, 
    # which is 1024 (much smaller than 50,000)
    toppings = list(set(toppings))
    m = len(toppings)

    # make our dp memoization table
    # fill it with -1's to specify that we have yet to evaluate the answer for this state
    dp = [ [ [-1 for i in range(1 << n)] for i in range(k + 1)] for i in range(m + 1) ]

    # the recursive dp function
    # the state we are using is dp[topping index][toppings left to choose][bitmask of people]
    def go(at, rem, mask):
        if at == m: # we are at the end, our answer will be n - people we dissatisfied
            return n - bitcount(mask)
        if dp[at][rem][mask] != -1: # we've calculated the answer for this state already
            return dp[at][rem][mask]
        
        ans = go(at + 1, rem, mask) # first transition is to skip this topping

        if rem > 0: # other transition is to consider this topping if possible
            ans = min(ans, go(at + 1, rem - 1, mask | toppings[at]))
        
        # store the answer and return it
        dp[at][rem][mask] = ans
        return ans

    # run the function and print the answer it returns
    print(go(0, k, 0))