# 2021 UCF HSPT - Nomor
# Author: Josh Delgado

# Grab the number of test cases, and read it in as an integer
numberTests = int(input())

# Go through every test case
for i in range(numberTests):
  # Grab the entire line of text
  lineOfText = input() 

  # Remove all the e's by replacing the e's with blanks (effectively deleting them)
  lineOfTextWithoutEs = lineOfText.replace('e', '') 

  # Print out the line without e's
  print(lineOfTextWithoutEs)