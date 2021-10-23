# this is the intended solution; however, python is
# incredibly inefficient with memory allocation;
# we can achieve the same big O notation runtime as
# the solution outlined below but with much faster
# execution time using a memory technique known as 
# space saving; the full space saving solution is
# outlined in knapsackSpaceSave.py

t = int(input())
for tt in range(t):
	# scan first two numbers on the same line
	n, m = map(int, input().split())

	values = list()
	weights = list()

	# we will also keep track of the total of the weights
	totWeights = 0

	for i in range(n):
		v, w = map(int, input().split())
		values.append(v)
		weights.append(w)
		totWeights += w

	# getting the difference between the total weights of
	# all items and m, our max capacity, is crucial and this
	# will guide the rest of the solution; if this difference
	# is less than or equal to zero, we might as well take
	# every item with no consequence; otherwise, we will
	# have to make decisions; we can show this difference,
	# given the bounds of the problem, is bounded by 55,000
	diff = totWeights - m
	if diff < 0:
		diff = 0

	# we will now run a DP (Dynamic Programming) solution to
	# help us make our decisions; our state will be what index
	# of item we are on, and how much weight we have "skipped"
	# taking of all of the items behind us (the sum of all of
	# the weights that we have chosen to not take); when we reach
	# the end of our array, we will have needed to skip a sum of
	# weights >= diff

	# set up our dp with (n + 1) rows, one for each index including
	# the index just past the end of the array, and (diff + 1)
	# columns, one for each value of weights we skipped
	dp = list()
	for i in range(n + 1):
		dp.append(list())
		for skipped in range(diff + 1):
			# fill our dp array with -infinity (-10^12 has the same effect)
			dp[i].append(-1_000_000_000_000)

	# if we have no items left (index == n) and we have
	# skipped a total weight of diff or greater (skipped >= diff),
	# then our return will be 0 extra value, rather than -infinity;
	# this is our base case
	dp[n][diff] = 0

	# walk backwards through the items to build partial solutions
	for i in reversed(range(n)):
		# find answers for all amounts of weights we would have skipped
		for skipped in range(diff + 1):
			# choose to take this item
			take = values[i] + dp[i + 1][skipped]
			# choose to leave this item; if we are to exceed
			# diff, then min it with diff, as skipping exactly
			# diff and skipping more than diff has the same effect
			leave = dp[i + 1][min(diff, skipped + weights[i])]

			# take the best of the two options here
			dp[i][skipped] = max(take, leave)

	# print the value of dp[0][0], as at index 0, we will have skipped
	# a sum of 0 weight; so this value should store the minimum answer
	print(dp[0][0])
