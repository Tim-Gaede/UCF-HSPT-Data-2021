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

	# we will use a space-saving technique, where we only use
	# two arrays at a time, one for the dp of the index that we
	# are currently on, and one for the dp for the index 1 in
	# front of us

	# set up our dp at index n to be some really small value
	# for every position except for position diff, where it
	# will be 0
	dp = list()
	for skipped in range(diff):
		dp.append(-1_000_000_000_000)
	dp.append(0)

	for i in reversed(range(n)):
		# create a new dp table to store our answers for this index
		newDp = list()
		for skipped in range(diff + 1):
			# choose to take this item
			take = values[i] + dp[skipped]
			# choose to leave this item; if we are to exceed
			# diff, then min it with diff, as skipping exactly
			# diff and skipping more than diff has the same effect
			leave = dp[min(diff, skipped + weights[i])]

			# take the best of the two options here
			newDp.append(max(take, leave))

		# set dp to the newly calculated list
		dp = newDp.copy()

	# print the value of dp[0], as at index 0, we will have skipped
	# a sum of 0 weight
	print(dp[0])
