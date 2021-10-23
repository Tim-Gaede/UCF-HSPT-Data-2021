# house.py by Jacob Steinebronn
# The idea of the solution is to take the side
# length, and multiply by 5.

numTests = (int)(input().strip())

for testNum in range(numTests):

    sideLength = (int)(input().strip())
    print(sideLength * 5)