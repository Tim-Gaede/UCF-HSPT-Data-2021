
# Read in number of stops
numStops = int(input())

# Loop over stops
for i in range(0, numStops):
   # Read in the current battery charge
   charge = int(input())

   # Print the answer by simply subtracting current charge from 100
   print(100 - charge)

