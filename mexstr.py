#Scan in the number of test cases
t = int(input())
for run in range(t):
    #Scan in the given string
    word = input()
    #Assuming lowercase english characters, the minimum number of characters needed for some string to contain all possible strings of length K, is 26^K
    #Let N be the number of characters in the given word, 1<= N <= 500.
    #We can trivially guess to find the minimum value of K such that 26^K is greater than any possible value of N; that is K = 2.
    #Therefore, we can simply generate all possible strings of length 2 in lexicographic order until one of them is not included as a substring in the given word.
    foundAns = False
    #Try 1 letter words
    for i in range(26):
        string = ""
        char_val = ord('a') + i
        string = string + chr(char_val)
        if string not in word:
            print(string)
            foundAns = True
            break
    #Try 2 letter words
    for i in range(26):
        for j in range(26):
            if foundAns:
                break
            string = ""
            charOneVal = ord('a')+i
            charTwoVal = ord('a')+j
            string = string + chr(charOneVal)+chr(charTwoVal)
            if string in word:
                continue
            else:
                #Since we are brute forcing in lexicographical order, the first string not included in the word is the mex string!
                print(string)
                foundAns = True


