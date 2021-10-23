# passwords.py by Jacob Steinebronn

numTests = (int)(input())

#For every test
for testNum in range(numTests):
    
    # Get the input
    password = (str)(input().strip())
    
    # We will collect the characters of password in these 
    lowercases = ""
    uppercases = ""
    
    # For every character, put it where it belongs
    for c in password:
        if c.isupper():
            # If it's an uppercase, put it with the uppercases
            uppercases += c
        else:
            # Otherwise, put it with the lowercases
            lowercases += c

    # Our answer is all the uppercases, then all the lowercases
    ans = uppercases + lowercases
    print(ans)