
# Read in number of melons
numMelons = int(input())

# Loop over melons
for i in range(0, numMelons):
   # Read in the current battery charge
   x, y = map(int, input().split())

   # Print the answer by simply adding the x and the y (don't forget trip back)
   print(x + y + x + y)

